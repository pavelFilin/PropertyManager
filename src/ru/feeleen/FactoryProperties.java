package ru.feeleen;

public class FactoryProperties {
    private static FactoryProperties instance;

    private FactoryProperties() { }
    public static FactoryProperties getInstance() {
        if (instance == null) {
            instance = new FactoryProperties();
        }
        return instance;
    }

    public static Flat getFlat(String name, double price){
        return new Flat(name, price);
    }
    public static Home getHome(String name, double price){
        return new Home(name, price);
    }
    public static Dacha getDacha(String name, double price){
        return new Dacha(name, price);
    }
    public static LandPlot getLandPlot(String name, double price){
        return new LandPlot(name, price);
    }

}