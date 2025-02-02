package com.samflearn.service.course;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samflearn.common.entity.Course;
import com.samflearn.common.entity.User;
import com.samflearn.dto.course.CourseRequestDto;
import com.samflearn.dto.course.CourseResponseDto;
import com.samflearn.repository.course.CourseRepository;
import com.samflearn.repository.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.samflearn.common.entity.QCourse.course;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;

    public Course createCourse(CourseRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Course course = new Course(requestDto.getCourse_name(), requestDto.getCourse_price(), requestDto.getCategory(),user);

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
}
