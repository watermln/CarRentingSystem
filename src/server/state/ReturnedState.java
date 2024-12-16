package server.state;
import server.payment.*;

public class ReturnedState implements RentalState {
    @Override
    public void makePayment(float amount, Payment payment) {
        System.out.println("Processing final payment in ReturnedState...");
        payment.getPaymentMethod().makePayment(amount);
        System.out.println("Rental completed. No further actions allowed.");
    }
}
