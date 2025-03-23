package com.hrd.homework002.service;

import com.hrd.homework002.model.request.StudentRequest;
import com.hrd.homework002.model.response.StudentResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents(Integer offset, Integer limit);

    StudentResponse getStudentById(Long studentId);

    StudentResponse saveStudent(@Valid StudentRequest request);

    StudentResponse updateStudentById(Long studentId, @Valid StudentRequest request);

    void deleteStudentById(Long studentId);
}
