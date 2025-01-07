package com.example.projetodae.utils;

import com.example.projetodae.dtos.UserDTO;
import com.example.projetodae.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public final class DTOconverter {

    public static UserDTO toDTONoSubjects(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getTipouser()
        );
    }

    /*
    public static List<UserDTO> usersToDTOs(List<User> users){
        return users.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }
     */



}
