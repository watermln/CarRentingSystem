package server.payment;

public class Paypal implements PaymentMethod {
    private String email;
    private String password;

    public Paypal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void makePayment(float amount) {
        System.out.println("Processing PayPal payment of " + amount + " using account " + email);
    }
}
