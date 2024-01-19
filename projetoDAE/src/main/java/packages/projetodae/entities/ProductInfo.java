package packages.projetodae.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "product_info")
@Entity
public class ProductInfo {
    @Id
    private int id;
    private float temperature;
    private int humidity;
    private boolean openedPackage;
    private String location;
    private float pressure;

    // Construtor
    public ProductInfo(int id, float temperature, int humidity, boolean OpenedPackage, String location, float pressure) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.openedPackage = OpenedPackage;
        this.location = location;
        this.pressure = pressure;
    }

    public ProductInfo() {
    }

    // Getters
    public int getId() {
        return id;
    }
    public float getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public boolean isOpenedPackage() {
        return openedPackage;
    }

    public String getLocation() {
        return location;
    }

    public float getPressure() {
        return pressure;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }
    public void setTemperature(float temperatura) {
        this.temperature = temperatura;
    }

    public void setHumidity(int humidade) {
        this.humidity = humidade;
    }

    public void setOpenedPackage(boolean embalagemAberta) {
        this.openedPackage = embalagemAberta;
    }

    public void setLocation(String localizacao) {
        this.location = localizacao;
    }

    public void setPressure(float pressao) {
        this.pressure = pressao;
    }
}
