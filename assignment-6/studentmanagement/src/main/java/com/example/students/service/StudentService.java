package com.example.students.service;


import com.example.students.doa.StudentDeo;
import com.example.students.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class StudentService {
    private final StudentDeo studentDeo;

    @Autowired
    public StudentService(StudentDeo studentDeo){
        this.studentDeo = studentDeo;
    }

    @Transactional
    public void create(Student s){
        studentDeo.save(s);
    }

    @Transactional
    public void update(Student s, Integer id){
          studentDeo.update(s, id);
    }

    public void delete(Integer id){
        studentDeo.delete(id);
    }

    public Object studentList(){
        return studentDeo.findAll();
    }

    public Optional<Student> findById(Integer id){
        return studentDeo.findById(id);

    }

    public List<Student> findByName(String name){
        return studentDeo.findByName(name);
    }
}
