package com.homework.homework.model;
import jakarta.validation.constraints.*;


public class PVModel {
    @NotEmpty
    @NotNull
    private Double price;

    @NotEmpty
    @NotNull
    private Integer quantity;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
