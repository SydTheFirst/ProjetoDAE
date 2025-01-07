package com.example.projetodae.ejbs;

import com.example.projetodae.entities.TipoSensor;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {

    @EJB
    private SensorBean sensorBean;
    @EJB
    private RegistoSensorBean registoSensorBean;
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB(){
        System.out.println("BEGIN populateDB");
        try{
            sensorBean.create(1, TipoSensor.TEMPERATURA, true);
            sensorBean.create(1, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(2, TipoSensor.TEMPERATURA, false);


            registoSensorBean.create(1, Timestamp.from(Instant.now()), "20 Celsius");
            registoSensorBean.create(2, Timestamp.from(Instant.now()), "39ºN 8ºW");

        }catch (Exception e){
            logger.severe(e.getMessage());
        }

    }




}
