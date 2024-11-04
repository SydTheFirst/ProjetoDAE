package com.example.projetodae.utils;

public final class DTOconverter {
    public static UserDTO toDTONoSubjects(User user) {
        return new UserDTO(
                user.getid()
                user.getUsername(),
                user.getPassword(),
                user.tipouser()
        );
    }

    public static List<UserDTO> usersToDTOs(List<User> users) {
        return users.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }

}