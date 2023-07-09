package xyz.lechi.classbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.lechi.classbook.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // Empty
}
