package xyz.lechi.classbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xyz.lechi.classbook.dto.StudentDto;
import xyz.lechi.classbook.dto.mapper.StudentMapper;
import xyz.lechi.classbook.model.Student;
import xyz.lechi.classbook.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PostMapping
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        var student = studentMapper.fromDto(studentDto);
        return studentMapper.toDto(studentService.addStudent(student));
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService
            .getAllStudents()
            .stream()
            .map(studentMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        var student = studentService.getStudentById(id);

        if (student.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such student");
        }

        return student.get();
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @DeleteMapping
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }
}
