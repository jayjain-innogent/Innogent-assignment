package com.example.students.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseWithStudentCountDto {

    private Long courseId;
    private String courseName;
    private String instructor;
    private Long StudentCount;

}
