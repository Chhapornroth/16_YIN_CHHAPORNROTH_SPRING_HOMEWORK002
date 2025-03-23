package com.hrd.homework002.service.service_implementation;

import com.hrd.homework002.exception.NotFoundException;
import com.hrd.homework002.model.entity.Student;
import com.hrd.homework002.model.request.StudentRequest;
import com.hrd.homework002.model.response.StudentResponse;
import com.hrd.homework002.repository.StudentCoursesRepository;
import com.hrd.homework002.repository.StudentRepository;
import com.hrd.homework002.service.CourseService;
import com.hrd.homework002.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentCoursesRepository studentCoursesRepository;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentResponse> getAllStudents(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        return studentRepository
                .findAll(offset, limit)
                .stream()
                .map(student ->  (modelMapper.map(student, StudentResponse.class)))
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId);
        if(student == null) {
            throw new NotFoundException("Course with id " + studentId + " not found");
        }
        return modelMapper.map(student, StudentResponse.class);
    }

    @Override
    public StudentResponse saveStudent(@Valid StudentRequest request) {
        request.getCourseIds().forEach(courseId -> {
            if(courseService.getCourseById(courseId) == null) {
                throw new NotFoundException("Course with ID " + courseId + " not found");
            }
        });
        Student student = studentRepository.save(request);
        request.getCourseIds().forEach(courseId -> studentCoursesRepository.saveStudentIdAndCourseId(student.getStudentId(), courseId));
        if(student == null) {
            throw new NotFoundException("Failed to save course!!!");
        }
        return modelMapper.map(studentRepository.findById(student.getStudentId()), StudentResponse.class);
    }

    @Override
    public StudentResponse updateStudentById(Long studentId, StudentRequest request) {
        request.getCourseIds().forEach(courseId -> {
            if(courseService.getCourseById(courseId) == null) {
                throw new NotFoundException("Student with ID " + courseId + " not found");
            }
        });
        int isModified = studentRepository.update(studentId, request);
        if(isModified == 0) {
            throw new NotFoundException("Student with id " + studentId + " not found");
        }
        studentCoursesRepository.deleteStudentCourses(studentId);
        request.getCourseIds().forEach(courseId -> studentCoursesRepository.saveStudentIdAndCourseId(studentId, courseId));
        return modelMapper.map(studentRepository.findById(studentId), StudentResponse.class);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        int row = studentRepository.delete(studentId);
        if(row == 0) {
            throw new NotFoundException("Student with id " + studentId + " not found");
        }
    }
}
