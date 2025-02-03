package com.samflearn.repository.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.dto.course.CourseFindResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseQueryDslRepository {
    List<Course> findAllCourses();
    List<Course> findLikeCourse(String courseName);
    Course findCourseById(Long id);
    public Page<CourseFindResponseDto> findPageCourses(Pageable pageable,String requestString);
    public List<CourseFindResponseDto> findPageCoursesByLike();
}
