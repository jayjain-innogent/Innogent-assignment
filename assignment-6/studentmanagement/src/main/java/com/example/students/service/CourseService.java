package com.example.students.service;

import com.example.students.doa.CourseDao;
import com.example.students.dto.CourseNameWithStudentCount;
import com.example.students.dto.CourseRequestDto;
import com.example.students.dto.CourseResponseDto;
import com.example.students.dto.CourseWithStudentCountDto;
import com.example.students.entity.Course;
import com.example.students.mapper.CourseMapper;
import com.example.students.repository.CourseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public  class CourseService {
    @Autowired
    private CourseDao courseDao;

    @Transactional
    public void createCourse(CourseRequestDto courseDto){

        Course course = CourseMapper.mapToCourse(courseDto);
        courseDao.createCourse(course);
    }

    //Get Course Details with students count

    public List<CourseWithStudentCountDto> getCourseAndStdCnt(){
        return courseDao.getStudentCount();
    }

    //Get the top n Course

    public List<CourseWithStudentCountDto> getTopCourse(int n){
        return courseDao.getTopNCourses(n);
    }

    //Get All Data
    public List<CourseResponseDto> fetchAll(){
        List<Course> courses = courseDao.showAllCourse();
        List<CourseResponseDto> responseData = new ArrayList<>();
        for (Course c: courses){
            CourseResponseDto dto = CourseMapper.mapToCourseDto(c);
            responseData.add(dto);
        }
        return responseData;
    }

    //Update Student By Id
    @Transactional
    public CourseResponseDto updateById(CourseResponseDto courseResponseDto, Long id){
        Course course = courseDao.findById(id);

        if (courseResponseDto.getInstructor() != null){
            course.setInstructor(courseResponseDto.getInstructor());
        }
        courseDao.createCourse(course);
        return CourseMapper.mapToCourseDto(course);
    }

    //DELETE COURSE
    public void deleteById(Long id){
        courseDao.deleteCourse(id);
    }

    //Get COURSE WITH ZERO STUDENT
    public List<CourseResponseDto> getCourseWithNoStudent(){
        List<CourseWithStudentCountDto> course_data = courseDao.getCourseWithNoStudent();
        List<CourseResponseDto> course_dto = new ArrayList<>();

        for (CourseWithStudentCountDto c: course_data){
            CourseResponseDto dto = CourseMapper.mapToCourseDto(c);
            course_dto.add(dto);
        }
        return course_dto;
    }

    //GET COURSE NAME WITH STUDENT COUNT
    public List<CourseNameWithStudentCount> getCourseNameAndStdCnt(){
        return courseDao.getCourseNameAndStdCnt();
    }
}
