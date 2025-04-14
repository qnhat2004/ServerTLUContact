package com.server.myapp.service;

import com.server.myapp.service.dto.StudentDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.server.myapp.domain.Student}.
 */
public interface StudentService {
    /**
     * Save a student.
     *
     * @param studentDTO the entity to save.
     * @return the persisted entity.
     */
    StudentDTO save(StudentDTO studentDTO);

    /**
     * Updates a student.
     *
     * @param studentDTO the entity to update.
     * @return the persisted entity.
     */
    StudentDTO update(StudentDTO studentDTO);

    /**
     * Partially updates a student.
     *
     * @param studentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StudentDTO> partialUpdate(StudentDTO studentDTO);

    /**
     * Get all the students.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StudentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" student.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StudentDTO> findOne(Long id);

    /**
     * Delete the "id" student.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<StudentDTO> findOneByUserId(Long id);

    // Update student by user id
    Optional<StudentDTO> updateByUserId(Long id, StudentDTO studentDTO);

    // Get all student by unit id
    List<StudentDTO> findAllByUnitId();
}
