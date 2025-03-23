package com.hrd.homework002.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {
    @NotBlank (message = "Instructor's name is required!!!")
    private String instructorName;

    @NotBlank(message = "Email is required!!!")
    @Email(message = "Wrong format!!!")
    private String email;
}
