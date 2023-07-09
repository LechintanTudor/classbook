package xyz.lechi.classbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.lechi.classbook.model.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByStudentId(Long studentId);
}
