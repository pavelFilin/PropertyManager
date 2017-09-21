package ru.feeleen;

public class LandPlot extends Property {
    public LandPlot(String name, double price) {
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
