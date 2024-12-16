package client.facade;

import shared.dto.RentalDTO;
import shared.remote.RentalRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RentalFacade {
    private static RentalFacade instance;
    private RentalRemote rentalService;
    private RentalDTO currentRental;

    private RentalFacade() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            rentalService = (RentalRemote) registry.lookup("RentalService");
        } catch (Exception e) {
            System.err.println("Error connecting to RentalRemote: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static RentalFacade getInstance() {
        if (instance == null) {
            instance = new RentalFacade();
        }
        return instance;
    }

    public void setRentalData(RentalDTO rental) {
        this.currentRental = rental;
    }

    public RentalDTO getRentalData() {
        return currentRental;
    }

    public void updateRentalStatus(String rentalID, String newStatus) {
        try {
            rentalService.updateRentalStatus(rentalID, newStatus);
            System.out.println("Rental status updated: " + newStatus);
        } catch (Exception e) {
            System.err.println("Error updating rental status: " + e.getMessage());
        }
    }

    public void createRental(String rentalID, String startDate, String endDate, float totalCost) {
        try {
            RentalDTO rental = new RentalDTO(rentalID, startDate, endDate, totalCost);
            rentalService.createRental(rental);
            System.out.println("Rental created successfully.");
        } catch (Exception e) {
            System.err.println("Error creating rental: " + e.getMessage());
        }
    }

    public RentalDTO fetchRental(String rentalID) {
        try {
            return rentalService.getRental(rentalID);
        } catch (Exception e) {
            System.err.println("Error fetching rental: " + e.getMessage());
            return null;
        }
    }
}
