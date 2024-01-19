package packages.projetodae.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
@Table(name = "manufacturer")
@Entity
public class Manufacturer {
    @Id
    private String username;
    private String password;
    private String name;
    private List<Order> orders;

    private List<LogisticsOperator> logisticsOperators;

    private List<EndCustomer> endCustomers;

    private List<Warehouse> warehouses;

    // Construtores
    public Manufacturer(int id, String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.orders = new ArrayList<>();
        this.logisticsOperators = new ArrayList<>();
        this.endCustomers = new ArrayList<>();
    }

    public Manufacturer() {
        this.orders = new ArrayList<>();
        this.logisticsOperators = new ArrayList<>();
        this.endCustomers = new ArrayList<>();
    }

    // Getters e Setters


    public List<Order> getOrders() {
        return orders;
    }

    public List<LogisticsOperator> getLogisticsOperators() {
        return logisticsOperators;
    }

    public List<EndCustomer> getEndCustomers() {
        return endCustomers;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
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

    public void addEndCustomer(EndCustomer endCustomer) {
        this.endCustomers.add(endCustomer);
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }
}
