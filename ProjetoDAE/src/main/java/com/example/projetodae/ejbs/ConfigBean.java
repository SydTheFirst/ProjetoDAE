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
        try {

            // Users
            userBean.create("admin", "admin123", TipoUser.Admin);
            userBean.create("user1", "password1", TipoUser.Cliente);
            userBean.create("user2", "password2", TipoUser.Cliente);

            // Encomendas
            encomendaBean.create(10, "user1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(3600)), MetodoPagamento.Cartao, Status.Pendente);
            encomendaBean.create(20, "user1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(7200)), MetodoPagamento.ReferenciaMB, Status.Enviada);
            encomendaBean.create(30,"user1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(10800)), MetodoPagamento.Paypal, Status.Entregue);

            // Volumes
            volumeBean.create(1, 1);
            volumeBean.create(2, 2);
            volumeBean.create(3, 3);

            // Embalagens
            embalagemBean.create(1, 1, 1, 10);
            embalagemBean.create(2, 2, 2, 5);
            embalagemBean.create(3, 3, 3, 15);

            // Produtos
            produtoBean.create(100,"Pasta de dentes", 2.0f, Categoria.HIGIENE);
            produtoBean.create(200, "Sabão", 1.5f, Categoria.HIGIENE);
            produtoBean.create(300, "Maçã", 0.8f, Categoria.ALIMENTAR);

            // Sensores
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
