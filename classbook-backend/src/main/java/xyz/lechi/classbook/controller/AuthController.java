package xyz.lechi.classbook.controller;

import org.springframework.web.bind.annotation.*;
import xyz.lechi.classbook.dto.RegisterDto;
import xyz.lechi.classbook.dto.TokenDto;
import xyz.lechi.classbook.dto.UserDto;
import xyz.lechi.classbook.dto.mapper.UserMapper;
import xyz.lechi.classbook.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto registerDto) {
        var user = userMapper.fromRegisterDto(registerDto);
        user = userService.addUser(user);
        return userMapper.toDto(user);
    }

    @PostMapping("/login")
    public TokenDto login() {
        return null;
    }

    @GetMapping
    public void loginStatus() {

    }

    @PostMapping("/logout")
    public void logout() {

    }
}
