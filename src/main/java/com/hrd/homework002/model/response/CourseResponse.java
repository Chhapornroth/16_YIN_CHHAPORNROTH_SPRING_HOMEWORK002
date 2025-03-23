package com.hrd.homework002.model.response;

import com.hrd.homework002.model.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long courseId;
    private String courseName;
    private String description;
    private Instructor instructor;
}
