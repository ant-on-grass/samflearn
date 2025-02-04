package com.samflearn.repository.course;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samflearn.common.entity.course.Course;
import com.samflearn.common.generic.CustomPageImpl;
import com.samflearn.dto.course.CourseFindResponseDto;
import com.samflearn.dto.course.CourseSortByLikeResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.samflearn.common.entity.course.QCourse.course;
import static com.samflearn.common.entity.like.QLike.like;
import static org.springframework.data.domain.Sort.Order.desc;

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
                .where(course.courseName.like("%" + courseName + "%"))
                .fetch();
    }

    @Override
    public Course findCourseById(Long id) {
        return queryFactory.selectFrom(course)
                .where(course.id.eq(id))
                .fetchOne();
    }
//
    @Override
    public Page<CourseFindResponseDto> findPageCourses(Pageable pageable,String requestString) {
        List<CourseFindResponseDto> courseList = queryFactory.select(
                Projections.constructor(
                        CourseFindResponseDto.class,
                        course
                )
        )
                .from(course)
                .where(likeCourse(requestString))
                .orderBy(getSortOrders(pageable))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        Long totalCount = Optional.ofNullable(queryFactory.select(
                Wildcard.count)
                .from(course)
                .fetchOne()).orElse(0L);

        return new CustomPageImpl<>(courseList,pageable,totalCount);

    }

    @Override
    public List<CourseSortByLikeResponseDto> findPageCoursesByLike() {
        List<CourseSortByLikeResponseDto> courseList = queryFactory.select(
                        Projections.constructor(
                            CourseSortByLikeResponseDto.class,
                                course,
                            JPAExpressions.
                            select(like.id.count())
                                .from(course)
                                .where(course.id.eq(like.course.id))
                        )
                )
                .from(course)
                .leftJoin(like).on(like.course.id.eq(course.id))
                .fetchJoin()
                .groupBy(course.id)
                .limit(10L)
                .orderBy(like.id.count().desc())
                .fetch();

        // 조인
        // 쿼리 // ㅜ


        return courseList;

    }




    private BooleanExpression likeCourse(String requestString) {
        if (requestString == null || requestString.isEmpty()) {
            return null;
        }
        return course.courseName.like("%" + requestString + "%");
    }


    private OrderSpecifier<?>[] getSortOrders(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            String property = order.getProperty();
            boolean isAscending = order.isAscending();

            OrderSpecifier<?> orderSpecifier = switch (property) {
                case "coursePrice" -> isAscending ? course.coursePrice.asc() : course.coursePrice.desc();
                default -> course.id.asc();
            };
            orders.add(orderSpecifier);
        }
        return orders.toArray(new OrderSpecifier[]{});
    }


}