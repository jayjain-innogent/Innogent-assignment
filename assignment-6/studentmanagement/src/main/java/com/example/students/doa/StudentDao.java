package com.example.students.doa;

import com.example.students.dto.StudentDto;
import com.example.students.entity.Course;
import com.example.students.entity.Student;
import com.example.students.exception.ResourceNotFoundException;
import com.example.students.exception.StudentNotFoundException;
import com.example.students.repository.StudentRepo;
//import com.example.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    @Autowired
    private StudentRepo studentRepo;

    //create new Student
    public Student createStudent(Student student){
        Student std = studentRepo.save(student);
        return std;
    }

    //Delete Student By Id
    public void deleteStudentById(Long id){
        Student student = studentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student Not Found")
        );
        studentRepo.deleteById(id);
    }

    //Update Student By Id
    public void updateStudentById(Student student){
        studentRepo.save(student);
    }

    //Find Student by id
    public Student findById(long id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found "+ id));

    }

    //Find By First Name
    public List<Student> findByFirstName(String name){

        return studentRepo.findStudentByFirstName(name);
    }



    //Show All the student
    public List<Student> showAll(){
        return studentRepo.findAll();
    }

    public Course findCourseById(Long id){
        return studentRepo.findCourseById(id);

    }

    //Find Student By Id
    public Student findById(Long id){
        return studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student with id "+id));
    }

    public List<Student> findStudentsByCourseId(Long id){
        return studentRepo.findStudentsByCourseId(id);
    }

    // GET STUDENT DETAILS WITH COURSE
    public List<Student> studentList(){
        return studentRepo.findAll();
    }



    //FIND STUDENT BY CITY AND INSTRUCTOR
    public List<Student> findStdByCityAndInstructor(String city, String instructor){
        List<Student> students =  studentRepo.findStdByCityAndInstructor(city, instructor);

        if(students.isEmpty()){
            throw new StudentNotFoundException("No students found in " + city + " taught by " + instructor);
        }
        return students;
    }

    // STUDENT WITHOUT ANY COURSE
    public List<Student> findStudentWithoutCourse(){
        List<Student> data = studentRepo.findStudentWithoutCourse();
        if(data.isEmpty()){
            throw new StudentNotFoundException("No student without any Course");
        }
        return data;
    }

    //FIND STUDENT BY COURSE NAME
    public List<Student> findStudentWithCourseName(String name){
        List<Student> data = studentRepo.findStudentWithCourseName(name);
        if(data.isEmpty()){
            throw new StudentNotFoundException("No student without any Course");
        }
        return data;
    }
}
