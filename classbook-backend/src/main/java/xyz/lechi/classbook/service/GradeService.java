package xyz.lechi.classbook.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.exception.EntityNotFound;
import xyz.lechi.classbook.model.Grade;
import xyz.lechi.classbook.model.Student;
import xyz.lechi.classbook.model.Subject;
import xyz.lechi.classbook.repository.GradeRepository;
import xyz.lechi.classbook.repository.StudentRepository;
import xyz.lechi.classbook.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    @Transactional
    public Grade addGrade(Long studentId, Long subjectId, int points) {
        var student = studentRepository
            .findById(studentId)
            .orElseThrow(() -> new EntityNotFound(Student.class, studentId));

        var subject = subjectRepository
            .findById(subjectId)
            .orElseThrow(() -> new EntityNotFound(Subject.class, subjectId));

        var grade = Grade.builder()
            .student(student)
            .subject(subject)
            .points(points)
            .build();

        return gradeRepository.save(grade);
    }

    public List<Grade> getAllGradesForStudent(Long studentId) {
        return gradeRepository.findAllByStudentId(studentId);
    }
}
