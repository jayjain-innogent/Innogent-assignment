package com.example.students.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private CourseResponseDto course;

}
