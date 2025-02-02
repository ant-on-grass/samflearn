package com.samflearn.controller.course;

import com.samflearn.common.entity.Course;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(
            @RequestBody CourseRequestDto requestDto
            ){
        Course course = courseService.createCourse(requestDto);

        CourseResponseDto courseResponseDto = new CourseResponseDto(course);

        return new ResponseEntity<>(courseResponseDto, HttpStatus.CREATED);
    }
}
