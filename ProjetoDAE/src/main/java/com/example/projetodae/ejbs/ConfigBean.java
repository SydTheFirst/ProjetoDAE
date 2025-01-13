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

            // Populando Users
            userBean.create("admin", "admin123", TipoUser.Admin);
            userBean.create("user1", "password1", TipoUser.Cliente);
            userBean.create("user2", "password2", TipoUser.Cliente);

            // Populando Encomendas
            encomendaBean.create(1, Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(3600)), MetodoPagamento.Cartao, Status.EmEspera);
            encomendaBean.create(2, Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(7200)), MetodoPagamento.ReferenciaMB, Status.EmEntrega);
            encomendaBean.create(3, Timestamp.from(Instant.now()), Timestamp.from(Instant.now().plusSeconds(10800)), MetodoPagamento.Paypal, Status.Entregue);

            // Populando Volumes
            volumeBean.create(1, 1);
            volumeBean.create(2, 2);
            volumeBean.create(3, 3);

            // Populando Embalagens
            embalagemBean.create(1, 1, 1, 10);
            embalagemBean.create(2, 2, 2, 5);
            embalagemBean.create(3, 3, 3, 15);

            // Populando Produtos
            produtoBean.create("Pasta de dentes", 2.0f, Categoria.HIGIENE);
            produtoBean.create("Sabão", 1.5f, Categoria.HIGIENE);
            produtoBean.create("Maçã", 0.8f, Categoria.ALIMENTAR);

            // Populando Sensores
            sensorBean.create(1, TipoSensor.TEMPERATURA, true);
            sensorBean.create(2, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(3, TipoSensor.PRESSAO_ATMOSFERICA, false);

            // Populando Registos de Sensores
            registoSensorBean.create(1, Timestamp.from(Instant.now()), "20 Celsius");
            registoSensorBean.create(2, Timestamp.from(Instant.now()), "39ºN 8ºW");
            registoSensorBean.create(3, Timestamp.from(Instant.now()), "50% Humidity");

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        System.out.println("END populateDB");
    }
}
