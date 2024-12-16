package shared.remote;

import shared.dto.RentalDTO; // Use server's version of RentalDTO
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RentalRemote defines the remote methods for managing car rentals.
 */
public interface RentalRemote extends Remote {
    /**
     * Creates a new rental for a car.
     * @param rental The RentalDTO object containing rental details.
     * @throws RemoteException if RMI communication fails.
     */
    void createRental(RentalDTO rental) throws RemoteException;

    /**
     * Retrieves the details of a rental by its ID.
     * @param rentalID The unique ID of the rental.
     * @return The RentalDTO object representing the rental.
     * @throws RemoteException if RMI communication fails.
     */
    RentalDTO getRental(String rentalID) throws RemoteException;

    /**
     * Updates the status of a rental.
     * @param rentalID The unique ID of the rental.
     * @param status The new status (e.g., Booked, Completed).
     * @throws RemoteException if RMI communication fails.
     */
    void updateRentalStatus(String rentalID, String status) throws RemoteException;
}
