package com.booleanuk.library.DTO.mapper;

import com.booleanuk.library.DTO.UserDTO;
import com.booleanuk.library.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "rentedVideoGames", ignore = true)
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}