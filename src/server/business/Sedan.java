package server.business;

public class Sedan extends Car {
    private double price;

    public Sedan(String carID, String make, String model, String status, double price) {
        super(carID, make, model, "Sedan", status,price);
        this.price = price;
    }


    public void showDetails() {
        System.out.println("Sedan: " + getMake() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Price: $" + price);
        System.out.println("Status: " + getStatus());
    }


    public double getPrice() {
        return price;
    }
}
