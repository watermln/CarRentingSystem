package server.remote.impl;

import shared.remote.RentalRemote;
import shared.dto.RentalDTO;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * RentalRemoteImpl provides the implementation for managing rentals remotely.
 */
public class RentalRemoteImpl extends UnicastRemoteObject implements RentalRemote {
    private Map<String, RentalDTO> rentalMap;

    public RentalRemoteImpl() throws RemoteException {
        this.rentalMap = new HashMap<>();
    }

    @Override
    public void createRental(RentalDTO rental) throws RemoteException {
        rentalMap.put(rental.getRentalID(), rental);
        System.out.println("Rental created: " + rental.getRentalID());
    }

    @Override
    public RentalDTO getRental(String rentalID) throws RemoteException {
        System.out.println("Fetching rental: " + rentalID);
        return rentalMap.get(rentalID);
    }

    @Override
    public void updateRentalStatus(String rentalID, String status) throws RemoteException {
        RentalDTO rental = rentalMap.get(rentalID);
        if (rental != null) {
            System.out.println("Updating rental status: " + rentalID + " to " + status);
            // Additional logic for updating the status can be implemented here.
        } else {
            System.out.println("Rental not found: " + rentalID);
        }
    }
}
