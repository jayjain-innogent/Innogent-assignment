package com.example.students.controller;

import com.example.students.dto.StudentDto;
import com.example.students.dto.StudentRequestDto;
//import com.example.students.entity.Student;
//import com.example.students.service.StudentService;
//import com.example.students.mapper.StudentMapper;
//import com.example.students.repository.StudentRepo;
import com.example.students.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/api/student")
public class StudentController {



    @Autowired
    private StudentServices studentServices;
    @Autowired
    private PathMatcher pathMatcher;

    //SERVER TESTING
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Server is running");
    }

    //CREATEING STUDENT WITH COURSE ID
    @PostMapping("/create/{id}")
    public ResponseEntity<StudentDto> create(@RequestBody StudentRequestDto student, @PathVariable Long id){
        StudentDto std = studentServices.createStudent(student,id);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    //UPDATING STUDENT BY ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody StudentDto s, @PathVariable Long id){
        studentServices.updateStd(s, id);
        return ResponseEntity.ok("Updated");
    }

     //FETCH ALL THE STUDENT WITH COURSE DETAIL
    @GetMapping("/studentslist")
    public ResponseEntity<List<StudentDto>> getStudent(){
        return new ResponseEntity<>(studentServices.studentList(), HttpStatus.OK);
    }
//
     //DELETING SUTUDENT BY ID
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> delete(@PathVariable Long id){
        studentServices.deleteById(id);
        return ResponseEntity.ok("Deleted " + id);
    }

    //FIND STUDENT BY ID
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(studentServices.findById(id), HttpStatus.OK);
    }



    @GetMapping("/getStudentByCourseId/{id}")
    public List<StudentDto> findStudentsByCourseId(@PathVariable Long id){
        return studentServices.findStudentsByCourseId(id);
        }

    //Find Student BY first NAME
    @GetMapping("/find-by-first-name")
    public ResponseEntity<List<StudentDto>> findByFirstName(@RequestParam String name){
        return new ResponseEntity<>(studentServices.findByName(name), HttpStatus.OK);
    }


    //FIND STUDENT BY CITY AND INSTRUCTOR
    @GetMapping("/find-std-by-city-and-instructor")
    public ResponseEntity<List<StudentDto>> findStudentsByCityAndInstructor(
            @RequestParam String city,
            @RequestParam String instructor){
        return new ResponseEntity<>(studentServices.findStdByCityAndInstructor(city, instructor), HttpStatus.OK);
    }

    // STUDENT WITHOUT ANY COURSE
    @GetMapping("/get-student-without-course")
    public ResponseEntity<List<StudentDto>> findStudentWithoutCourse(){
        return new ResponseEntity<>(studentServices.findStudentWithoutCourse(),HttpStatus.OK);
    }

    //FIND STUDENT BY COURSE NAME
    @GetMapping("/get-student-with-course-name")
    public ResponseEntity<List<StudentDto>> findStudentWithCourseName(@RequestParam String name){
        return new ResponseEntity<>(studentServices.findStudentWithCourseName(name),HttpStatus.OK);
    }
}
