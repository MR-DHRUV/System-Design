import java.util.*;

// Observer interface
interface Observer {

    void update(float temperature, float humidity);
}

// concrete observers
class NewsChannelObserver implements Observer {

    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        // handle the update
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "C degrees and " + humidity + "% humidity");
    }
}

class CricketStadiumObserver implements Observer {

    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        // handle the update
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "C degrees and " + humidity + "% humidity");
    }
}

// Subject/ Publisher interface
interface Subject {

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}

// Concrete subject
class WeatherStation implements Subject {

    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    // business logic to set new weather measurements
    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers(); // notify all observers of the change
    }
}

public class Main {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer NewsChannelObserver = new NewsChannelObserver();
        Observer CricketStadiumObserver = new CricketStadiumObserver();

        weatherStation.addObserver(NewsChannelObserver);
        weatherStation.addObserver(CricketStadiumObserver);

        // Simulate new weather measurements
        weatherStation.setMeasurements(30.0f, 65.0f);
        weatherStation.setMeasurements(25.0f, 70.0f);
    }
}
