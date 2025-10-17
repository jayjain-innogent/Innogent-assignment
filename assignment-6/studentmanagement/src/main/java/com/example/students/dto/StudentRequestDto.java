package com.example.students.dto;

import com.example.students.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private Course course;
}
