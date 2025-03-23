package com.hrd.homework002.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
    private Long instructorId;
    private String instructorName;
    private String email;
}
