package com.samflearn.service.user;

import com.samflearn.common.entity.User;
import com.samflearn.dto.user.UserRequestDto;
import com.samflearn.dto.user.UserResponseDto;
import com.samflearn.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
