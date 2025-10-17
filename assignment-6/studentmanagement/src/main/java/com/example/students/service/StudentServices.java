package com.example.students.service;


import com.example.students.doa.StudentDao;
import com.example.students.dto.StudentDto;
import com.example.students.dto.StudentRequestDto;
import com.example.students.entity.Course;
import com.example.students.entity.Student;
import com.example.students.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServices {
    @Autowired
    private StudentDao studentDao;

    public StudentDto createStudent(StudentRequestDto student, Long id){
        Course course = studentDao.findCourseById(id);
        student.setCourse(course);
        Student std =  studentDao.createStudent(StudentMapper.mapToStudent(student));
        StudentDto dto = StudentMapper.mapToStudentDto(std);
        return dto;
    }

    public List<StudentDto> findStudentsByCourseId(Long id){
        List<Student> student = studentDao.findStudentsByCourseId(id);
        List<StudentDto> dtoData = new ArrayList<>();
        for (Student s : student){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            dtoData.add(dto);
        }

        return dtoData;

    }

    //UPDATE STUDENTS USING ID
    public StudentDto updateStd(StudentDto studentDto,Long id){
        Student std = studentDao.findById(id);
        if (studentDto.getCity() != null){
            std.setCity(studentDto.getCity());
        }
        if (studentDto.getFirstName() != null){
            std.setFirstName(studentDto.getFirstName());
        }
        if (studentDto.getLastName() != null){
            std.setLastName(studentDto.getLastName());
        }
        studentDao.createStudent(std);
        return studentDto;
    }
    // GET ALL THE STUDENT WITH COURSE
    public List<StudentDto> studentList(){
        List<Student> student = studentDao.studentList();
        List<StudentDto> student_dto = new ArrayList<>();
        for (Student s : student){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            student_dto.add(dto);
        }
        return student_dto;
    }

    //DElETE THE STUDENT BY ID
    public void deleteById(Long id){
        studentDao.deleteStudentById(id);
    }

    //FIND STUDENT BASED ON ID
    public StudentDto findById(Long id){
        Student student = studentDao.findById(id);
        return StudentMapper.mapToStudentDto(student);
    }

    //FIND STUDENT BY NAME
    public List<StudentDto> findByName(String Name){
        List<Student> std_data = studentDao.findByFirstName(Name);
        List<StudentDto> std_dto = new ArrayList<>();
        for (Student s: std_data){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            std_dto.add(dto);
        }
        return std_dto;
    }
    //FIND STUDENT BY CITY AND INSTRUCTOR
    public List<StudentDto> findStdByCityAndInstructor(String city, String instructor){
        List<Student> std_data = studentDao.findStdByCityAndInstructor(city, instructor);
        List<StudentDto> std_dto = new ArrayList<>();

        for(Student s: std_data){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            std_dto.add(dto);
        }

        return std_dto;
    }

    // STUDENT WITHOUT ANY COURSE
    public List<StudentDto> findStudentWithoutCourse(){
        List<Student> std_data = studentDao.findStudentWithoutCourse();
        List<StudentDto> std_dto = new ArrayList<>();

        for(Student s: std_data){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            std_dto.add(dto);
        }

        return std_dto;
    }

    //FIND STUDENT BY COURSE NAME
    public List<StudentDto> findStudentWithCourseName(String name){
        List<Student> std_data = studentDao.findStudentWithCourseName(name);
        List<StudentDto> std_dto = new ArrayList<>();

        for(Student s: std_data){
            StudentDto dto = StudentMapper.mapToStudentDto(s);
            std_dto.add(dto);
        }

        return std_dto;
    }
}
