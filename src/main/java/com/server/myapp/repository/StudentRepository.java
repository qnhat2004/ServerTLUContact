package com.server.myapp.repository;

import com.server.myapp.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Student entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE " +
        "LOWER(s.fullName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
        "LOWER(s.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
        "LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
        "LOWER(s.address) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Student> findBySearchQuery(@Param("search") String search, Pageable pageable);
}
