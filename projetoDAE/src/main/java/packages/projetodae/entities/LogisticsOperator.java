package packages.projetodae.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Table(name = "logistics_operator")
@Entity
public class LogisticsOperator {
    @Id
    private String username;
    private String password;
    private String name;
    private List<Manufacturer> manufacturer;

    // Construtor
    public LogisticsOperator(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.manufacturer = new ArrayList<>();
    }

    public LogisticsOperator() {
        this.manufacturer = new ArrayList<>();
    }

    // Getters e Setters

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

}
