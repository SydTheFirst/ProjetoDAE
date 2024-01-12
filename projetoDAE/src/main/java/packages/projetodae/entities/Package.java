package packages.projetodae.entities;

import jakarta.persistence.Id;

public class Package {
    @Id
    private int id;
    private String type;
    private String material;
    private String product;
    private String function;

    // Construtor vazio
    public Package() {
        // Inicializa todos os campos como vazios
        this.id = 0;
        this.type = "";
        this.material = "";
        this.product = "";
        this.function = "";
    }

    public Package(int id, String type, String material, String product, String function) {
        this.id = id;
        this.type = type;
        this.material = material;
        this.product = product;
        this.function = function;
    }

    // Getters e Setters para o campo 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters e Setters para o campo 'type'
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters e Setters para o campo 'material'
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    // Getters e Setters para o campo 'product'
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    // Getters e Setters para o campo 'function'
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
