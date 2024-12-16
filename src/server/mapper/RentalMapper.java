package server.mapper;

import server.business.Rental;

import java.util.HashMap;
import java.util.Map;

public class RentalMapper {
    private Map<String, Rental> database = new HashMap<>(); // Simulated database

    public void saveRental(Rental rental) {
        database.put(rental.getRentalID(), rental);
        System.out.println("Rental saved: " + rental.getRentalID());
    }

    public Rental findRentalByID(String rentalID) {
        return database.get(rentalID);
    }

    public void updateRental(String rentalID, Rental updatedRental) {
        if (database.containsKey(rentalID)) {
            database.put(rentalID, updatedRental);
            System.out.println("Rental updated: " + rentalID);
        } else {
            System.out.println("Rental not found for update: " + rentalID);
        }
    }

    public void deleteRental(String rentalID) {
        if (database.remove(rentalID) != null) {
            System.out.println("Rental deleted: " + rentalID);
        } else {
            System.out.println("Rental not found for deletion: " + rentalID);
        }
    }
}
