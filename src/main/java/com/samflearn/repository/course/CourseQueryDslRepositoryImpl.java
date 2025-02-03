package com.samflearn.repository.course;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samflearn.common.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.samflearn.common.entity.QCourse.course;

@Repository
@RequiredArgsConstructor
public class CourseQueryDslRepositoryImpl implements CourseQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Course> findAllCourses() {
        return queryFactory
                .selectFrom(course)
                .fetch();
    }

    @Override
    public List<Course> findLikeCourse(String courseName) {
        return queryFactory.selectFrom(course)
                .where(course.course_name.like("%" + courseName + "%"))
                .fetch();
    }

    @Override
    public Course findCourseById(Long id) {
        return queryFactory.selectFrom(course)
                .where(course.id.eq(id))
                .fetchOne();
    }
}