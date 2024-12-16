package shared.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String customerName;
    private String email;

    // Constructor
    public CustomerDTO(String customerName, String email) {
        this.customerName = customerName;
        this.email = email;
    }

    // Getters and Setters
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

    public void displayDetails() {
        System.out.println("Client Customer Name: " + customerName);
        System.out.println("Client Email: " + email);
    }
}
