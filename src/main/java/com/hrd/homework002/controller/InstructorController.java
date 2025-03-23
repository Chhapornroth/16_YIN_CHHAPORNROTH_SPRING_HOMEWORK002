package com.hrd.homework002.controller;

import com.hrd.homework002.api_response.APIResponse;
import com.hrd.homework002.model.request.InstructorRequest;
import com.hrd.homework002.model.response.InstructorResponse;
import com.hrd.homework002.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<APIResponse<List<InstructorResponse>>> getAllInstructors(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {
        List<InstructorResponse> instructors = instructorService.getAllInstructors(offset, limit);
        return ResponseEntity.ok(new APIResponse<>("All instructors have been successfully fetched.", instructors, HttpStatus.OK));
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<APIResponse<InstructorResponse>> getInstructorById(@PathVariable("instructor-id") Long instructorId) {
        InstructorResponse instructor = instructorService.getInstructorById(instructorId);
        return ResponseEntity.ok(new APIResponse<>("The instructor has been successfully founded.", instructor, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<APIResponse<InstructorResponse>> saveInstructor(@Valid @RequestBody InstructorRequest request) {
        InstructorResponse instructor = instructorService.saveInstructor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>("The instructor has been successfully added.", instructor, HttpStatus.CREATED));
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<APIResponse<InstructorResponse>> updateInstructorById(
            @PathVariable("instructor-id") Long instructorId,
            @Valid @RequestBody InstructorRequest request) {
        InstructorResponse instructor = instructorService.updateInstructorById(instructorId, request);
        return ResponseEntity.ok(new APIResponse<>("The instructor has been successfully updated.", instructor, HttpStatus.OK));
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable("instructor-id") Long instructorId) {
        instructorService.deleteInstructorById(instructorId);
        return ResponseEntity.ok(new APIResponse<>("The instructor has been successfully removed.", null, HttpStatus.OK));
    }
}
