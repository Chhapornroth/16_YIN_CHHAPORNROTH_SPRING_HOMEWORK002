package com.hrd.homework002.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "Course name is required!!!")
    private String courseName;
    private String description;
    @NotNull(message = "instructor ID is required!!!")
    private Long instructorId;
}
