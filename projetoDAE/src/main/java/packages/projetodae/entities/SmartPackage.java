package packages.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "smart_package")
@Entity
public class SmartPackage {
    @Id
    private int id;
    private int parentPackageId;
    @OneToOne(mappedBy = "order", cascade = CascadeType.REMOVE)
    private Order order;
    private int level;
    private int productId;

    // Construtor vazio
    public SmartPackage() {
    }

    public SmartPackage(int id, int parentPackageId, Order order, int level, int produtoId) {
        this.id = id;
        this.parentPackageId = parentPackageId;
        this.order = order;
        this.level = level;
        this.productId = produtoId;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentPackageId() {
        return parentPackageId;
    }

    public void setParentPackageId(int embalagemPaiId) {
        this.parentPackageId = embalagemPaiId;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int nivel) {
        this.level = nivel;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int produtoId) {
        this.productId = produtoId;
    }
}
