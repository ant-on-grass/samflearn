package com.samflearn.dto.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.course.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseUpdateResponseDto {
    private final String courseName;
    private final Integer coursePrice;
    private final CourseCategory category;

    public static CourseUpdateResponseDto courseUpdateResponseDto(Course course){
        return new CourseUpdateResponseDto(
                course.getCourseName(),
                course.getCoursePrice(),
                course.getCategory()
        );
    }
}
