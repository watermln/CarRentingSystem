package client.customer;

import shared.observer.Observer;
import shared.observer.Notification;
import shared.dto.CustomerDTO;


public class Customer implements Observer {
    private String customerID;
    private String customerName;
    private String email;

    public Customer(String customerID, String customerName, String email) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.email = email;
    }

    @Override
    public void update(Notification notification) {
        System.out.println("Notification for Customer " + customerName + ":");
        System.out.println(notification);
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerDTO toCustomerDTO() {
        return new CustomerDTO(customerName, email);
    }

    public void updateFromDTO(CustomerDTO customerDTO) {
        this.customerName = customerDTO.getCustomerName();
        this.email = customerDTO.getEmail();
    }
}
