package com.example.students.mapper;

import com.example.students.dto.CourseResponseDto;
import com.example.students.dto.CourseWithStudentCountDto;
import com.example.students.entity.Course;

public class CourseMapper {

    public static CourseResponseDto mapToCourseDto(Course course){
        return new CourseResponseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getInstructor()
        );
    }

    public static CourseResponseDto mapToCourseDto(CourseWithStudentCountDto course){
        return new CourseResponseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getInstructor()
        );
    }

    public static Course mapToCourse(CourseResponseDto courseResponseDto){
        return Course.builder()
                .courseId(courseResponseDto.getId())
                .courseName(courseResponseDto.getName())
                .instructor(courseResponseDto.getInstructor())
                .build();
    }


}
