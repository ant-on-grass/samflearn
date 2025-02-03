package com.samflearn.repository.like;

import com.samflearn.common.entity.course.Course;
import com.samflearn.common.entity.like.Like;
import com.samflearn.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndCourse(User user, Course course);

}
