package com.example.students.controller;

import com.example.students.entity.Student;
import com.example.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/students")
public class StudentController {



    @Autowired
    private StudentService studentService;
    @Autowired
    private PathMatcher pathMatcher;

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Server is running");
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Student student){
        studentService.create(student);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Student s, @PathVariable Integer id){
        studentService.update(s, id);
        return ResponseEntity.ok("Updated");
    }

    @GetMapping("/studentslist")
    public ResponseEntity<Object> getStudent(){
        Object students = studentService.studentList();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        studentService.delete(id);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional> findById(@PathVariable Integer id){
        Optional<Student> data = studentService.findById(id);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List> findByName(@PathVariable String name){
        List<Student> data = studentService.findByName(name);
        return ResponseEntity.ok(data);

    }
}
