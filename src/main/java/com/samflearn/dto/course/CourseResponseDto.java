package com.samflearn.dto.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.course.CourseCategory;
import com.samflearn.common.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseResponseDto {
    private String course_name;
    private User user;
    private Integer course_price;
    private CourseCategory category;

    public CourseResponseDto(Course course){
        this.course_name = course.getCourseName();
        this.user = course.getUser();
        this.course_price = course.getCoursePrice();
        this.category = course.getCategory();
    }
}
