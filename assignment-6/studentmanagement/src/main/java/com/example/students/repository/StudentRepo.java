package com.example.students.repository;

import com.example.students.entity.Course;
import com.example.students.entity.Student;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findStudentByFirstName(String name);

    @Query("SELECT c FROM Course c WHERE c.courseId = :id")
    Course findCourseById(@Param("id") Long id);

    @Query("Select s from Student s Where s.course.courseId = :id")
    List<Student> findStudentsByCourseId(@Param("id") Long id);

    @Query("Select s from Student s WHERE s.city = :city and s.course.instructor = :instructor")
    List<Student> findStdByCityAndInstructor(@Param("city") String city, @Param("instructor") String instructor);

    @Query("SELECT s From Student s Where s.course is null")
    List<Student> findStudentWithoutCourse();

    @Query("select s from Student s where s.course.courseName = :name")
    List<Student> findStudentWithCourseName(@Param("name") String name);

//

}

