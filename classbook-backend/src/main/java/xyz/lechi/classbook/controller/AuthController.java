package xyz.lechi.classbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xyz.lechi.classbook.dto.LogInDto;
import xyz.lechi.classbook.dto.RegisterDto;
import xyz.lechi.classbook.dto.TokenDto;
import xyz.lechi.classbook.dto.UserDto;
import xyz.lechi.classbook.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public TokenDto logIn(@RequestBody LogInDto logInDto) {
        return authService.logIn(logInDto);
    }

    @PostMapping("/logout")
    public void logOut() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return;
        }

        var token = (String) authentication.getCredentials();
        authService.logOut(token);
    }
}
