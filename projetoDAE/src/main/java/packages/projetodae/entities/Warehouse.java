package packages.projetodae.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "warehouse")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllWarehouses",
                query = "SELECT w FROM Warehouse w ORDER BY w.name"
        )
})
public class Warehouse implements Serializable {
    @Id
    private int id;
    private String name;
    private String location;
    @ManyToMany(mappedBy = "warehouses")
    private List<Order> orders;
    @OneToOne(mappedBy = "logisticsOperator")
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
