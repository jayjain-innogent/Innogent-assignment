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

    //CREATE
    @PostMapping("/create")
    public void create(@RequestBody CourseRequestDto course){
        courseService.createCourse(course);
    }

    //GET COURSE DETAILS WITH STUDENT COUNT
    @GetMapping("/get-course-with-std-cnt")
    public List<CourseWithStudentCountDto> getCourseDetail(){
        return courseService.getCourseAndStdCnt();
    }

    //GET TOP COURSES
    @GetMapping("/gettop/{n}")
    public ResponseEntity<List<CourseWithStudentCountDto>> getTopNCourses(@PathVariable int n){
        return new ResponseEntity<>(courseService.getTopCourse(n), HttpStatus.OK);
    }

    //Fetch All Course Data
    @GetMapping("/get-all-courses")
    public ResponseEntity<List<CourseResponseDto>> fetchAll() {
        List<CourseResponseDto> data = courseService.fetchAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



    //Update Course By ID
    @PutMapping("/update/{id}")
    public ResponseEntity<CourseResponseDto> updateById(@RequestBody CourseUpdateDto courseUpdateDto, @PathVariable Long id){
        return new ResponseEntity<>(courseService.updateById(courseUpdateDto,id), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    //GET COURSE WITH NO ENROLLMENTS
    @GetMapping("/no-students-enrolled")
    public ResponseEntity<List<CourseResponseDto>> getCourseWithNoStd(){
        return new ResponseEntity<>(courseService.getCourseWithNoStudent(), HttpStatus.OK);
    }

    //GET COURSE NAME WITH COUNT OF STUDENT
    @GetMapping("/course-name-stdcount")
    public ResponseEntity<List<CourseNameWithStudentCount>> getCourseNameAndStdCnt(){
        return new ResponseEntity<>(courseService.getCourseNameAndStdCnt(), HttpStatus.OK);
    }



}
