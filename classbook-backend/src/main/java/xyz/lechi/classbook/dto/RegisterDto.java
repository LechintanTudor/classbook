package xyz.lechi.classbook.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
