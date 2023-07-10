package xyz.lechi.classbook.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
