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
import xyz.lechi.classbook.exception.EntityNotFound;
import xyz.lechi.classbook.model.JwtToken;
import xyz.lechi.classbook.model.User;
import xyz.lechi.classbook.repository.JwtTokenRepository;
import xyz.lechi.classbook.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final JwtTokenService jwtTokenService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

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

        var user = userRepository
            .findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFound(User.class));

        var token = jwtTokenService.createJwtToken(userDetails);

        var jwtToken = JwtToken.builder()
            .user(user)
            .token(token)
            .isRevoked(false)
            .isExpired(false)
            .build();

        jwtTokenRepository.save(jwtToken);
        return new TokenDto(token);
    }

    public void logOut(String token) {
        var jwtToken = jwtTokenRepository
            .findByToken(token)
            .orElseThrow(() -> new EntityNotFound(JwtToken.class));

        jwtToken.setRevoked(true);
        jwtTokenRepository.save(jwtToken);
    }
}
