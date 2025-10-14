package com.example.students.doa;

import com.example.students.entity.Student;
import com.example.students.repository.StudentRepo;
//import com.example.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDeo {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentDeo(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    //Create Student Data
    public void save(Student student){
        studentRepo.save(student);
    }

    //Delete Student Data By ID
    public void delete(Integer id){
        studentRepo.deleteById(id);
    }

    //Update Data By Id
    public void update(Student student, Integer id){
        Student new_s = studentRepo.findById(id).get();

        new_s.setName(student.getName());
        new_s.setEmail(student.getEmail());
        studentRepo.save(new_s);
    }

    //Show All Student Records
    public List<Student> findAll(){
        return studentRepo.findAll();
    }

    //Find Student By ID
    public Optional<Student> findById(Integer id){
        return studentRepo.findById(id);
    }

    //Find Student By Name
    public List<Student> findByName(String name){
        return studentRepo.findByName(name);
    }

}
