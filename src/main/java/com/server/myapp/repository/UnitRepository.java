package com.server.myapp.repository;

import com.server.myapp.domain.Unit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Unit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    // Get all child units of a parent unit
    @Query("SELECT u.id FROM Unit u WHERE u.parentUnit.id = ?1")
    List<Long> findAllByParentUnitId(Long parentUnitId);
}
