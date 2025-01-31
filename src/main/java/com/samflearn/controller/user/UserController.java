package com.samflearn.controller.user;

import com.samflearn.dto.user.UserRequestDto;
import com.samflearn.dto.user.UserResponseDto;
import com.samflearn.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.createUserService(requestDto);
        return new ResponseEntity<> (userResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserService(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
