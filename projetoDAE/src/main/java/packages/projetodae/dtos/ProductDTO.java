package packages.projetodae.dtos;

import packages.projetodae.entities.Order;
import packages.projetodae.entities.ProductInfo;

import java.util.LinkedList;
import java.util.List;

public class ProductDTO {
    private int id;
    private String name;
    private int productInfo;
    private List<OrderDTO> orders;

    public ProductDTO() {

    }

    public ProductDTO(int id, String name, int productInfo) {
        this.id = id;
        this.name = name;
        this.productInfo = productInfo;
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

    public int getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(int productInfo) {
        this.productInfo = productInfo;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
