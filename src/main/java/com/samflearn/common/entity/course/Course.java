package com.samflearn.common.entity.course;

import com.samflearn.common.entity.user.User;
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

    private String courseName;
    private Integer coursePrice;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    public Course() {

    }

    public Course(String courseName, Integer coursePrice, CourseCategory category,User user) {
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.category = category;
        this.user = user;
    }

    public void updateCourse(CourseRequestDto requestDto) {
        if (requestDto.getCourseName() != null && !requestDto.getCourseName().isEmpty()) {
            this.courseName = requestDto.getCourseName();
        }
        if (requestDto.getCoursePrice() != null) {
            this.coursePrice = requestDto.getCoursePrice();
        }
        if (requestDto.getCategory() != null) {
            this.category = requestDto.getCategory();
        }
    }
}