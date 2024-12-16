package shared.dto;

import server.state.BookedState;
import server.state.RentalState;

import java.io.Serializable;

public class CarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String carID;
    private String model;
    private int year;
    private String ownerPhone;
    private RentalState rentalState;

    public CarDTO(String carID, String model, int year, String ownerPhone) {
        this.carID = carID;
        this.model = model;
        this.year = year;
        this.ownerPhone = ownerPhone;
        this.rentalState = new BookedState();
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public RentalState getRentalState() {
        return rentalState;
    }

    public void setRentalState(RentalState rentalState) {
        if (rentalState != null) {
            this.rentalState = rentalState;
        } else {
            System.out.println("Warning: Cannot set rentalState to null. Keeping current state.");
        }
    }

    public void carDetails() {
        System.out.println("Car ID: " + carID);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Owner Phone: " + ownerPhone);
        System.out.println("Rental State: " + rentalState.getClass().getSimpleName());
    }
}
