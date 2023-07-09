package xyz.lechi.classbook.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddGradeDto {
    private final Long studentId;
    private final Long subjectId;
    private final int points;
}
