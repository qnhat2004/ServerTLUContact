package com.server.myapp.service.mapper;

import com.server.myapp.domain.Unit;
import com.server.myapp.service.dto.UnitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Unit} and its DTO {@link UnitDTO}.
 */
@Mapper(componentModel = "spring")
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {
    @Mapping(target = "parentUnit", source = "parentUnit", qualifiedByName = "unitId")
    UnitDTO toDto(Unit s);

    @Named("unitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UnitDTO toDtoUnitId(Unit unit);
}
