package com.example.projetodae.utils;

import com.example.projetodae.dtos.EncomendaDTO;
import com.example.projetodae.dtos.RegistoSensorDTO;
import com.example.projetodae.dtos.SensorDTO;
import com.example.projetodae.dtos.UserDTO;
import com.example.projetodae.entities.Encomenda;
import com.example.projetodae.entities.RegistoSensor;
import com.example.projetodae.entities.Sensor;
import com.example.projetodae.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public final class DTOconverter {

    //USERS
    public static UserDTO toDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getTipouser()
        );
    }
    public static List<UserDTO> usersToDTOs(List<User> users){
        return users.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }

    //ENCOMENDAS
    public static EncomendaDTO toDTO(Encomenda encomenda){
        return new EncomendaDTO(
                encomenda.getId(),
                encomenda.getIdCliente(),
                encomenda.getDataPartida(),
                encomenda.getDataChegada(),
                encomenda.getMetodoPagamento(),
                encomenda.getStatus()
        );
    }
    public static List<EncomendaDTO> encomendasToDTOs(List<Encomenda> encomendas){
        return encomendas.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }


    //REGISTOS
    public static RegistoSensorDTO toDTO(RegistoSensor registoSensor){
        return new RegistoSensorDTO(
                registoSensor.getIdSensor(),
                registoSensor.getTimeStamp(),
                registoSensor.getValor()
        );
    }
    public static List<RegistoSensorDTO> registoSensorsToDTOs(List<RegistoSensor> registoSensors){
        return registoSensors.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }


    //SENSORS
    public static SensorDTO toDTO(Sensor sensor){
        return new SensorDTO(
                sensor.getId(),
                sensor.getIdEmbalagem(),
                sensor.getTipoSensor(),
                sensor.isAtivo()
        );
    }
    public static List<SensorDTO> sensorsToDTOs(List<Sensor> sensors){
        return sensors.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }

}
