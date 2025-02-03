package com.samflearn.dto.course;

import com.samflearn.common.entity.CourseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseRequestDto {
    private String course_name;
    private Long user_id;
    private Integer course_price;
    private CourseCategory category;
}