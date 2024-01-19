package packages.projetodae.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity
public class Product {
    @Id
    private int id;
    private String name;
    private Manufacturer manufacturer;
    private ProductInfo productInfo;

    // Construtor
    public Product(int id, String name, Manufacturer manufacturer, ProductInfo infoDoProduto) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productInfo = infoDoProduto;
    }

    public Product() {
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

    public void setName(String nome) {
        this.name = nome;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
