package xyz.lechi.classbook.controller;

import org.springframework.web.bind.annotation.*;
import xyz.lechi.classbook.dto.AddGradeDto;
import xyz.lechi.classbook.model.Grade;
import xyz.lechi.classbook.service.GradeService;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public Grade addGrade(@RequestBody AddGradeDto addGradeDto) {
        return gradeService.addGrade(
            addGradeDto.getStudentId(),
            addGradeDto.getSubjectId(),
            addGradeDto.getPoints()
        );
    }

    @GetMapping
    public List<Grade> getAllGradesForStudent(@RequestParam Long studentId) {
        return gradeService.getAllGradesForStudent(studentId);
    }
}
