package com.samflearn.dto.course;

import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseUpdateResponseDto {
    private final String course_name;
    private final Integer course_price;
    private final CourseCategory category;

    public static CourseUpdateResponseDto courseUpdateResponseDto(Course course){
        return new CourseUpdateResponseDto(
                course.getCourse_name(),
                course.getCourse_price(),
                course.getCategory()
        );
    }
}
