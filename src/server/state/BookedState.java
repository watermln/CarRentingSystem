package server.state;
import server.payment.*;

public class BookedState implements RentalState {
    @Override
    public void makePayment(float amount, Payment payment) {
        System.out.println("Processing payment in BookedState...");
        payment.getPaymentMethod().makePayment(amount);
        payment.setRentalState(new RentedState());
        System.out.println("Transitioned to RentedState.");
    }
}
 