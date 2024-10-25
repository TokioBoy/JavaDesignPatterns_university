package ObserverPatternInClass.PushClass;

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

        meteoStation.update();

        meteoStation.removeObserver(display1);

        meteoStation.update();

    }
}

class MeteoStationData {
    private double temperture;
    private double pressure;
    private double humidity;


    public MeteoStationData(double temperture, double pressure, double humidity) {
        this.temperture = temperture;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemperture() {
        return temperture;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}

interface Observer {
    void update(Object object);
}

class Subject {
    LinkedList<Observer> observers = new LinkedList<>();

    void registerObserver(Observer observer){
        observers.add(observer);
    }
    void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyAllObservers(Object object) {

        for(var observer: observers){
            observer.update(object);
        }
    }
}

class MeteoStation extends Subject {

    void update(){
        notifyAllObservers(new MeteoStationData(getTemperature(), getPressure(), getHumidity()));
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
    public void update(Object object) {
        MeteoStationData meteoStationData = (MeteoStationData) object;
        this.temperature = meteoStationData.getTemperture();
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
    public void update(Object object) {
        MeteoStationData meteoStationData = (MeteoStationData) object;
        this.temperture = meteoStationData.getTemperture();
        this.pressure = meteoStationData.getPressure();
        this.humidity = meteoStationData.getHumidity();
        display();
    }
}