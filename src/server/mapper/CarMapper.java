package server.mapper;

import java.util.HashMap;
import java.util.Map;
import server.business.*;

public class CarMapper {
    private Map<String, Car> database = new HashMap<>(); // Simulated database

    public void saveCar(Car car) {
        database.put(car.getCarID(), car);
        System.out.println("Car saved: " + car.getCarID());
    }

    public Car findCarByID(String carID) {
        return database.get(carID);
    }

    public void updateCar(String carID, Car updatedCar) {
        if (database.containsKey(carID)) {
            database.put(carID, updatedCar);
            System.out.println("Car updated: " + carID);
        } else {
            System.out.println("Car not found for update: " + carID);
        }
    }

    public void deleteCar(String carID) {
        if (database.remove(carID) != null) {
            System.out.println("Car deleted: " + carID);
        } else {
            System.out.println("Car not found for deletion: " + carID);
        }
    }
}
