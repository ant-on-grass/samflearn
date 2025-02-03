package com.samflearn.dto.user;

import com.samflearn.common.entity.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {

    private final String name;

    private final String email;

    private final String password;

    private final UserRole userRole;
}
