package server.business;

public class SUV extends Car {
    private double price;

    public SUV(String carID, String make, String model, String status, double price) {
        super(carID, make, model, "SUV", status,price);
        this.price = price;
    }


    public void showDetails() {
        System.out.println("SUV: " + getMake() + " " + getModel() + " (" + getYear() + ")");
        System.out.println("Price: $" + price);
        System.out.println("Status: " + getStatus());
    }


    public double getPrice() {
        return price;
    }
}
