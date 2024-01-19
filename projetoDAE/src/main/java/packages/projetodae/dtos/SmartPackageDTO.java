package packages.projetodae.dtos;

import packages.projetodae.entities.Order;

public class SmartPackageDTO {
    private int id;
    private int parentPackageId;
    private int order;
    private int level;
    private int productId;

    public SmartPackageDTO() {
    }

    public SmartPackageDTO(int id, int parentPackageId, int order, int level, int productId) {
        this.id = id;
        this.parentPackageId = parentPackageId;
        this.order = order;
        this.level = level;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentPackageId() {
        return parentPackageId;
    }

    public void setParentPackageId(int parentPackageId) {
        this.parentPackageId = parentPackageId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
