package com.hrd.homework002.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<CourseResponse> courses;
}
