package client.client;

import shared.dto.CarDTO;
import shared.dto.CustomerDTO;
import shared.dto.RentalDTO;
import shared.remote.CarRemote;
import shared.remote.CustomerRemote;
import shared.remote.RentalRemote;

import java.rmi.Naming;
import java.util.List;

/**
 * RMIClient - Connects to the RMI server and tests all remote operations.
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            System.out.println("Connecting to RMI Server...");

            CarRemote carService = (CarRemote) Naming.lookup("rmi://localhost:1099/CarService");
            RentalRemote rentalService = (RentalRemote) Naming.lookup("rmi://localhost:1099/RentalService");
            CustomerRemote customerService = (CustomerRemote) Naming.lookup("rmi://localhost:1099/CustomerService");


            List<CarDTO> cars = carService.getAvailableCars();
            System.out.println("Available Cars:");
            for (CarDTO car : cars) {
                car.carDetails();
            }

            System.out.println("\n=== Testing CustomerRemote ===");
            customerService.registerCustomer(new CustomerDTO("John Doe", "john@example.com"));
            CustomerDTO customer = customerService.getCustomer("John Doe");
            System.out.println("Customer: " + customer.getCustomerName() + ", Email: " + customer.getEmail());

            System.out.println("\n=== Testing RentalRemote ===");
            RentalDTO rental = new RentalDTO("R001", "2024-01-01", "2024-01-07", 500.0f);
            rentalService.createRental(rental);

            RentalDTO fetchedRental = rentalService.getRental("R001");
            System.out.println("Rental ID: " + fetchedRental.getRentalID() + ", Total Cost: " + fetchedRental.getTotalCost());

            rentalService.updateRentalStatus("R001", "Completed");
            System.out.println("Rental status updated to 'Completed'.");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
