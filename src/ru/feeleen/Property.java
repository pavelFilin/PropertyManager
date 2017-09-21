package ru.feeleen;

public abstract class Property {

    private double tax;

    private double price;

    private String name;

    public Property(String name, double price){
        this.setName(name);
        this.setPrice(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double calculateTaxe();

    public abstract void setTax(double tax);
}
