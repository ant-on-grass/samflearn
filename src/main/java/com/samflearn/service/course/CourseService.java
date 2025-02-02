package com.samflearn.service.course;

import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.User;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.repository.course.CourseRepository;
import com.samflearn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Course createCourse(CourseRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Course course = new Course(requestDto.getCourse_name(), requestDto.getCourse_price(), requestDto.getCategory(),user);

        return courseRepository.save(course);
    }
}
