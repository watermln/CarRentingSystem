package server.remote.impl;

import shared.dto.CarDTO;
import shared.remote.CarRemote;
import server.database.MongoDBConnection;
import org.bson.Document;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CarRemoteImpl extends UnicastRemoteObject implements CarRemote {
    private MongoDBConnection dbConnection;

    public CarRemoteImpl() throws RemoteException {
        super();
        this.dbConnection = new MongoDBConnection();
    }

    @Override
    public List<CarDTO> getAvailableCars() throws RemoteException {
        System.out.println("Fetching available cars from MongoDB...");

        List<Document> carDocuments = new ArrayList<>();
        dbConnection.findDocuments("cars", "status", "available", carDocuments);

        List<CarDTO> carList = new ArrayList<>();
        if (carDocuments != null) {
            for (Document doc : carDocuments) {
                int year = 0; // Default year value
                Object yearObj = doc.get("year");

                if (yearObj instanceof Integer) {
                    year = (Integer) yearObj;
                } else if (yearObj instanceof String) {
                    try {
                        year = Integer.parseInt((String) yearObj);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid year format: " + yearObj);
                    }
                }

                CarDTO car = new CarDTO(
                        doc.getString("carID"),
                        doc.getString("model"),
                        year,
                        doc.getString("ownerPhone")
                );

                carList.add(car);
            }
        }


        System.out.println("Fetched " + carList.size() + " available cars.");
        return carList;
    }

    @Override
    public void addCar(CarDTO car) throws RemoteException {
        System.out.println("Adding car to MongoDB...");

        Document carDoc = new Document("carID", car.getCarID())
                .append("model", car.getModel())
                .append("year", car.getYear())
                .append("ownerPhone", car.getOwnerPhone()) // Include owner's phone number
                .append("status", "available"); // Default status to "available"

        dbConnection.insertDocument("cars", carDoc);
        System.out.println("Car added successfully: " + car.getCarID());
    }

    @Override
    public void removeCar(String carID) throws RemoteException {
        System.out.println("Removing car from MongoDB...");

        dbConnection.deleteDocument("cars", "carID", carID);
        System.out.println("Car removed successfully: " + carID);
    }
}
