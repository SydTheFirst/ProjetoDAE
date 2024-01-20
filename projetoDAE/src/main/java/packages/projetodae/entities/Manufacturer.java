package packages.projetodae.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Table(name = "manufacturer")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllManufacturers",
                query = "SELECT m FROM Manufacturer m ORDER BY m.name"
        )
})
public class Manufacturer implements Serializable {
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
        super(username, password, name);
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

    // Outros métodos
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void addLogisticsOperator(LogisticsOperator logisticsOperator) {
        this.logisticsOperators.add(logisticsOperator);
    }
}
