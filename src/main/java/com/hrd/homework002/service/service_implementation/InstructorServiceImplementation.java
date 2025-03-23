package com.hrd.homework002.service.service_implementation;

import com.hrd.homework002.exception.NotFoundException;
import com.hrd.homework002.model.entity.Instructor;
import com.hrd.homework002.model.request.InstructorRequest;
import com.hrd.homework002.model.response.InstructorResponse;
import com.hrd.homework002.repository.InstructorRepository;
import com.hrd.homework002.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImplementation implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InstructorResponse> getAllInstructors(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        return instructorRepository
                .findAll(offset, limit)
                .stream()
                .map(instructor ->  (modelMapper.map(instructor, InstructorResponse.class)))
                .toList();
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId);
        if(instructor == null) {
            throw new NotFoundException("Instructor with id " + instructorId + " not found");
        }
        return modelMapper.map(instructor, InstructorResponse.class);
    }

    @Override
    public InstructorResponse saveInstructor(@Valid InstructorRequest request) {
        Instructor instructor = instructorRepository.save(request);
        if(instructor == null) {
            throw new NotFoundException("Failed to save instructor!!!");
        }
        return modelMapper.map(instructor, InstructorResponse.class);
    }

    @Override
    public InstructorResponse updateInstructorById(Long instructorId, @Valid InstructorRequest request) {
        Instructor instructor = instructorRepository.findById(instructorId);
        if(instructor == null) {
            throw new NotFoundException("Instructor with id " + instructorId + " not found");
        }
        return modelMapper.map(instructorRepository.update(instructorId, request), InstructorResponse.class);
    }

    @Override
    public void deleteInstructorById(Long instructorId) {
        int row = instructorRepository.delete(instructorId);
        if(row == 0) {
            throw new NotFoundException("Instructor with id " + instructorId + " not found");
        }
    }
}
