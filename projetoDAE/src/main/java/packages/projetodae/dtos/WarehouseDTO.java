package packages.projetodae.dtos;

import packages.projetodae.entities.LogisticsOperator;
import packages.projetodae.entities.Order;

import java.util.LinkedList;
import java.util.List;

public class WarehouseDTO {
    private int id;
    private String name;
    private String location;
    private List<OrderDTO> orders;
    private int logisticsOperator;

    public WarehouseDTO() {

    }

    public WarehouseDTO(int id, String name, String location, int logisticsOperator) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.logisticsOperator = logisticsOperator;
        this.orders = new LinkedList<OrderDTO>();
    }

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

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public int getLogisticsOperator() {
        return logisticsOperator;
    }

    public void setLogisticsOperator(int logisticsOperator) {
        this.logisticsOperator = logisticsOperator;
    }
}
