package xyz.lechi.classbook.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
}
