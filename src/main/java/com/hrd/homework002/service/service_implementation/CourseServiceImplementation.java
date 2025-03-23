package com.hrd.homework002.service.service_implementation;

import com.hrd.homework002.exception.NotFoundException;
import com.hrd.homework002.model.entity.Course;
import com.hrd.homework002.model.request.CourseRequest;
import com.hrd.homework002.model.response.CourseResponse;
import com.hrd.homework002.repository.CourseRepository;
import com.hrd.homework002.service.CourseService;
import com.hrd.homework002.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImplementation implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final ModelMapper modelMapper;

    @Override
    public List<CourseResponse> getAllCourses(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        return courseRepository
                .findAll(offset, limit)
                .stream()
                .map(course ->  (modelMapper.map(course, CourseResponse.class)))
                .toList();
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId);
        if(course == null) {
            throw new NotFoundException("Course with id " + courseId + " not found");
        }
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse saveCourse(@Valid CourseRequest request) {
        if(instructorService.getInstructorById(request.getInstructorId()) == null) {
            throw new NotFoundException("Instructor with ID " + request.getInstructorId() + " not found");
        }
        Course course = courseRepository.save(request);
        if(course == null) {
            throw new NotFoundException("Failed to save course!!!");
        }
        return modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse updateCourseById(Long courseId, @Valid CourseRequest request) {
        if(instructorService.getInstructorById(request.getInstructorId()) == null) {
            throw new NotFoundException("Instructor with ID " + request.getInstructorId() + " not found");
        }
        Course course = courseRepository.findById(courseId);
        if(course == null) {
            throw new NotFoundException("Course with id " + courseId + " not found");
        }
        return modelMapper.map(courseRepository.update(courseId, request), CourseResponse.class);
    }

    @Override
    public void deleteCourseById(Long courseId) {
        int row = courseRepository.delete(courseId);
        if(row == 0) {
            throw new NotFoundException("Course with id " + courseId + " not found");
        }
    }
}
