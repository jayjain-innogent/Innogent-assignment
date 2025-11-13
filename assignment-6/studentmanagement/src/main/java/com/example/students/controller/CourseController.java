package com.example.students.controller;

import com.example.students.dto.*;
import com.example.students.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // CREATE COURSE
    @PostMapping
    public void create(@RequestBody CourseRequestDto course){
        courseService.createCourse(course);
    }

    // COURSE DETAIL WITH STUDENT COUNT
    @GetMapping("/student-count")
    public List<CourseWithStudentCountDto> getCourseDetail(){
        return courseService.getCourseAndStdCnt();
    }

    // GET TOP N COURSES
    @GetMapping("/top/{n}")
    public ResponseEntity<List<CourseWithStudentCountDto>> getTopNCourses(@PathVariable int n){
        return new ResponseEntity<>(courseService.getTopCourse(n), HttpStatus.OK);
    }

    // FETCH ALL COURSE DATA
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> fetchAll() {
        List<CourseResponseDto> data = courseService.fetchAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // UPDATE COURSE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateById(
            @RequestBody CourseUpdateDto courseUpdateDto,
            @PathVariable Long id){
        return new ResponseEntity<>(courseService.updateById(courseUpdateDto,id), HttpStatus.OK);
    }

    // DELETE COURSE BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    // GET COURSES WITH NO STUDENTS
    @GetMapping("/no-students")
    public ResponseEntity<List<CourseResponseDto>> getCourseWithNoStd(){
        return new ResponseEntity<>(courseService.getCourseWithNoStudent(), HttpStatus.OK);
    }

    // GET COURSE NAME + STUDENT COUNT ONLY
    @GetMapping("/name-student-count")
    public ResponseEntity<List<CourseNameWithStudentCount>> getCourseNameAndStdCnt(){
        return new ResponseEntity<>(courseService.getCourseNameAndStdCnt(), HttpStatus.OK);
    }

}