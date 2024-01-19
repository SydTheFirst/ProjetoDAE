package packages.projetodae.dtos;

import packages.projetodae.entities.Product;

public class ProductInfoDTO {
    private int id;
    private float temperature;
    private int humidity;
    private boolean openedPackage;
    private String location;
    private float pressure;
    private int product;

    public ProductInfoDTO(int id, float temperature, int humidity, boolean openedPackage, String location, float pressure, int product) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.openedPackage = openedPackage;
        this.location = location;
        this.pressure = pressure;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isOpenedPackage() {
        return openedPackage;
    }

    public void setOpenedPackage(boolean openedPackage) {
        this.openedPackage = openedPackage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
