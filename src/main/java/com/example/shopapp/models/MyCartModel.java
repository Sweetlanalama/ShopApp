package com.example.shopapp.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    String productName;
    String productImg;
    String productPrice;
    int totalPrice;
    String totalQuantity;
    String documentId;

    public MyCartModel() {
    }

    public MyCartModel(String productName, String productImg, String productPrice, int totalPrice, String totalQuantity) {
        this.productName = productName;
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
