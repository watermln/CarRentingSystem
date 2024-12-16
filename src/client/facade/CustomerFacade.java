package client.facade;

import shared.dto.CustomerDTO;
import shared.remote.CustomerRemote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CustomerFacade {
    private static CustomerFacade instance;
    private CustomerRemote customerService;

    private CustomerFacade() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            customerService = (CustomerRemote) registry.lookup("CustomerService");
        } catch (Exception e) {
            System.err.println("Error connecting to CustomerRemote: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static CustomerFacade getInstance() {
        if (instance == null) {
            instance = new CustomerFacade();
        }
        return instance;
    }

    public void registerCustomer(String name, String email) {
        try {
            CustomerDTO customer = new CustomerDTO(name, email);
            customerService.registerCustomer(customer);
            System.out.println("Customer registered successfully.");
        } catch (Exception e) {
            System.err.println("Error registering customer: " + e.getMessage());
        }
    }

    public CustomerDTO getCustomer(String customerID) {
        try {
            return customerService.getCustomer(customerID);
        } catch (Exception e) {
            System.err.println("Error fetching customer: " + e.getMessage());
            return null;
        }
    }
}
