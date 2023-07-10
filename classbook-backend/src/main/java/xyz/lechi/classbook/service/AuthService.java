package xyz.lechi.classbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.dto.LogInDto;
import xyz.lechi.classbook.dto.RegisterDto;
import xyz.lechi.classbook.dto.TokenDto;
import xyz.lechi.classbook.dto.UserDto;
import xyz.lechi.classbook.dto.mapper.UserMapper;
import xyz.lechi.classbook.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto register(RegisterDto registerDto) {
        var user = userMapper.fromRegisterDto(registerDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public TokenDto logIn(LogInDto logInDto) {
        var authToken = new UsernamePasswordAuthenticationToken(
            logInDto.getUsername(),
            logInDto.getPassword()
        );

        var userDetails = (UserDetails) authenticationManager
            .authenticate(authToken)
            .getPrincipal();

       return new TokenDto(jwtService.createJwt(userDetails));
    }
}
