package xyz.lechi.classbook.controller;

import org.springframework.web.bind.annotation.*;
import xyz.lechi.classbook.model.Subject;
import xyz.lechi.classbook.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
}
