package lesson1;

public class lesson1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Cat cat = new Cat();
        WildCat wildCat = new WildCat();
        cat.makeSound();

        Animal[] animals = {new Dog(), new Cat(), new WildCat()};
        for(var animal : animals){
            animal.makeSound();
        }
        Cat[] cats = {new Cat(), new WildCat()};

        Item[] items = {new Cat(), new DBItem()};

        System.out.println(Cat.catCount);

        System.out.println(MyMath.addTwoNumbers(1, 2));

    }
}


//abstract class Item{
//    abstract int getId();
//
//}

interface Item{
    int getId();
    int getName();
    int getTimeStamp();
}

class DBItem implements Item{

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getName() {
        return 0;
    }

    @Override
    public int getTimeStamp() {
        return 0;
    }
}

abstract class Animal{
    protected String name;
    protected void digest(){}
    abstract void makeSound();
}

class Dog extends Animal{
    void makeSound(){
        System.out.println("Bark");
    }
}


class Cat extends Animal implements Item{
    static int catCount = 0;

    Cat(){
        catCount++;
    }
    void makeSound(){
        System.out.println("Meow");
        System.out.println("I am " + name);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getName() {
        return 0;
    }

    @Override
    public int getTimeStamp() {
        return 0;
    }
}

class WildCat extends Cat{
    void live(){
        makeSound();
        digest();
    }
}

class MyMath {
    static int addTwoNumbers(int a, int b){
        return a + b;
    }
}