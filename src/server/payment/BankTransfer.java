package server.payment;

public class BankTransfer implements PaymentMethod {
    private String accountNumber;
    private String routingNumber;

    public BankTransfer(String accountNumber, String routingNumber) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    @Override
    public void makePayment(float amount) {
        System.out.println("Processing bank transfer of " + amount + " from account " + accountNumber);
    }
}
