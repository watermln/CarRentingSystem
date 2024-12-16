package server.payment;
import server.state.*;

public class Payment {
    private String paymentID;
    private double totalAmount;
    private String paymentStatus;
    private PaymentMethod paymentMethod;
    private RentalState rentalState;

    public Payment(String paymentID) {
        this.paymentID = paymentID;
        this.totalAmount = 0.0;
        this.paymentStatus = "Pending";
        this.rentalState = new BookedState(); // Default state
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void makePayment(float amount) {
        if (paymentMethod == null) {
            System.out.println("No payment method selected.");
            return;
        }
        rentalState.makePayment(amount, this);
        this.totalAmount += amount;
        this.paymentStatus = "Completed";
    }

    public void setRentalState(RentalState rentalState) {
        this.rentalState = rentalState;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public RentalState getRentalState() {
        return rentalState;
    }
}
