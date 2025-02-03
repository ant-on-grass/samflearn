package com.samflearn.common.entity;

import com.samflearn.dto.course.CourseRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "course")
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String course_name;
    private Integer course_price;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    public Course() {

    }

    public Course(String course_name, Integer course_price, CourseCategory category,User user) {
        this.course_name = course_name;
        this.course_price = course_price;
        this.category = category;
        this.user = user;
    }

    public void updateCourse(CourseRequestDto requestDto) {
        if (requestDto.getCourse_name() != null && !requestDto.getCourse_name().isEmpty()) {
            this.course_name = requestDto.getCourse_name();
        }
        if (requestDto.getCourse_price() != null) {
            this.course_price = requestDto.getCourse_price();
        }
        if (requestDto.getCategory() != null) {
            this.category = requestDto.getCategory();
        }
    }
}