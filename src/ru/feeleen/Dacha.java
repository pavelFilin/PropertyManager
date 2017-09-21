package ru.feeleen;

public class Dacha extends Property {

    public Dacha(String name, double price) {
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
