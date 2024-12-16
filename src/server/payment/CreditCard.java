package server.payment;


public class CreditCard implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private int cvv;
    private String expiryDate;

    public CreditCard(String cardNumber, String cardHolder, int cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void makePayment(float amount) {
        System.out.println("Processing credit card payment of " + amount + " using card " + cardNumber);
    }
}
