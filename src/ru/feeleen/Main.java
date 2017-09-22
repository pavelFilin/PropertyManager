package ru.feeleen;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        Property.setTax(.13);
        List<Property> properties = new ArrayList<>();
        properties.addAll(getPropertiesFromFile());
        Menu menu = Menu.Add;
        System.out.println("Properties manager v1.0");
        try(Scanner scn = new Scanner(System.in)) {
            do {
                showMenu();
                try {
                    menu = Menu.values()[choose(scn)-1];
                    switch (menu){
                        case Add: addNewProperty(scn, properties); break;
                        case SetTax: setTax(scn); break;
                        case Print: printAll(properties); break;
                        case PrintClass: printClass(properties, scn); break;
                        case Save: save(properties); break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error entry \nTry again");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBounds i = " + (Integer.parseInt(e.getMessage())+1));
                    System.out.println("Bounds of 1... " + Menu.values().length);
                    System.out.println("Try again or enter 5 to exit from application");
                } catch (Exception e) {
                    System.out.println(e);
                    e.getStackTrace();
                    System.err.println("Application finished crush");
                    menu=Menu.Exit;
                }
            } while (menu!=Menu.Exit);
            System.out.println("Goodbye");
        }
    }

    public static void printAll(List<Property> properties){
        if (properties.size()==0) {
            System.out.println("them don't exist");
        } else {
            for (Property item : properties) {
                System.out.println("name = " + item.getName() + "; price = " + item.getPrice() + "; nalog = " + item.calculateTaxe());
            }
            System.out.println();
        }
    }

    public static List getPropertiesFromFile() throws IOException{
        List<Property> properties = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("test.txt"))){
            String tempString = reader.readLine();
            if (tempString!=null) {
                while (tempString!=null) {
                String[] tempStrings = tempString.split(";");
                Properties prop = Properties.valueOf(tempStrings[0]);
                switch (prop) {
                    case Flat: {
                        properties.add(FactoryProperties.getFlat(tempStrings[1], Double.parseDouble(tempStrings[2])));
                    }

                    break;
                    case Home: {
                        properties.add(FactoryProperties.getHome(tempStrings[1], Double.parseDouble(tempStrings[2])));
                    }
                    break;
                    case Dacha: {
                        properties.add(FactoryProperties.getDacha(tempStrings[1], Double.parseDouble(tempStrings[2])));
                    }
                    break;
                    case LandPlot: {
                        properties.add(FactoryProperties.getDacha(tempStrings[1], Double.parseDouble(tempStrings[2])));
                    }
                    break;
                }
                tempString = reader.readLine();
            }
            }
        }
        return properties;
    }

    public static void showMenu(){
        System.out.println("Enter "+ (Menu.Add.ordinal()+1) + " to Add properties");
        System.out.println("Enter "+ (Menu.SetTax.ordinal()+1) + " to SetTax properties");
        System.out.println("Enter "+ (Menu.Print.ordinal()+1) + " to Print all properties");
        System.out.println("Enter "+ (Menu.PrintClass.ordinal()+1) + " to Print Specific properties");
        System.out.println("Enter "+ (Menu.Save.ordinal()+1) + " to Save all properties");
        System.out.println("Enter "+ (Menu.Exit.ordinal()+1) + " to Exit from application");
    }

    public static void showChoiceOfProperty(){
        System.out.println("Enter "+ (Properties.Flat.ordinal()+1) + " to choose Flat");
        System.out.println("Enter "+ (Properties.Home.ordinal()+1) + " to choose Home");
        System.out.println("Enter "+ (Properties.Dacha.ordinal()+1) + " to choose Dacha");
        System.out.println("Enter "+ (Properties.LandPlot.ordinal()+1) + " to choose LandPlot");
    }

    public static int choose(Scanner scn) {
        return Integer.parseInt(scn.nextLine());
    }

    public static void addNewProperty(Scanner scn, List<Property> properties){
        System.out.println();
        showChoiceOfProperty();
        boolean checked = false;
        do {
            try {
                Properties prop = Properties.values()[choose(scn)-1];
                switch (prop) {
                    case Flat: {
                        properties.add(createFlat(scn));
                        checked = true;
                    } break;
                    case Home: {
                        properties.add(createHome(scn));
                        checked = true;
                    } break;
                    case Dacha: {
                        properties.add(createDacha(scn));
                        checked = true;
                    } break;
                    case LandPlot: {
                        properties.add(createLandPlot(scn));
                        checked = true;
                    } break;

                    default: throw new IllegalArgumentException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBounds i = " + (Integer.parseInt(e.getMessage())+1));
                System.out.println("Bounds of 1... " + Properties.values().length);
            } catch (Exception e) {
                System.out.println("Error");
                checked=false;
            } finally {
            }
        } while (!checked);
    }

    public static Property createFlat(Scanner scn){
        System.out.println("Enter flat's name");
        String name = scn.nextLine();
        System.out.println("Enter flat's price");
        double price = Integer.parseInt(scn.nextLine());
        return  FactoryProperties.getFlat(name, price);
    }

    public static Property createHome(Scanner scn){
        System.out.println("Enter home's name");
        String name = scn.nextLine();
        System.out.println("Enter home's price");
        double price = Integer.parseInt(scn.nextLine());
        return FactoryProperties.getHome(name, price);
    }

    public static Property createDacha(Scanner scn){
        System.out.println("Enter dacha's name");
        String name = scn.nextLine();
        System.out.println("Enter dacha's price");
        double price = Integer.parseInt(scn.nextLine());
        return  FactoryProperties.getDacha(name, price);
    }

    public static Property createLandPlot(Scanner scn){
        System.out.println("Enter landPlot's name");
        String name = scn.nextLine();
        System.out.println("Enter landPlot's price");
        double price = Integer.parseInt(scn.nextLine());
        return FactoryProperties.getLandPlot(name, price);
    }

    public static void printClass(List<Property> arr, Scanner scn){
        showChoiceOfProperty();
        Properties prop = Properties.Flat;
        boolean checked = false;
        do {
            try {
               prop = Properties.values()[choose(scn)-1];
                checked = true;
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("Error");
                checked = false;
            }
        } while(!checked);

        List<Property> propertiesOfConcreteClass = new ArrayList<>();

        for (Property item: arr) {
            if ((item.getClass().getSimpleName()).toLowerCase().equals((prop.toString()).toLowerCase())) {
                propertiesOfConcreteClass.add(item);
            }
        }
        printAll(propertiesOfConcreteClass);
    }

    public static void save(List<Property> arr) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt", false))) {
            for (Property item : arr) {
                writer.write(item.getClass().getSimpleName() + ";" + item.getName() + ";" + item.getPrice());
                writer.newLine();
            }
        }
        System.out.println("Save");
    }

    static void setTax(Scanner scn){
        double tax = 0;
        boolean checked;
        do {
            try {
                System.out.println("Enter tax");
                tax = Double.parseDouble(scn.nextLine());
                checked = true;
            }
            catch (Exception e){
                System.out.println("Error entry");
                checked = false;
            }
        } while (!checked);
        Property.setTax(tax);
    }
}
