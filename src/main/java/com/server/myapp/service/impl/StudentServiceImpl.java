package com.server.myapp.service.impl;

import com.server.myapp.domain.Student;
import com.server.myapp.repository.StudentRepository;
import com.server.myapp.service.StudentService;
import com.server.myapp.service.dto.StudentDTO;
import com.server.myapp.service.mapper.StudentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.server.myapp.domain.Student}.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        LOG.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        LOG.debug("Request to update Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public Optional<StudentDTO> partialUpdate(StudentDTO studentDTO) {
        LOG.debug("Request to partially update Student : {}", studentDTO);

        return studentRepository
            .findById(studentDTO.getId())
            .map(existingStudent -> {
                studentMapper.partialUpdate(existingStudent, studentDTO);

                return existingStudent;
            })
            .map(studentRepository::save)
            .map(studentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Students");
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentDTO> findOne(Long id) {
        LOG.debug("Request to get Student : {}", id);
        return studentRepository.findById(id).map(studentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }
}
