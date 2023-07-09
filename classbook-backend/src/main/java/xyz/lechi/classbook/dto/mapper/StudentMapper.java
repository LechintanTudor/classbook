package xyz.lechi.classbook.dto.mapper;

import org.springframework.stereotype.Component;
import xyz.lechi.classbook.dto.StudentDto;
import xyz.lechi.classbook.model.Student;

@Component
public class StudentMapper {
    public StudentDto toDto(Student student) {
        return StudentDto.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .build();
    }

    public Student fromDto(StudentDto studentDto) {
        return Student.builder()
            .id(studentDto.getId())
            .firstName(studentDto.getFirstName())
            .lastName(studentDto.getLastName())
            .build();
    }
}
