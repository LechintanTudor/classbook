package xyz.lechi.classbook.dto.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.lechi.classbook.dto.AuthUserDto;
import xyz.lechi.classbook.dto.RegisterDto;
import xyz.lechi.classbook.dto.UserDto;
import xyz.lechi.classbook.model.User;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromRegisterDto(RegisterDto registerDto) {
        return User.builder()
            .username(registerDto.getUsername())
            .firstName(registerDto.getFirstName())
            .lastName(registerDto.getLastName())
            .passwordHash(passwordEncoder.encode(registerDto.getPassword()))
            .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
    }

    public AuthUserDto toAuthUserDto(User user) {
        return AuthUserDto.builder()
            .id(user.getId())
            .username(user.getUsername())
            .passwordHash(user.getPasswordHash())
            .build();
    }
}
