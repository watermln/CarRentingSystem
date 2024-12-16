package server.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.remote.CarRemote;
import shared.remote.RentalRemote;
import shared.remote.CustomerRemote;

import server.remote.impl.CarRemoteImpl;
import server.remote.impl.RentalRemoteImpl;
import server.remote.impl.CustomerRemoteImpl;

/**
 * RMIServer - Starts the RMI registry and binds the remote services.
 */
public class RMIServer {
    public static void main(String[] args) {
        try {
            System.out.println("Starting RMI Server...");

            CarRemote carService = new CarRemoteImpl();
            RentalRemote rentalService = new RentalRemoteImpl();
            CustomerRemote customerService = new CustomerRemoteImpl();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("CarService", carService);
            registry.rebind("RentalService", rentalService);
            registry.rebind("CustomerService", customerService);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
