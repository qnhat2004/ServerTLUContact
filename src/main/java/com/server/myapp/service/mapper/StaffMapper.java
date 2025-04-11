package com.server.myapp.service.mapper;

import com.server.myapp.domain.Staff;
import com.server.myapp.domain.Unit;
import com.server.myapp.domain.User;
import com.server.myapp.service.dto.StaffDTO;
import com.server.myapp.service.dto.UnitDTO;
import com.server.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Staff} and its DTO {@link StaffDTO}.
 */
@Mapper(componentModel = "spring")
public interface StaffMapper extends EntityMapper<StaffDTO, Staff> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "unitId")
    StaffDTO toDto(Staff s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("unitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UnitDTO toDtoUnitId(Unit unit);
}
