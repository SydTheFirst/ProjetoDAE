package packages.projetodae.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Table(name = "manufacturer")
@Entity
public class Manufacturer {
    @Id
    private String username;
    private String password;
    private String name;
    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE)
    private List<Order> orders;
    @ManyToMany(mappedBy = "manufacturers")
    private List<LogisticsOperator> logisticsOperators;

    // Construtores
    public Manufacturer(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.orders = new ArrayList<>();
        this.logisticsOperators = new ArrayList<>();
    }

    public Manufacturer() {
        this.orders = new ArrayList<>();
        this.logisticsOperators = new ArrayList<>();
    }

    // Getters e Setters


    public List<Order> getOrders() {
        return orders;
    }

    public List<LogisticsOperator> getLogisticsOperators() {
        return logisticsOperators;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    // Outros métodos
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addLogisticsOperator(LogisticsOperator logisticsOperator) {
        this.logisticsOperators.add(logisticsOperator);
    }
}
