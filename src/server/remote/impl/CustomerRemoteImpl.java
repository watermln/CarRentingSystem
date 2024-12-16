package server.remote.impl;

import shared.remote.CustomerRemote;
import shared.dto.CustomerDTO;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * CustomerRemoteImpl provides the implementation for managing customers remotely.
 */
public class CustomerRemoteImpl extends UnicastRemoteObject implements CustomerRemote {
    private Map<String, CustomerDTO> customerMap;

    public CustomerRemoteImpl() throws RemoteException {
        this.customerMap = new HashMap<>();
    }

    @Override
    public void registerCustomer(CustomerDTO customer) throws RemoteException {
        customerMap.put(customer.getCustomerName(), customer);
        System.out.println("Customer registered: " + customer.getCustomerName());
    }

    @Override
    public CustomerDTO getCustomer(String customerID) throws RemoteException {
        System.out.println("Fetching customer: " + customerID);
        return customerMap.get(customerID);
    }
}
