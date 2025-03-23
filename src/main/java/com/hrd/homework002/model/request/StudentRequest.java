package com.hrd.homework002.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank (message = "Student's name is required!!!")
    private String studentName;

    @NotBlank(message = "Email is required!!!")
    @Email(message = "Wrong format!!!")
    private String email;

    @NotBlank(message = "Phone number is required!!!")
    @Pattern(regexp = "^\\d+$", message = "Please follow the phone number format!!!")
    private String phoneNumber;

    @NotNull(message = "Course Id is required!!!")
    private Set<Long> courseIds;
}
