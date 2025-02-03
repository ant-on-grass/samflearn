package com.samflearn.service.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.user.User;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.dto.course.CourseUpdateResponseDto;
import com.samflearn.repository.course.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course createCourse(CourseRequestDto requestDto) {
        User user = courseRepository.findUserById(requestDto.getUserId());

        Course course = new Course(
                requestDto.getCourseName(),
                requestDto.getCoursePrice(),
                requestDto.getCategory(),
                user
        );

        return courseRepository.save(course);
    }

    @Transactional
    public List<CourseResponseDto> findAllCourse() {
        return courseRepository.findAllCourses().stream()
                .map(CourseResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseResponseDto> findLikeCourse(String course_name)
    {
        return courseRepository.findLikeCourse(course_name).stream()
                .map(CourseResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseUpdateResponseDto updateCourse(Long id, CourseRequestDto requestDto) {

        Course findCourse = courseRepository.findCourseById(id);
        findCourse.updateCourse(requestDto);
        Course updatedCourse = courseRepository.save(findCourse);

        return CourseUpdateResponseDto.courseUpdateResponseDto(updatedCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
