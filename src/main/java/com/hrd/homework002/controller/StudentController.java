package com.hrd.homework002.controller;

import com.hrd.homework002.api_response.APIResponse;
import com.hrd.homework002.model.request.StudentRequest;
import com.hrd.homework002.model.response.StudentResponse;
import com.hrd.homework002.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudents(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        List<StudentResponse> students = studentService.getAllStudents(offset, limit);
        return ResponseEntity.ok(new APIResponse<>("All students have been successfully fetched.", students, HttpStatus.OK));
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<APIResponse<StudentResponse>> getStudentById(@PathVariable("student-id") Long studentId){
        StudentResponse student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(new APIResponse<>("The student has been successfully founded.", student, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<APIResponse<StudentResponse>> saveStudent(@Valid @RequestBody StudentRequest request) {
        StudentResponse student = studentService.saveStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>("The student has been successfully added.", student, HttpStatus.CREATED));
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<APIResponse<StudentResponse>> updateStudentById(
            @PathVariable("student-id") Long studentId,
            @Valid @RequestBody StudentRequest request) {
        StudentResponse student = studentService.updateStudentById(studentId, request);
        return ResponseEntity.ok(new APIResponse<>("The student has been successfully updated.", student, HttpStatus.OK));
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("student-id") Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok(new APIResponse<>("The student has been successfully removed.", null, HttpStatus.OK));
    }
}
