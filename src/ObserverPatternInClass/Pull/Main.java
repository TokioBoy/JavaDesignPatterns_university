package ObserverPatternInClass.Pull;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Object object = new Object();

        MeteoStation meteoStation = new MeteoStation();

        MultipurposeDisplay display1 = new MultipurposeDisplay(meteoStation);
        TemperatureDisplay display2 = new TemperatureDisplay(meteoStation);

        meteoStation.registerObserver(display1);
        meteoStation.registerObserver(display2);

        meteoStation.update();

        meteoStation.removeObserver(display1);

        meteoStation.update();

    }
}


interface Observer {
    void update();
}

class Subject {
    LinkedList<Observer> observers = new LinkedList<>();

    void registerObserver(Observer observer){
        observers.add(observer);
    }
    void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyAllObservers() {

        for(var observer: observers){
            observer.update();
        }
    }
}

class MeteoStation extends Subject {

    void update(){
        notifyAllObservers();
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
    private MeteoStation meteoStation;

    public TemperatureDisplay(MeteoStation meteoStation) {
        this.meteoStation = meteoStation;
    }

    @Override
    public void display() {
        System.out.printf("Temperature: %f Celsius\n", temperature);
    }

    @Override
    public void update() {

        this.temperature = meteoStation.getTemperature();
        display();
    }
}

class MultipurposeDisplay implements Observer, Display {

    private double temperture;
    private double pressure;
    private double humidity;

    private MeteoStation meteoStation;

    public MultipurposeDisplay(MeteoStation meteoStation) {
        this.meteoStation = meteoStation;
    }

    @Override
    public void display() {
        System.out.printf("Temperature: %f, humidity: %f, pressure: %f\n", temperture, humidity, pressure);
    }

    @Override
    public void update() {
        this.temperture = meteoStation.getTemperature();
        this.pressure = meteoStation.getPressure();
        this.humidity = meteoStation.getHumidity();
        display();
    }
}