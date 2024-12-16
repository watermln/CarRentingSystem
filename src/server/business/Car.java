package server.business;
import java.util.ArrayList;
import java.util.List;

public abstract class Car {
    private String carID;
    private String make;
    private String model;
    private String type;
    private String status;
    private double price;
    private int year;
    private List<String> rentalRequests;

    public Car(String carID, String make, String model, String type, String status, double price) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.type = type;
        this.price=price;
        this.status = status;
        this.rentalRequests = new ArrayList<>();
    }

    public void addRentalRequest(String requestID) {
        if (!rentalRequests.contains(requestID)) {
            rentalRequests.add(requestID);
            System.out.println("Rental request " + requestID + " added for car: " + model);
        } else {
            System.out.println("Rental request " + requestID + " already exists for this car.");
        }
    }

    public void removeRentalRequest(String requestID) {
        if (rentalRequests.remove(requestID)) {
            System.out.println("Rental request " + requestID + " removed for car: " + model);
        } else {
            System.out.println("Rental request " + requestID + " not found for this car.");
        }
    }

    public void viewRentalRequests() {
        System.out.println("Rental requests for car: " + model);
        if (rentalRequests.isEmpty()) {
            System.out.println("No rental requests.");
        } else {
            for (String requestID : rentalRequests) {
                System.out.println(" - Request ID: " + requestID);
            }
        }
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getRentalRequests() {
        return rentalRequests;
    }

    public void setRentalRequests(List<String> rentalRequests) {
        this.rentalRequests = rentalRequests;
    }

    public abstract void showDetails();


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
