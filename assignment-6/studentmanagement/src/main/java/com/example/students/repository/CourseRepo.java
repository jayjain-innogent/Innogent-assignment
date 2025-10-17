package com.example.students.repository;

import com.example.students.dto.CourseNameWithStudentCount;
import com.example.students.dto.CourseWithStudentCountDto;
import com.example.students.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    @Query("select new com.example.students.dto.CourseWithStudentCountDto(c.courseId, c.courseName, c.instructor, COUNT(s)) " +
            "FROM Course c LEFT JOIN c.students s " +
            "GROUP BY c.courseId, c.courseName, c.instructor")
    List<CourseWithStudentCountDto> getCourseAndStudentCount();

    @Query("SELECT new com.example.students.dto.CourseWithStudentCountDto(" +
            "c.courseId, c.courseName, c.instructor, COUNT(s)) " +
            "FROM Course c LEFT JOIN c.students s " +
            "GROUP BY c.courseId, c.courseName, c.instructor " +
            "ORDER BY COUNT(s) DESC")
    Page<CourseWithStudentCountDto> findTopCourses(Pageable pageable);

    @Query("SELECT new com.example.students.dto.CourseWithStudentCountDto(" +
            "c.courseId, c.courseName, c.instructor, COUNT(s)) " +
            "FROM Course c LEFT JOIN c.students s " +
            "GROUP BY c.courseId, c.courseName, c.instructor " +
            "Having COUNT(s) = 0")
    List<CourseWithStudentCountDto> getCourseWithNoStudent();

    @Query("select new com.example.students.dto.CourseNameWithStudentCount(c.courseName, COUNT(s)) " +
            "FROM Course c LEFT JOIN c.students s " +
            "GROUP BY c.courseName")
    List<CourseNameWithStudentCount> getCourseNameAndStdCnt();
}

