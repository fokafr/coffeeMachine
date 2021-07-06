package com.business;

public class DrinkDesc {
    public DrinkDesc(String name) {
        this.name = name;
    }

    private String name;
    private double price;
    private boolean isHot;


    public void setHot(boolean hot) {
        isHot = hot;
    }


    public boolean isHot() {
        return isHot;
    }


    public DrinkDesc(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
