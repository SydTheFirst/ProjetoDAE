package packages.projetodae.dtos;

public class OrderDTO {
    public int id;
    public int endCustomerId;
    public int logisticsOperatorId;
    public String state;
    public int smartPackageId;
    public int manufacturerId;

    public OrderDTO() {
    }

    public OrderDTO(int id, int endCustomerId, int logisticsOperatorId, String state, int smartPackageId, int manufacturerId) {
        this.id = id;
        this.endCustomerId = endCustomerId;
        this.logisticsOperatorId = logisticsOperatorId;
        this.state = state;
        this.smartPackageId = smartPackageId;
        this.manufacturerId = manufacturerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEndCustomerId() {
        return endCustomerId;
    }

    public void setEndCustomerId(int endCustomerId) {
        this.endCustomerId = endCustomerId;
    }

    public int getLogisticsOperatorId() {
        return logisticsOperatorId;
    }

    public void setLogisticsOperatorId(int logisticsOperatorId) {
        this.logisticsOperatorId = logisticsOperatorId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSmartPackageId() {
        return smartPackageId;
    }

    public void setSmartPackageId(int smartPackageId) {
        this.smartPackageId = smartPackageId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
