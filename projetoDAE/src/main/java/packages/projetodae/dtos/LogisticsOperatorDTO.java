package packages.projetodae.dtos;

import packages.projetodae.entities.Manufacturer;
import packages.projetodae.entities.Order;
import packages.projetodae.entities.Warehouse;

import java.util.LinkedList;
import java.util.List;

public class LogisticsOperatorDTO {

        private String username;

        private String password;

        private String name;

        private List<ManufacturerDTO> manufacturer;

        private int warehouse;

        private List<OrderDTO> orders;

        public LogisticsOperatorDTO() {

        }

        public LogisticsOperatorDTO(String username, String password, String name, int warehouse) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.warehouse = warehouse;
            this.manufacturer = new LinkedList<ManufacturerDTO>();
            this.orders = new LinkedList<OrderDTO>();
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

    public List<ManufacturerDTO> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(List<ManufacturerDTO> manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(int warehouse) {
        this.warehouse = warehouse;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
