package packages.projetodae.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "product")
@Entity
public class Product {
    @Id
    private int id;
    private String name;
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private ProductInfo productInfo;
    @ManyToMany
    @JoinTable(
            name = "product_order",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> orders;

    // Construtor
    public Product(int id, String name, ProductInfo infoDoProduto) {
        this.id = id;
        this.name = name;
        this.productInfo = infoDoProduto;
        this.orders = new ArrayList<>();
    }

    public Product() {
        this.orders = new ArrayList<>();
    }
    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
