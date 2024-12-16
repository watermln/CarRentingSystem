package client.facade;

import shared.dto.CarDTO;
import shared.remote.CarRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

/**
 * CarFacade provides client-side access to CarRemote methods.
 */
public class CarFacade {
    private static CarFacade instance;
    private CarRemote carService;
    private CarDTO currentCar;

    private CarFacade() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            carService = (CarRemote) registry.lookup("CarService");
        } catch (Exception e) {
            System.err.println("Error connecting to CarRemote: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static CarFacade getInstance() {
        if (instance == null) {
            instance = new CarFacade();
        }
        return instance;
    }

    public void setCarData(CarDTO car) {
        this.currentCar = car;
    }

    public CarDTO getCarData() {
        return currentCar;
    }

    public List<CarDTO> getAvailableCars() {
        try {
            return carService.getAvailableCars();
        } catch (Exception e) {
            System.err.println("Error fetching cars: " + e.getMessage());
            return null;
        }
    }

    public void addCar(String carID, String model, int year) {
        try {
            CarDTO car = new CarDTO(carID, model, year, "defaultPhoneNumber"); // Replace defaultPhoneNumber with actual value
            carService.addCar(car);
            System.out.println("Car added successfully: " + car.getCarID());
        } catch (Exception e) {
            System.err.println("Error adding car: " + e.getMessage());
        }
    }
}
