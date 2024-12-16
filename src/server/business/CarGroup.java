package server.business;

import java.util.ArrayList;
import java.util.List;

public class CarGroup extends Car {
    private List<Car> cars = new ArrayList<>();

    public CarGroup(String carID, String make, String model, String status,double price) {
        super(carID, make, model, "Group", status,price);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void showDetails() {
        System.out.println("Car Group: " + getMake() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Status: " + getStatus());
        System.out.println("Cars in this group:");
        for (Car car : cars) {
            car.showDetails();
        }
    }


    public double getPrice() {
        double totalPrice = 0;
        for (Car car : cars) {
            totalPrice += car.getPrice();
        }
        return totalPrice;
    }
}
