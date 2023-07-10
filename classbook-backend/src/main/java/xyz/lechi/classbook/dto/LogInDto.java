package xyz.lechi.classbook.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LogInDto {
    private final String username;
    private final String password;
}
