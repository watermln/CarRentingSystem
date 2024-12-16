package server.server;

import server.payment.*;
import server.state.*;

/**
 * Main class for local server-side testing (Strategy, State, and Business Logic).
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("=== Local Server-Side Testing ===");

        testPaymentMethod();

        testRentalState();
    }

    public static void testPaymentMethod() {
        System.out.println("\n=== Testing PaymentMethod (Strategy Pattern) ===");

        Payment payment = new Payment("PAY001");

        PaymentMethod creditCard = new CreditCard("1234-5678-9012-3456", "John Doe", 123, "12/25");
        payment.setPaymentMethod(creditCard);
        payment.makePayment(100);

        PaymentMethod paypal = new Paypal("john@example.com", "password123");
        payment.setPaymentMethod(paypal);
        payment.makePayment(50);

        PaymentMethod bankTransfer = new BankTransfer("987654321", "0011223344");
        payment.setPaymentMethod(bankTransfer);
        payment.makePayment(200);

        System.out.println("=== End of PaymentMethod Test ===");
    }

    public static void testRentalState() {
        System.out.println("\n=== Testing RentalState (State Pattern) ===");

        Payment payment = new Payment("PAY002");

        payment.setRentalState(new BookedState());
        payment.makePayment(100);

        payment.setRentalState(new RentedState());
        payment.makePayment(50);

        payment.setRentalState(new ReturnedState());
        payment.makePayment(30);

        System.out.println("=== End of RentalState Test ===");
    }
}
