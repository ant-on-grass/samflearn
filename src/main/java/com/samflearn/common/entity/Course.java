package com.samflearn.common.entity;

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

    public Course(String course_name, Integer course_price, CourseCategory category,User user) {
        this.course_name = course_name;
        this.course_price = course_price;
        this.category = category;
        this.user = user;
    }

    public Course() {

    }
}