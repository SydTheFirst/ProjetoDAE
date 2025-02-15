package com.example.projetodae.utils;

import com.example.projetodae.dtos.*;
import com.example.projetodae.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public final class DTOconverter {

    //USERS
    public static UserDTO toDTO(User user){
        return new UserDTO(
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
                encomenda.getCliente(),
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
                registoSensor.getId(),
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


    //VOLUMES
    public static VolumeDTO toDTO(Volume volume){
        return new VolumeDTO(
                volume.getId(),
                volume.getIdEncomenda()
        );
    }

    public static List<VolumeDTO> volumesToDTOs(List<Volume> volumes){
        return volumes.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }



    //EMBALAGENS

    public static EmbalagemDTO toDTO(Embalagem embalagem){
        return new EmbalagemDTO(
                embalagem.getId(),
                embalagem.getIdVolume(),
                embalagem.getIdEncomenda(),
                embalagem.getIdProduto(),
                embalagem.getQuantidade()
        );
    }
    public static List<EmbalagemDTO> embalagensDTOs(List<Embalagem> embalagens){
        return embalagens.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }

    //PRODUTOS
    public static ProdutoDTO toDTO(Produto produto){
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria()
        );
    }
    public static List<ProdutoDTO> produtosToDTOs(List<Produto> produtos){
        return produtos.stream().map(DTOconverter::toDTO).collect(Collectors.toList());
    }
}
