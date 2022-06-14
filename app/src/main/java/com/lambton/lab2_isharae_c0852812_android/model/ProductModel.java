package com.lambton.lab2_isharae_c0852812_android.model;

public class ProductModel {
    Integer productId;
    String productName, productDescription;
    Double productPrice;

    public Integer getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
