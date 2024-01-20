package packages.projetodae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Table(name = "order")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllOrders",
                query = "SELECT o FROM Order o ORDER BY o.id"
        )
})
public class Order implements Serializable {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "end_customer_id")
    @NotNull
    private EndCustomer endCustomer;
    @ManyToOne
    @JoinColumn(name = "logistics_operator_id")
    private LogisticsOperator logisticsOperator;
    @OneToOne(mappedBy = "order", cascade = CascadeType.REMOVE)
    private SmartPackage smartPackage;
    @ManyToMany(mappedBy = "orders", cascade = CascadeType.DETACH)
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    @ManyToMany
    @JoinTable(
            name = "order_warehouse",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id", referencedColumnName = "id"))
    private List<Warehouse> warehouses;
    private String state;

    // Construtores

    public Order(int id, EndCustomer endCustomer, LogisticsOperator logisticsOperator, String state, SmartPackage smartPackage) {
        this.id = id;
        this.endCustomer = endCustomer;
        this.logisticsOperator = logisticsOperator;
        this.state = state;
        this.smartPackage = smartPackage;
        this.products = new ArrayList<>();
        this.warehouses = new ArrayList<>();
    }

    public Order() {
        this.products = new ArrayList<>();
        this.warehouses = new ArrayList<>();
    }
    //Getters e Setter

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EndCustomer getEndCustomer() {
        return endCustomer;
    }

    public void setEndCustomer(EndCustomer consumidorFinalId) {
        this.endCustomer = consumidorFinalId;
    }

    public LogisticsOperator getLogisticsOperator() {
        return logisticsOperator;
    }

    public SmartPackage getSmartPackage() {
        return smartPackage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getState() {
        return state;
    }

    public void setLogisticsOperator(LogisticsOperator logisticsOperator) {
        this.logisticsOperator = logisticsOperator;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSmartPackage(SmartPackage smartPackages) {
        this.smartPackage = smartPackages;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}