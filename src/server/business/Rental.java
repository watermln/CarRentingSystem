package server.business;

import shared.observer.Subject;
import shared.observer.Notification;
import server.mapper.*;

public class Rental extends Subject {
    private String rentalID;
    private String rentalStatus;
    
    private String startDate;
    private String endDate;
    private float totalCost;

    public Rental(String rentalID) {
        this.rentalID = rentalID;
    }

    public void setRentalStatus(String rentalStatus) {
    this.rentalStatus = rentalStatus;
    Notification notification = new Notification(
        "Rental Status Update",
        "Rental ID " + rentalID + " status changed to " + rentalStatus
    );
    notifyObservers(notification);
}


    public String getRentalID() {
        return rentalID;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void save(RentalMapper rentalMapper) {
        rentalMapper.saveRental(this);
    }

    public void update(RentalMapper rentalMapper) {
        rentalMapper.updateRental(rentalID, this);
    }

    public void delete(RentalMapper rentalMapper) {
        rentalMapper.deleteRental(rentalID);
    }

    public static Rental find(String rentalID, RentalMapper rentalMapper) {
        return rentalMapper.findRentalByID(rentalID);
    }
     public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    // Example implementation of calculateCost()
    public void calculateCost() {
        // Logic to calculate total cost (e.g., based on days and rate)
        this.totalCost = 100 * 5; // Example: 5 days at $100/day
    }
}
   