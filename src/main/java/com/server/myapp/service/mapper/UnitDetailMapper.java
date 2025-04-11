package com.server.myapp.service.mapper;

import com.server.myapp.domain.Unit;
import com.server.myapp.service.dto.UnitDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface UnitDetailMapper {

    @Mapping(target = "parentUnitId", source = "parentUnit.id") // Map parentUnit's id
    @Mapping(target = "childUnitIds", ignore = true) // Ignore child units, handled in service
    UnitDetailDTO toDto(Unit unit);

    // Custom mapping method to handle Unit to Long mapping
    default Long map(Unit parentUnit) {
        return parentUnit != null ? parentUnit.getId() : null;
    }
}
