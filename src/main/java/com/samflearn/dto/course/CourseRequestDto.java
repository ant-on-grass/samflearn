package com.samflearn.dto.course;

import com.samflearn.common.entity.course.CourseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseRequestDto {
    private String courseName;
    private Long userId;
    private Integer coursePrice;
    private CourseCategory category;
}