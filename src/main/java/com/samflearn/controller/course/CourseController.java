package com.samflearn.controller.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.dto.course.CourseFindResponseDto;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.dto.course.CourseSortByLikeResponseDto;
import com.samflearn.dto.course.CourseUpdateResponseDto;
import com.samflearn.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> findAllCourse(
    ){
        List<CourseResponseDto> findCourseList = courseService.findAllCourse();

        return new ResponseEntity<>(findCourseList, HttpStatus.OK);
    }

    @GetMapping("/{courseName}")
    public ResponseEntity<List<CourseResponseDto>> findLikeCourse(
            @PathVariable String courseName
    ){
        List<CourseResponseDto> findCourseList = courseService.findLikeCourse(courseName);

        return new ResponseEntity<>(findCourseList, HttpStatus.OK);
    }

    @GetMapping("/v1/like")
    public ResponseEntity<Page<CourseFindResponseDto>> findCourseAPI(Pageable pageable,
        @RequestParam("courseName") String courseName
    ){
        Page<CourseFindResponseDto> findCourseList = courseService.findCourse(pageable,courseName);

        return new ResponseEntity<>(findCourseList, HttpStatus.OK);
    }

    @GetMapping("/v2/like")
    public ResponseEntity<Page<CourseFindResponseDto>> findCourseV2API(Pageable pageable,
                                                                     @RequestParam("courseName") String courseName
    ){
        Page<CourseFindResponseDto> findCourseList = courseService.findCourseV2(pageable,courseName);

        return new ResponseEntity<>(findCourseList, HttpStatus.OK);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<CourseSortByLikeResponseDto>> findLikeCourseAPI(

    ){
        List<CourseSortByLikeResponseDto> findCourseList = courseService.findCourseByLike();

        return new ResponseEntity<>(findCourseList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseUpdateResponseDto> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDto requestDto
    ){
        CourseUpdateResponseDto updateCourse = courseService.updateCourse(id,requestDto);
        return new ResponseEntity<>(updateCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDto> deleteCourse(
            @PathVariable Long id){
        courseService.deleteCourse(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
