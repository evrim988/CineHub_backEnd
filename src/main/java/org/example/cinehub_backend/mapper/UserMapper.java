package org.example.cinehub_backend.mapper;

import org.example.cinehub_backend.dto.request.UserRegisterRequestDto;
import org.example.cinehub_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromUserRegisterRequestDto(UserRegisterRequestDto userRegisterRequestDto);
}
