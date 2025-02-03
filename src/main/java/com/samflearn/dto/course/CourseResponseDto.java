package com.samflearn.dto.course;

import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.CourseCategory;
import com.samflearn.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseResponseDto {
    private String course_name;
    private UserResponseDto user;
    private Integer course_price;
    private CourseCategory category;

    public CourseResponseDto(Course course){
        this.course_name = course.getCourse_name();
        this.user = getUser();
        this.course_price = course.getCourse_price();
        this.category = course.getCategory();
    }
}
