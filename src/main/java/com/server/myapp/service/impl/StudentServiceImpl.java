package com.server.myapp.service.impl;

import com.server.myapp.domain.Student;
import com.server.myapp.repository.StudentRepository;
import com.server.myapp.service.StudentService;
import com.server.myapp.service.dto.StudentDTO;
import com.server.myapp.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public Optional<StudentDTO> findOneByUserId(Long id) {
        LOG.debug("Request to get Student by userId : {}", id);
        return studentRepository.findByUserId(id).map(studentMapper::toDto);
    }

    @Override
    public Optional<StudentDTO> updateByUserId(Long id, StudentDTO studentDTO) {
        LOG.debug("Request to update Student by userId : {}", id);

        return studentRepository
            .findByUserId(id)
            .map(existingStudent -> {
                studentMapper.partialUpdate(existingStudent, studentDTO);

                return existingStudent;
            })
            .map(studentRepository::save)
            .map(studentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> findAllByUnitId() {
        // Lấy thông tin user hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

//        // Tìm thông tin sinh viên hoặc giảng viên dựa trên email (hoặc user_id)
//        Optional<Student> currentStudent = studentRepository.findByEmail(currentUserEmail);
//
//        if (currentStudent.isPresent()) {
//            Long unitId = currentStudent.get().getUnit().getId(); // Lấy unit_id từ sinh viên
//            return studentRepository.findAllByUnitId(unitId)
//                .stream()
//                .map(studentMapper::toDto)
//                .toList();
//        } else {
//            throw new IllegalStateException("User is not associated with any student or unit.");
//        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            // Nếu người dùng là sinh viên, lấy thông tin sinh viên
            Optional<Student> currentStudent = studentRepository.findByEmail(currentUserEmail);
            if (currentStudent.isPresent()) {
                Long unitId = currentStudent.get().getUnit().getId(); // Lấy unit_id từ sinh viên
                return studentRepository.findAllByUnitId(unitId)
                    .stream()
                    .map(studentMapper::toDto)
                    .toList();
            } else {
                throw new IllegalStateException("User is not associated with any student or unit.");
            }
        } else {
            // Nếu người dùng không phải là sinh viên, trả về danh sách tất cả sinh viên
            return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
        }
    }
}
