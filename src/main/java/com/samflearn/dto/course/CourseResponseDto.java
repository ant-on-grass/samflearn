package com.samflearn.dto.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.course.CourseCategory;
import com.samflearn.dto.user.UserNameResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseResponseDto {
    private String courseName;
    private UserNameResponseDto user;
    private Integer coursePrice ;
    private CourseCategory category;

    public CourseResponseDto(Course course){
        this.courseName = course.getCourseName();
        this.user = new UserNameResponseDto(course.getUser());
        this.coursePrice = course.getCoursePrice();
        this.category = course.getCategory();
    }
}
