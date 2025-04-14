package com.server.myapp.repository;

import com.server.myapp.domain.Student;
import com.server.myapp.service.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Student entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    // Find student by user id
    @Query(nativeQuery = true, value = "SELECT * FROM student s WHERE s.user_id = ?1")
    Optional<Student> findByUserId(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM student s WHERE s.unit_id = :id")
    List<Student> findAllByUnitId(Long id);
}
