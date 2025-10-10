package com.example.students.service;


import com.example.students.entity.Student;
import com.example.students.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo ;

    public Student create(Student s){
     return studentRepo.save(s);
    }

    public Student update(Student s, Integer id){

        Student new_s = studentRepo.findById(id).get();
        new_s.setId(id);
        new_s.setName(s.getName());
        new_s.setEmail(s.getEmail());
        return studentRepo.save(new_s);
    }

    public Object studentList(){
        List<Student> students = new ArrayList<>();
        students = studentRepo.findAll();
        return students;

    }

    public void deleteData(Integer id){
        studentRepo.deleteById(id);
    }
}
