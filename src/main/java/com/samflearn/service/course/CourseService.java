package com.samflearn.service.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.user.User;
import com.samflearn.common.exception.user.NotFoundException;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.dto.course.CourseUpdateResponseDto;
import com.samflearn.repository.course.CourseRepository;
import com.samflearn.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Course createCourse(CourseRequestDto requestDto) {
        User findUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));

        Course course = new Course(
                requestDto.getCourseName(),
                requestDto.getCoursePrice(),
                requestDto.getCategory(),
                findUser
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
