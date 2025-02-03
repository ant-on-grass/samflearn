package com.samflearn.service.like;

import com.samflearn.common.entity.like.Like;
import com.samflearn.common.entity.user.User;
import com.samflearn.common.exception.user.NotFoundException;
import com.samflearn.repository.course.CourseRepository;
import com.samflearn.repository.like.LikeRepository;
import com.samflearn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LikeRepository likeRepository;

    public void createLikeService(Long user, Long course) {

        User findUser = userRepository.findById(user)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 유저입니다."));

        Course findCourse = courseRepository.findById(course)
                .orElseThrow(()-> new NotFoundException("존재하지 않는 강의 입니다."));

        if(likeRepository.existsByUserAndCourse(findUser, findCourse)) {
            throw new RuntimeException("이미 좋아요를 눌렀습니다"); //익셉션 핸들러로 변경
        }

        Like like = new Like();

        like.addUser(findUser);

        like.addCourse(findCourse);

        likeRepository.save(like);

    }

    public void deleteLikeService(Long like, Long user, Long course) {

        Like findLike = likeRepository.findById(like)
                .orElseThrow(() -> new NotFoundException("좋아요를 누르지 않은 강의 입니다"));
        likeRepository.delete(findLike);

    }
}
