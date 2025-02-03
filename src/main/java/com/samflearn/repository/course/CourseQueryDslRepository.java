package com.samflearn.repository.course;

import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.User;

import java.util.List;

public interface CourseQueryDslRepository {
    List<Course> findAllCourses();
    List<Course> findLikeCourse(String courseName);
    Course findCourseById(Long id);
    User findUserById(Long userId);
}
