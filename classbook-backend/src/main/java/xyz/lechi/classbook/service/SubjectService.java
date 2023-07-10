package xyz.lechi.classbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.model.Subject;
import xyz.lechi.classbook.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
