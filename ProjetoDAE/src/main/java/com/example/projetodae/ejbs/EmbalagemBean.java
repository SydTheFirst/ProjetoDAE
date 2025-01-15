package com.example.projetodae.ejbs;

import com.example.projetodae.entities.Embalagem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EmbalagemBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(int id, String idVolume, int idEncomenda, int idProduto, int quantidade) {
        Embalagem embalagem = new Embalagem(id, idVolume, idEncomenda, idProduto, quantidade);
        entityManager.persist(embalagem);
    }

    public Embalagem find(int id) {
        return entityManager.find(Embalagem.class, id);
    }

    public List<Embalagem> getAllEmbalagens() {
        return entityManager.createNamedQuery("getAllEmbalagens", Embalagem.class).getResultList();
    }

    public List<Embalagem> getEmbalagensByEncomenda(int idEncomenda) {
        Query query = entityManager.createNamedQuery("getEmbalagensByEncomenda", Embalagem.class);
        query.setParameter("idEncomenda", idEncomenda);
        return query.getResultList();
    }

    public List<Embalagem> getEmbalagensByProduto(int idProduto) {
        Query query = entityManager.createNamedQuery("getEmbalagensByProduto", Embalagem.class);
        query.setParameter("idProduto", idProduto);
        return query.getResultList();
    }

    public void updateEmbalagem(int id, int quantidade) {
        Embalagem embalagem = find(id);
        if (embalagem != null) {
            embalagem.setQuantidade(quantidade);
            entityManager.merge(embalagem);
        }
    }

    public void deleteEmbalagem(int id) {
        Embalagem embalagem = find(id);
        if (embalagem != null) {
            entityManager.remove(embalagem);
        }
    }
}