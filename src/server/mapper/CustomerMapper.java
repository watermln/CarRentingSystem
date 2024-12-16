package server.mapper;

import client.customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerMapper {
    private Map<String, Customer> database = new HashMap<>();

    public void saveCustomer(Customer customer) {
        database.put(customer.getCustomerID(), customer);
        System.out.println("Customer saved: " + customer.getCustomerName());
    }

    public Customer findCustomerByID(String customerID) {
        return database.get(customerID);
    }
}
