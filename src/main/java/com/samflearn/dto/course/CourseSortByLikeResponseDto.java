package com.samflearn.dto.course;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.course.CourseCategory;
import com.samflearn.common.entity.like.Like;
import lombok.Getter;

@Getter
public class CourseSortByLikeResponseDto {

    private Long id;

    private Long user_id;

    private String courseName;

    private Integer coursePrice;

    private CourseCategory category;

    private long courseByLikeCount;

    public CourseSortByLikeResponseDto(Course course, long courseByLikeCount) {

        this.id = course.getId();
        this.user_id = course.getUser().getId();
        this.courseName = course.getCourseName();
        this.coursePrice = course.getCoursePrice();
        this.category = course.getCategory();
        this.courseByLikeCount= courseByLikeCount;

    }
}
