package com.samflearn.service.course;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.User;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.dto.course.CourseUpdateResponseDto;
import com.samflearn.repository.course.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.samflearn.common.entity.QCourse.course;
import static com.samflearn.common.entity.QUser.user;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    public Course createCourse(CourseRequestDto requestDto) {
        queryFactory = new JPAQueryFactory(em);

        User fetchOne = queryFactory
                .selectFrom(user)
                .where(user.id.eq(requestDto.getUser_id()))
                .fetchOne();

        Course course = new Course(requestDto.getCourse_name(), requestDto.getCourse_price(), requestDto.getCategory(),fetchOne);

        return courseRepository.save(course);
    }

    @Transactional
    public List<CourseResponseDto> findAllCourse() {
        queryFactory = new JPAQueryFactory(em);

        List<Course> courseList = queryFactory
                .selectFrom(course)
                .fetch();

        return courseList.stream()
                .map(CourseResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseResponseDto> findLikeCourse(String course_name)
    {
        queryFactory = new JPAQueryFactory(em);

        List<Course> courseList = queryFactory
                .selectFrom(course)
                .where(course.course_name.like("%" + course_name +"%"))
                .fetch();

        return courseList.stream()
                .map(CourseResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseUpdateResponseDto updateCourse(Long id, CourseRequestDto requestDto) {
        queryFactory = new JPAQueryFactory(em);

        Course findCourse = queryFactory
                .selectFrom(course)
                .where(course.id.eq(id))
                .fetchOne();
        findCourse.updateCourse(requestDto);
        Course updateCourse = courseRepository.save(findCourse);

        return CourseUpdateResponseDto.courseUpdateResponseDto(updateCourse);
    }
}
