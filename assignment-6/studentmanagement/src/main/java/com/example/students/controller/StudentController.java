package com.example.students.controller;

import com.example.students.dto.StudentDto;
import com.example.students.dto.StudentRequestDto;
import com.example.students.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")   // changed base path only
public class StudentController {

    @Autowired
    private StudentServices studentServices;
    @Autowired
    private PathMatcher pathMatcher;

    // SERVER TESTING
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Server is running");
    }

    // CREATE STUDENT WITH COURSE ID
    @PostMapping("/course/{id}")
    public ResponseEntity<StudentDto> create(
            @RequestBody StudentRequestDto student,
            @PathVariable Long id) {
        StudentDto std = studentServices.createStudent(student, id);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    // UPDATE STUDENT BY ID
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody StudentDto s, @PathVariable Long id) {
        studentServices.updateStd(s, id);
        return ResponseEntity.ok("Updated");
    }

    // FETCH ALL STUDENTS WITH COURSE DETAILS
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudent() {
        return new ResponseEntity<>(studentServices.studentList(), HttpStatus.OK);
    }

    // DELETE STUDENT BY ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentServices.deleteById(id);
        return ResponseEntity.ok("Deleted " + id);
    }

    // FIND STUDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(studentServices.findById(id), HttpStatus.OK);
    }

    // FIND STUDENT BY COURSE ID
    @GetMapping("/course/id/{id}")
    public List<StudentDto> findStudentsByCourseId(@PathVariable Long id) {
        return studentServices.findStudentsByCourseId(id);
    }

    // FIND STUDENT BY FIRST NAME
    @GetMapping("/name")
    public ResponseEntity<List<StudentDto>> findByFirstName(@RequestParam String name) {
        return new ResponseEntity<>(studentServices.findByName(name), HttpStatus.OK);
    }

    // FIND STUDENT BY CITY AND INSTRUCTOR
    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> findStudentsByCityAndInstructor(
            @RequestParam String city,
            @RequestParam String instructor) {
        return new ResponseEntity<>(studentServices.findStdByCityAndInstructor(city, instructor), HttpStatus.OK);
    }

    // STUDENT WITHOUT ANY COURSE
    @GetMapping("/no-course")
    public ResponseEntity<List<StudentDto>> findStudentWithoutCourse() {
        return new ResponseEntity<>(studentServices.findStudentWithoutCourse(), HttpStatus.OK);
    }

    // FIND STUDENT BY COURSE NAME
    @GetMapping("/course/name")
    public ResponseEntity<List<StudentDto>> findStudentWithCourseName(@RequestParam String name) {
        return new ResponseEntity<>(studentServices.findStudentWithCourseName(name), HttpStatus.OK);
    }
}
