package shared.remote;

import shared.dto.CarDTO; // Use server's version of CarDTO
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * CarRemote defines the remote methods that can be invoked for car management.
 */
public interface CarRemote extends Remote {
    /**
     * Retrieves a list of all available cars.
     * @return A list of CarDTO objects representing available cars.
     * @throws RemoteException if RMI communication fails.
     */
    List<CarDTO> getAvailableCars() throws RemoteException;

    /**
     * Adds a new car to the system.
     * @param car The CarDTO object containing car details.
     * @throws RemoteException if RMI communication fails.
     */
    void addCar(CarDTO car) throws RemoteException;

    /**
     * Removes a car from the system by ID.
     * @param carID The unique ID of the car to remove.
     * @throws RemoteException if RMI communication fails.
     */
    void removeCar(String carID) throws RemoteException;
}
