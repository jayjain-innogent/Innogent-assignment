package com.example.students.doa;

import com.example.students.dto.CourseNameWithStudentCount;
import com.example.students.dto.CourseWithStudentCountDto;
import com.example.students.entity.Course;
import com.example.students.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

//import java.awt.print.Pageable;
import java.util.List;

@Repository
public class CourseDao {

    @Autowired
    private CourseRepo courseRepo;

    //Create Course
    public Course createCourse(Course course){

        return courseRepo.save(course);
    }

    //Delete Course
    public void deleteCourse(Long id){ courseRepo.deleteById(id);}

    //Show All Course
    public List<Course> showAllCourse(){ return courseRepo.findAll(); }

    //Update Course By Id
    public void updateCourse(Course course){ courseRepo.save(course); }

    //Find Course By Id
    public Course findById(Long id){
        Course course = courseRepo.findById(id).get();
        return course;
    }

    //Get All courses with student count
    public List<CourseWithStudentCountDto> getStudentCount(){
        return courseRepo.getCourseAndStudentCount();
    }



    //Get Top N Course Based of enrollment
    public List<CourseWithStudentCountDto> getTopNCourses(int n) {
        Pageable pageable = PageRequest.of(0, n); // page 0, size = n
        return courseRepo.findTopCourses(pageable).stream().toList();
    }

    //GET COURSE WITH NO STUDENTS
    public List<CourseWithStudentCountDto> getCourseWithNoStudent(){
        return courseRepo.getCourseWithNoStudent();
    }

    //GET COURSE NAME AND STUDNETS COUNT
    public List<CourseNameWithStudentCount> getCourseNameAndStdCnt(){
        return courseRepo.getCourseNameAndStdCnt();
    }

}
