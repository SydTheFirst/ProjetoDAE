package com.example.projetodae.ejbs;

import com.example.projetodae.entities.*;
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

    @EJB
    private ProdutoBean produtoBean;

    @EJB
    private UserBean userBean;

    @EJB
    private VolumeBean volumeBean;

    @EJB
    private EncomendaBean encomendaBean;

    @EJB
    private EmbalagemBean embalagemBean;

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB() {
        System.out.println("BEGIN populateDB");
        try {

            // Users
            userBean.create("admin", "admin123", TipoUser.Admin);
            userBean.create("user1", "password1", TipoUser.Cliente);
            userBean.create("user2", "password2", TipoUser.Cliente);

            // Encomendas
            encomendaBean.create(10, "user1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(3600)), MetodoPagamento.Cartao, Status.Pendente);
            encomendaBean.create(20, "user2", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(7200)), MetodoPagamento.ReferenciaMB, Status.Enviada);
            encomendaBean.create(30,"user1", Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(10800)), MetodoPagamento.Paypal, Status.Entregue);

            // Volumes
            volumeBean.create("10-1", 10);
            volumeBean.create("10-2", 10);
            volumeBean.create("10-3", 10);
            volumeBean.create("20-1", 20);
            volumeBean.create("30-1", 30);

            // Embalagens
            embalagemBean.create(1, "10-1", 10, 100, 2);
            embalagemBean.create(2, "10-2", 10, 200, 2);
            embalagemBean.create(3, "10-3", 10, 300, 2);
            embalagemBean.create(4, "20-1", 20, 200, 1);
            embalagemBean.create(5, "30-1", 30, 300, 4);

            // Produtos
            produtoBean.create(100,"Pasta de dentes", 2.0f, Categoria.HIGIENE);
            produtoBean.create(200, "Sabão", 1.5f, Categoria.HIGIENE);
            produtoBean.create(300, "Maçã", 0.8f, Categoria.ALIMENTAR);

            // Sensores
            sensorBean.create(1, TipoSensor.TEMPERATURA, true);
            sensorBean.create(2, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(3, TipoSensor.PRESSAO_ATMOSFERICA, false);

            // Registos de Sensores
            registoSensorBean.create(1, Timestamp.from(Instant.now()), "20 Celsius");
            registoSensorBean.create(2, Timestamp.from(Instant.now()), "39ºN 8ºW");
            registoSensorBean.create(3, Timestamp.from(Instant.now()), "50% Humidity");
            registoSensorBean.create(3, Timestamp.from(Instant.now()), "60% Humidity");
            registoSensorBean.create(3, Timestamp.from(Instant.now()), "70% Humidity");

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        System.out.println("END populateDB");
    }
}
