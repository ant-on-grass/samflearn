package com.samflearn.dto.user;

import com.samflearn.common.entity.User;
import com.samflearn.common.entity.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final UserRole userRole;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole()
        );
    }

}
