package server.business;

public class Hatchback extends Car {
    private double price;

    public Hatchback(String carID, String make, String model, String status, double price) {
        super(carID, make, model, "Hatchback", status,price);
        this.price = price;
    }


    public void showDetails() {
        System.out.println("Hatchback: " + getMake() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Price: $" + price);
        System.out.println("Status: " + getStatus());
    }


    public double getPrice() {
        return price;
    }
}
