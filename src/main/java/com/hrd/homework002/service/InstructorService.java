package com.hrd.homework002.service;

import com.hrd.homework002.model.request.InstructorRequest;
import com.hrd.homework002.model.response.InstructorResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface InstructorService {
    List<InstructorResponse> getAllInstructors(Integer offset, Integer limit);

    InstructorResponse getInstructorById(Long instructorId);

    InstructorResponse saveInstructor(@Valid InstructorRequest request);

    InstructorResponse updateInstructorById(Long instructorId, @Valid InstructorRequest request);

    void deleteInstructorById(Long instructorId);
}
