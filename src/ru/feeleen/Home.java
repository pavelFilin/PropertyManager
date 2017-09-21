package ru.feeleen;

public class Home extends Property {
    public Home(String name, double price) {
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
