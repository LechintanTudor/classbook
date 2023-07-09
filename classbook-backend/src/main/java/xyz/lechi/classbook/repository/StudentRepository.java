package xyz.lechi.classbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.lechi.classbook.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Empty
}
