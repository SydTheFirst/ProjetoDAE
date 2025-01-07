package com.example.projetodae.ejbs;

import com.example.projetodae.entities.Categoria;
import com.example.projetodae.entities.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String nome, float preco, Categoria categoria) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        entityManager.persist(produto);
    }

    public Produto find(int id) {
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> getAllProdutos() {
        return entityManager.createNamedQuery("getAllProdutos", Produto.class).getResultList();
    }

    public void updateProduto(int id, String nome, float preco, Categoria categoria) {
        Produto produto = find(id);
        if (produto != null) {
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setCategoria(categoria);
            entityManager.merge(produto);
        }
    }

    public void deleteProduto(int id) {
        Produto produto = find(id);
        if (produto != null) {
            entityManager.remove(produto);
        }
    }
}