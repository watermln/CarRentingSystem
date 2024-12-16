package server.state;
import server.payment.*;

public interface RentalState {
    void makePayment(float amount, Payment payment);
}
