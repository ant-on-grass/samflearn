package com.samflearn.repository.course;

import com.samflearn.common.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long>,CourseQueryDslRepository {
}
