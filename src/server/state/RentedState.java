package server.state;
import server.payment.*;

public class RentedState implements RentalState {
    @Override
    public void makePayment(float amount, Payment payment) {
        System.out.println("Processing additional payment in RentedState...");
        payment.getPaymentMethod().makePayment(amount);
    }
}
