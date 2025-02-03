package com.samflearn.common.entity.like;

import com.samflearn.common.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "like")
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    public void addUser(User user) {
        this.user = user;
    }

    public void addCourse(Course course) {
        this.course = course;
    }

}
