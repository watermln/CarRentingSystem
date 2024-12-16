package shared.dto;

import java.io.Serializable;

public class RentalDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String rentalID;
    private String startDate;
    private String endDate;
    private float totalCost;

    // Constructor
    public RentalDTO(String rentalID, String startDate, String endDate, float totalCost) {
        this.rentalID = rentalID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
        this.rentalID = rentalID;
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

    // Display details for debugging
    public void displayDetails() {
        System.out.println("Client Rental ID: " + rentalID);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Total Cost: " + totalCost);
    }
}
