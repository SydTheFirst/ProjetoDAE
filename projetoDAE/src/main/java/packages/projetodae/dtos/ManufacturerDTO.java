package packages.projetodae.dtos;

import packages.projetodae.entities.LogisticsOperator;
import packages.projetodae.entities.Order;

import java.util.LinkedList;
import java.util.List;

public class ManufacturerDTO {
    private String username;
    private String password;
    private String name;
    private List<OrderDTO> orders;
    private List<LogisticsOperatorDTO> logisticsOperators;

    public ManufacturerDTO() {

    }

    public ManufacturerDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.orders = new LinkedList<OrderDTO>();
        this.logisticsOperators = new LinkedList<LogisticsOperatorDTO>();
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

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public List<LogisticsOperatorDTO> getLogisticsOperators() {
        return logisticsOperators;
    }

    public void setLogisticsOperators(List<LogisticsOperatorDTO> logisticsOperators) {
        this.logisticsOperators = logisticsOperators;
    }
}
