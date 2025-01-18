package com.example.projetodae.ejbs;

import com.example.projetodae.entities.*;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import static java.sql.Types.NULL;

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

    LocalDateTime fixedDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0);
    Timestamp fixedTimestamp = Timestamp.valueOf(fixedDateTime);

    @PostConstruct
    public void populateDB() {
        System.out.println("BEGIN populateDB");
        try {

            // Users
            userBean.create("admin", "admin123", TipoUser.Admin);
            userBean.create("joaosilva", "joao123", TipoUser.Cliente);
            userBean.create("mariaoliveira", "maria123", TipoUser.Cliente);
            userBean.create("pedroribeiro", "pedro123", TipoUser.Cliente);
            userBean.create("anacarvalho", "ana123", TipoUser.Cliente);
            userBean.create("carlosalmeida", "carlos123", TipoUser.Cliente);
            userBean.create("pedrotrindade", "pedro123", TipoUser.Logistica);

            // Encomendas
            encomendaBean.create(1, "joaosilva", fixedTimestamp, fixedTimestamp, MetodoPagamento.Cartao, Status.Pendente);

            encomendaBean.create(101, "joaosilva", fixedTimestamp, fixedTimestamp, MetodoPagamento.Cartao, Status.Pendente);
            encomendaBean.create(102, "joaosilva", Timestamp.valueOf(LocalDateTime.now().minusDays(2)), fixedTimestamp, MetodoPagamento.Paypal, Status.Enviada);

            encomendaBean.create(201, "mariaoliveira", Timestamp.valueOf(LocalDateTime.now().minusDays(3)), Timestamp.valueOf(LocalDateTime.now()), MetodoPagamento.ReferenciaMB, Status.Entregue);
            encomendaBean.create(202, "mariaoliveira", fixedTimestamp, fixedTimestamp, MetodoPagamento.Cartao, Status.Pendente);

            encomendaBean.create(301, "pedroribeiro", fixedTimestamp, fixedTimestamp, MetodoPagamento.Paypal, Status.Pendente);
            encomendaBean.create(302, "pedroribeiro", Timestamp.valueOf(LocalDateTime.now().minusDays(4)), Timestamp.valueOf(LocalDateTime.now().minusDays(1)), MetodoPagamento.Cartao, Status.Entregue);

            encomendaBean.create(401, "anacarvalho", Timestamp.valueOf(LocalDateTime.now()), fixedTimestamp, MetodoPagamento.Cartao, Status.Enviada);
            encomendaBean.create(402, "anacarvalho", Timestamp.valueOf(LocalDateTime.now().minusDays(5)), Timestamp.valueOf(LocalDateTime.now()), MetodoPagamento.Paypal, Status.Entregue);

            encomendaBean.create(501, "carlosalmeida", fixedTimestamp, fixedTimestamp, MetodoPagamento.ReferenciaMB, Status.Pendente);
            encomendaBean.create(502, "carlosalmeida", Timestamp.valueOf(LocalDateTime.now().minusDays(3)), fixedTimestamp, MetodoPagamento.Cartao, Status.Enviada);

            // Volumes
            volumeBean.create("V101-1", 101);
            volumeBean.create("V101-2", 101);
            volumeBean.create("V102-1", 102);
            volumeBean.create("V102-2", 102);
            volumeBean.create("V201-1", 201);
            volumeBean.create("V202-1", 202);
            volumeBean.create("V301-1", 301);
            volumeBean.create("V302-1", 302);
            volumeBean.create("V401-1", 401);
            volumeBean.create("V402-1", 402);
            volumeBean.create("V501-1", 501);
            volumeBean.create("V502-1", 502);

            // Embalagens
            embalagemBean.create(1, "V101-1", 101, 1, 3);
            embalagemBean.create(2, "V101-2", 101, 2, 2);
            embalagemBean.create(3, "V102-1", 102, 3, 5);
            embalagemBean.create(4, "V102-2", 102, 4, 4);
            embalagemBean.create(5, "V201-1", 201, 5, 2);
            embalagemBean.create(6, "V202-1", 202, 6, 1);
            embalagemBean.create(7, "V301-1", 301, 7, 3);
            embalagemBean.create(8, "V302-1", 302, 8, 5);
            embalagemBean.create(9, "V401-1", 401, 9, 6);
            embalagemBean.create(10, "V402-1", 402, 10, 2);
            embalagemBean.create(11, "V501-1", 501, 2, 4);
            embalagemBean.create(12, "V502-1", 502, 3, 3);

            // Produtos
            produtoBean.create(1, "Maçã", 0.5f, Categoria.ALIMENTAR);
            produtoBean.create(2, "Chãmpo", 4.99f, Categoria.HIGIENE);
            produtoBean.create(3, "Pasta de dentes", 2.5f, Categoria.HIGIENE);
            produtoBean.create(4, "Arroz", 1.99f, Categoria.ALIMENTAR);
            produtoBean.create(5, "Camisola", 15.0f, Categoria.ROUPA);
            produtoBean.create(6, "Ração para gato", 12.0f, Categoria.ANIMAL);
            produtoBean.create(7, "Teclado", 25.0f, Categoria.TECNOLOGIA);
            produtoBean.create(8, "Livro", 10.0f, Categoria.LAZER);
            produtoBean.create(9, "Bola de futebol", 20.0f, Categoria.LAZER);
            produtoBean.create(10, "Detergente", 3.75f, Categoria.OUTRA);

            // Sensores
            sensorBean.create(1, TipoSensor.TEMPERATURA, true);
            sensorBean.create(1, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(2, TipoSensor.TEMPERATURA, true);
            sensorBean.create(2, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(3, TipoSensor.TEMPERATURA, true);
            sensorBean.create(3, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(4, TipoSensor.TEMPERATURA, true);
            sensorBean.create(4, TipoSensor.LOCALIZACAO, true);
            sensorBean.create(NULL, TipoSensor.LOCALIZACAO, false);
            sensorBean.create(NULL, TipoSensor.TEMPERATURA, false);
            sensorBean.create(NULL, TipoSensor.ACELERACAO, false);

            // Registros de Sensores
            registoSensorBean.create(1, Timestamp.from(Instant.now()), "22°C");

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        System.out.println("END populateDB");
    }
}
