package ObserverPatternInClass.PushInterface;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Object object = new Object();

        MeteoStation meteoStation = new MeteoStation();

        MultipurposeDisplay display1 = new MultipurposeDisplay();
        TemperatureDisplay display2 = new TemperatureDisplay();

        meteoStation.registerObserver(display1);
        meteoStation.registerObserver(display2);

        meteoStation.notifyAllObservers();

        meteoStation.removeObserver(display1);

        meteoStation.notifyAllObservers();

    }
}

interface Observer {
    void update(double temperature, double pressure, double humidity);
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyAllObservers();
}

class MeteoStation implements Subject {

    LinkedList<Observer> observers = new LinkedList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        double temperature = getTemperature();
        double pressure = getPressure();
        double humidity = getHumidity();

        for(var observer: observers){
            observer.update(temperature, pressure, humidity);
        }
    }

    double getTemperature(){
        return 4.5;
    }

    double getPressure(){
        return 1000;
    }

    double getHumidity(){
        return 90;
    }
}

interface Display {
    void display();
}

class TemperatureDisplay implements Observer, Display {

    private double temperature;

    @Override
    public void display() {
        System.out.printf("Temperature: %f Celsius\n", temperature);
    }

    @Override
    public void update(double temperature, double pressure, double humidity) {
        this.temperature = temperature;
        display();
    }
}

class MultipurposeDisplay implements Observer, Display {

    private double temperture;
    private double pressure;
    private double humidity;

    @Override
    public void display() {
        System.out.printf("Temperature: %f, humidity: %f, pressure: %f\n", temperture, humidity, pressure);
    }

    @Override
    public void update(double temperature, double pressure, double humidity) {
        this.temperture = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }
}