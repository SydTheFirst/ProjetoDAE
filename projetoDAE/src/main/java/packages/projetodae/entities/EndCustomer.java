package packages.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
@Table(name = "end_customer")
@Entity
public class EndCustomer extends User {
    @NotBlank
    private String address;
    @Email
    @NotBlank
    private String email;
    @OneToMany(mappedBy = "endCustomer", cascade = CascadeType.REMOVE)
    private List<Order> ordersId;

    // Construtores
    public EndCustomer(String username, String password, String name, String address, String email) {
        super(username, password, name);
        this.address = address;
        this.email = email;
        this.ordersId = new ArrayList<>();
    }

    public EndCustomer() {
        this.ordersId = new ArrayList<>();
    }
    // Getters e Setters

    public String getAddress() {
        return address;
    }

    public void setAddress(String morada) {
        this.address = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrdersId() {
        return ordersId;
    }

    public void addEncomenda(Order encomendaId) {
        this.ordersId.add(encomendaId);
    }
}
