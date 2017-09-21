package ru.feeleen;

public class Flat extends Property {
    public Flat(String name, double price){
        super(name, price);
    }

    @Override
    public double calculateTaxe() {
        return 0;
    }

    @Override
    public void setTax(double tax) {
    }

}
