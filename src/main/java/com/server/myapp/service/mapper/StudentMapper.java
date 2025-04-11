package com.server.myapp.service.mapper;

import com.server.myapp.domain.Student;
import com.server.myapp.domain.Unit;
import com.server.myapp.domain.User;
import com.server.myapp.service.dto.StudentDTO;
import com.server.myapp.service.dto.UnitDTO;
import com.server.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentDTO}.
 */
@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "unitId")
    StudentDTO toDto(Student s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("unitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UnitDTO toDtoUnitId(Unit unit);
}
