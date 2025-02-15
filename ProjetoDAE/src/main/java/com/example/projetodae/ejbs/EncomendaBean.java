package com.example.projetodae.ejbs;

import com.example.projetodae.entities.Encomenda;
import com.example.projetodae.entities.MetodoPagamento;
import com.example.projetodae.entities.Status;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Timestamp;
import java.util.List;

@Stateless
public class EncomendaBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(int id, String cliente, Timestamp dataPartida, Timestamp dataChegada, MetodoPagamento metodoPagamento, Status status) {
        Encomenda encomenda = new Encomenda();
        encomenda.setId(id);
        encomenda.setCliente(cliente);
        encomenda.setDataPartida(dataPartida);
        encomenda.setDataChegada(dataChegada);
        encomenda.setMetodoPagamento(metodoPagamento);
        encomenda.setStatus(status);

        entityManager.persist(encomenda);
    }

    public Encomenda find(int id) {
        return entityManager.find(Encomenda.class, id);
    }

    public List<Encomenda> getAllEncomendas() {
        return entityManager.createNamedQuery("getAllEncomendas", Encomenda.class).getResultList();
    }

    //get encomendas by cliente
    public List<Encomenda> getEncomendasByCliente(String cliente) {
        return entityManager.createNamedQuery("getEncomendasByCliente", Encomenda.class).setParameter("cliente", cliente).getResultList();
    }

    public void updateEncomenda(int id,String cliente, Timestamp dataPartida, Timestamp dataChegada, MetodoPagamento metodoPagamento, Status status) {
        Encomenda encomenda = find(id);
        if (encomenda != null) {
            encomenda.setCliente(cliente);
            encomenda.setDataPartida(dataPartida);
            encomenda.setDataChegada(dataChegada);
            encomenda.setMetodoPagamento(metodoPagamento);
            encomenda.setStatus(status);
            entityManager.merge(encomenda);
        }
    }

    public boolean deleteEncomenda(int id) {
        Encomenda encomenda = find(id);
        if (encomenda != null) {
            entityManager.remove(encomenda);
        }
        return entityManager.find(Encomenda.class, id) == null;
    }
}