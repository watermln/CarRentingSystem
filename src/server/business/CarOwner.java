package server.business;

import java.util.ArrayList;
import java.util.List;


public class CarOwner {
    private String ownerID;
    private String ownerName;
    private String email;
    private List<Car> ownedCars;

    public CarOwner(String ownerID, String ownerName, String email) {
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.email = email;
        this.ownedCars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car != null && !ownedCars.contains(car)) {
            ownedCars.add(car);
            System.out.println("Car added by owner: " + car.getModel());
        } else {
            System.out.println("Car is either null or already added.");
        }
    }

    public void removeCar(Car car) {
        if (ownedCars.remove(car)) {
            System.out.println("Car removed by owner: " + car.getModel());
        } else {
            System.out.println("Car not found in the owner's list.");
        }
    }

    public void viewRentalRequests() {
        System.out.println("Viewing rental requests for owner: " + ownerName);
        for (Car car : ownedCars) {
            System.out.println("Rental requests for car: " + car.getModel());
            car.viewRentalRequests();
        }
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getEmail() {
        return email;
    }

    public List<Car> getOwnedCars() {
        return new ArrayList<>(ownedCars);
    }
}
