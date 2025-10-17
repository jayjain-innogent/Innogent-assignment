package com.example.students.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponseDto {
    private Long id;
    private String name;
    private String instructor;

}
