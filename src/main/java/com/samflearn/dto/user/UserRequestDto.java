package com.samflearn.dto.user;

import com.samflearn.common.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private  String name;

    private  String email;

    private  String password;

    private  UserRole userRole;
}
