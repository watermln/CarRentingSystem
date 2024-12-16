package client.customer;

import server.business.Rental;

public class Admin {
    private String adminID;
    private String password;

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
    }

    public boolean login(String adminID, String password) {
        return this.adminID.equals(adminID) && this.password.equals(password);
    }

    public void addRental(Rental rental) {
        System.out.println("Admin added a new rental: " + rental.getRentalID());
    }

    public void deleteRental(Rental rental) {
        System.out.println("Admin deleted rental: " + rental.getRentalID());
    }

    public String getAdminID() {
        return adminID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
