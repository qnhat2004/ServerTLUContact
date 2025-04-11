package com.server.myapp.service.impl;

import com.server.myapp.domain.Staff;
import com.server.myapp.repository.StaffRepository;
import com.server.myapp.service.StaffService;
import com.server.myapp.service.dto.StaffDTO;
import com.server.myapp.service.mapper.StaffMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.server.myapp.domain.Staff}.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private static final Logger LOG = LoggerFactory.getLogger(StaffServiceImpl.class);

    private final StaffRepository staffRepository;

    private final StaffMapper staffMapper;

    public StaffServiceImpl(StaffRepository staffRepository, StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    public StaffDTO save(StaffDTO staffDTO) {
        LOG.debug("Request to save Staff : {}", staffDTO);
        Staff staff = staffMapper.toEntity(staffDTO);
        staff = staffRepository.save(staff);
        return staffMapper.toDto(staff);
    }

    @Override
    public StaffDTO update(StaffDTO staffDTO) {
        LOG.debug("Request to update Staff : {}", staffDTO);
        Staff staff = staffMapper.toEntity(staffDTO);
        staff = staffRepository.save(staff);
        return staffMapper.toDto(staff);
    }

    @Override
    public Optional<StaffDTO> partialUpdate(StaffDTO staffDTO) {
        LOG.debug("Request to partially update Staff : {}", staffDTO);

        return staffRepository
            .findById(staffDTO.getId())
            .map(existingStaff -> {
                staffMapper.partialUpdate(existingStaff, staffDTO);

                return existingStaff;
            })
            .map(staffRepository::save)
            .map(staffMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StaffDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Staff");
        return staffRepository.findAll(pageable).map(staffMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StaffDTO> findOne(Long id) {
        LOG.debug("Request to get Staff : {}", id);
        return staffRepository.findById(id).map(staffMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Staff : {}", id);
        staffRepository.deleteById(id);
    }
}
