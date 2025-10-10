package com.example.students.controller;

import com.example.students.entity.Student;
import com.example.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public Student create(@RequestBody Student s){
        return studentService.create(s);
    }

    @PutMapping("/update/{id}")
    public Student update(@RequestBody Student s, @PathVariable Integer id){
        return studentService.update(s, id);
    }

    @GetMapping("/studentslist")
    public Object getStudent(){
        Object students = studentService.studentList();
        return students;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        studentService.deleteData(id);
    }
}
