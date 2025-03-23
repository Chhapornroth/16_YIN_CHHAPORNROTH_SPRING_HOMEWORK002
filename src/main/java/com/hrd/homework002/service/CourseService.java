package com.hrd.homework002.service;

import com.hrd.homework002.model.request.CourseRequest;
import com.hrd.homework002.model.response.CourseResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses(Integer offset, Integer limit);

    CourseResponse getCourseById(Long courseId);

    CourseResponse saveCourse(@Valid CourseRequest request);

    CourseResponse updateCourseById(Long courseId, @Valid CourseRequest request);

    void deleteCourseById(Long courseId);
}
