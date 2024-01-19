package packages.projetodae.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Table(name = "warehouse")
@Entity
public class Warehouse {
    @Id
    private int id;
    private String name;
    private String location;

    private List<Order> orders;
    private LogisticsOperator logisticsOperator;


    // Construtores
    public Warehouse(int id, String name, String location, LogisticsOperator logisticsOperator) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.logisticsOperator = logisticsOperator;
        this.orders = new ArrayList<>();
    }

    public Warehouse() {
        this.orders = new ArrayList<>();
    }

    // Outros métodos
    public void addOrder(Order order) {
        this.orders.add(order);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LogisticsOperator getLogisticsOperator() {
        return logisticsOperator;
    }

    public void setLogisticsOperator(LogisticsOperator logisticsOperator) {
        this.logisticsOperator = logisticsOperator;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
