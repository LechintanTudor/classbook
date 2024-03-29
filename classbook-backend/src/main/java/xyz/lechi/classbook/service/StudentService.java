package xyz.lechi.classbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.lechi.classbook.model.Student;
import xyz.lechi.classbook.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().toList();
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
