package com.hrd.homework002.controller;

import com.hrd.homework002.api_response.APIResponse;
import com.hrd.homework002.model.request.CourseRequest;
import com.hrd.homework002.model.response.CourseResponse;
import com.hrd.homework002.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CourseResponse>>> getAllCourses(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        List<CourseResponse> students = courseService.getAllCourses(offset, limit);
        return ResponseEntity.ok(new APIResponse<>("All courses have been successfully fetched.", students, HttpStatus.OK));
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<APIResponse<CourseResponse>> getCourseById(@PathVariable("course-id") Long courseId){
        CourseResponse course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(new APIResponse<>("The course has been successfully founded.", course, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<APIResponse<CourseResponse>> saveCourse(@Valid @RequestBody CourseRequest request) {
        CourseResponse course = courseService.saveCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>("The course has been successfully added.", course, HttpStatus.CREATED));
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<APIResponse<CourseResponse>> updateCourseById(
            @PathVariable("course-id") Long courseId,
            @Valid @RequestBody CourseRequest request) {
        CourseResponse course = courseService.updateCourseById(courseId, request);
        return ResponseEntity.ok(new APIResponse<>("The course has been successfully updated.", course, HttpStatus.OK));
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable("course-id") Long courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok(new APIResponse<>("The course has been successfully removed.", null, HttpStatus.OK));
    }
}
