package shared.remote;

import shared.dto.CustomerDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * CustomerRemote defines the remote methods for customer management.
 */
public interface CustomerRemote extends Remote {
    /**
     * Registers a new customer.
     * @param customer The CustomerDTO object containing customer details.
     * @throws RemoteException if RMI communication fails.
     */
    void registerCustomer(CustomerDTO customer) throws RemoteException;

    /**
     * Retrieves the details of a customer by ID.
     * @param customerID The unique ID of the customer.
     * @return The CustomerDTO object representing the customer.
     * @throws RemoteException if RMI communication fails.
     */
    CustomerDTO getCustomer(String customerID) throws RemoteException;
}
