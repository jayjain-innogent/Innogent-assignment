package com.example.students.mapper;

import com.example.students.dto.StudentDto;
import com.example.students.dto.StudentRequestDto;
import com.example.students.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student){
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setCity(student.getCity());

        if (student.getCourse() != null){
            dto.setCourse(CourseMapper.mapToCourseDto(student.getCourse()));
        }
        return dto;

    }

    public static Student mapToStudent(StudentRequestDto studentRequestDto){
        return Student.builder()
                .firstName(studentRequestDto.getFirstName())
                .lastName(studentRequestDto.getLastName())
                .email(studentRequestDto.getEmail())
                .city(studentRequestDto.getCity())
                .course(studentRequestDto.getCourse())
                .build();
    }

}
