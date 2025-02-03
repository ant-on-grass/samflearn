package com.samflearn.dto.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.course.CourseCategory;
import lombok.Getter;

@Getter
public class CourseFindResponseDto {

    private Long id;

    private Long user_id;

    private String courseName;

    private Integer coursePrice;

    private CourseCategory category;

    public CourseFindResponseDto(Course course) {

        this.id = course.getId();
        this.user_id = course.getUser().getId();
        this.courseName = course.getCourseName();
        this.coursePrice = course.getCoursePrice();
        this.category = course.getCategory();

    }


}
