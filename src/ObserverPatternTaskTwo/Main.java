package ObserverPatternTaskTwo;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Sensor sensor = new Sensor(22, 102, 21, false);

        CentralSystem centralSystem = new CentralSystem();
        LifeSupport lifeSupport = new LifeSupport();
        FireDetection fireDetection = new FireDetection();
        HullBreachDetection hullBreachDetection = new HullBreachDetection();

        sensor.addObserver(lifeSupport);
        sensor.addObserver(fireDetection);
        sensor.addObserver(hullBreachDetection);

        centralSystem.updateShipStatus(sensor);
    }
}


interface Observer {
    void updatePull(Sensor sensor);  // Pull-based notification
}

interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserversPull();
}

class Sensor implements Observable {
    private double temperature;
    private double pressure;
    private double oxygenLevel;
    private boolean smoke;
    private final List<Observer> observers = new ArrayList<>();

    public Sensor(double temperature, double pressure, double oxygenLevel, boolean smoke) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.oxygenLevel = oxygenLevel;
        this.smoke = smoke;
    }

    public double getTemperature() { return temperature; }
    public double getPressure() { return pressure; }
    public double getOxygenLevel() { return oxygenLevel; }
    public boolean isSmoke() { return smoke; }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversPull() {
        for (Observer observer : observers) {
            observer.updatePull(this);
        }
    }

    public void updateSensors(double temperature, double pressure, double oxygenLevel, boolean smoke) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.oxygenLevel = oxygenLevel;
        this.smoke = smoke;
        notifyObserversPull();
    }
}

class LifeSupport implements Observer {
    @Override
    public void updatePull(Sensor sensor) {
        double temp = sensor.getTemperature();
        double pressure = sensor.getPressure();
        double oxygen = sensor.getOxygenLevel();
        if (temp < 18 || temp > 26 || pressure < 101 || pressure > 108 || oxygen < 20 || oxygen > 40) {
            System.out.println("Life Support Alert!");
        }
    }
}

class FireDetection implements Observer {
    @Override
    public void updatePull(Sensor sensor) {
        if (sensor.getTemperature() > 26 && sensor.isSmoke()) {
            System.out.println("Fire Detection Alert!");
        }
    }
}

class HullBreachDetection implements Observer {
    private double lastPressure = 101; // assume initial safe pressure
    @Override
    public void updatePull(Sensor sensor) {
        double currentPressure = sensor.getPressure();
        if (Math.abs(currentPressure - lastPressure) > 1) {
            System.out.println("Hull Breach Detection Alert!");
        }
        lastPressure = currentPressure;
    }
}

class CentralSystem{
    void updateShipStatus(Sensor sensor){
        sensor.updateSensors(27, 102, 19, true);
    }
}
