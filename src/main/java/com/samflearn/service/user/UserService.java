package com.samflearn.service.user;

import com.samflearn.common.entity.User;
import com.samflearn.common.exception.user.NotFoundException;
import com.samflearn.dto.user.UserRequestDto;
import com.samflearn.dto.user.UserResponseDto;
import com.samflearn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUserService(UserRequestDto requestDto) {

        User user = new User(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getUserRole()
        );

        User saveUser = userRepository.save(user);

        return UserResponseDto.toDto(saveUser);
    }

    @Transactional
    public void deleteUserService(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("유저를 찾을 수 없습니다."));
        findUser.updateStatus();
    }
}
