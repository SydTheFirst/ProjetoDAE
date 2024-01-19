package packages.projetodae.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "logistics_operator")
@Entity
public class LogisticsOperator {
    @Id
    private String username;
    private String password;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "manufacturer_logisticsoperator",
            joinColumns = @JoinColumn(name = "manufacturer_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "logisticsoperator_id", referencedColumnName = "username")
    )
    private List<Manufacturer> manufacturer;
    @OneToOne(mappedBy = "logisticsOperator", cascade = CascadeType.REMOVE)
    private Warehouse warehouse;
    @OneToMany(mappedBy = "logisticsOperator", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    // Construtor
    public LogisticsOperator(String username, String password, String name, Warehouse warehouse) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.warehouse = warehouse;
        this.manufacturer = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public LogisticsOperator() {
        this.manufacturer = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // Getters e Setters

    public List<Manufacturer> getManufacturer() {
        return manufacturer;
    }

    public void addManufacturer(Manufacturer manufacturer) {
        this.manufacturer.add(manufacturer);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

}
