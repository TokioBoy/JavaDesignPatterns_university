package DecoratorPattern;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
//        Beverage beverage1 = new Decaf();
//        System.out.println(beverage1.getDescription());
//        System.out.println(beverage1.cost());
//        beverage1 = new Sugar(beverage1);
//        System.out.println(beverage1.getDescription());
//        System.out.println(beverage1.cost());
//        beverage1 = new Milk(beverage1);
//        System.out.println(beverage1.getDescription());
//        System.out.println(beverage1.cost());

        Beverage beverage2 = new Sugar(new Milk(new Milk(new Milk(new Espresso()))));
        System.out.println(beverage2.getDescription());
        System.out.println(beverage2.cost());

//        try {
//            FileInputStream fileInputStream = new FileInputStream("input.txt");
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}

abstract class Beverage {
    abstract String getDescription();
    abstract double cost();
}

abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage;

    CondimentDecorator(Beverage beverage){
        this.beverage = beverage;
    }
}

class Decaf extends Beverage {

    @Override
    String getDescription() {
        return "Decaf";
    }

    @Override
    double cost() {
        return 60;
    }
}

class Espresso extends Beverage{

    @Override
    String getDescription() {
        return "Espresso";
    }

    @Override
    double cost() {
        return 55;
    }
}

class Milk extends CondimentDecorator {
    Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    String getDescription() {
        return beverage.getDescription() + " + " + "Milk";
    }

    @Override
    double cost() {
        return beverage.cost() + 5;
    }
}

class Sugar extends CondimentDecorator {

    Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    String getDescription() {
        return beverage.getDescription() + " + Sugar";
    }

    @Override
    double cost() {
        return beverage.cost() + 3;
    }
}