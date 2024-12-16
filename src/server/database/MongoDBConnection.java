package server.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MongoDBConnection implements AutoCloseable {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private Gson gson;

    public MongoDBConnection() {
        mongoClient = new MongoClient("localhost", 27017);

        database = mongoClient.getDatabase("your_database_name");

        gson = new Gson();
    }

    public void createCollection(String collectionName) {
        database.createCollection(collectionName);
        System.out.println("Collection created: " + collectionName);
    }

    public void insertDocument(String collectionName, Object obj) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        String jsonStr = gson.toJson(obj);
        Document doc = Document.parse(jsonStr);

        collection.insertOne(doc);
        System.out.println("Document inserted into collection: " + collectionName);
    }

    public void findDocuments(String collectionName, String field, String value, List<Document> resultDocuments) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        try (MongoCursor<Document> cursor = collection.find(Filters.eq(field, value)).iterator()) {
            while (cursor.hasNext()) {
                resultDocuments.add(cursor.next());
            }
            System.out.println("Fetched " + resultDocuments.size() + " documents from collection: " + collectionName);
        } catch (Exception e) {
            System.err.println("Error while fetching documents: " + e.getMessage());
        }
    }

    public List<Document> findAllListedCars() {
        List<Document> documents = new ArrayList<>();
        MongoCollection<Document> collection = database.getCollection("ListedCars");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        } catch (Exception e) {
            System.err.println("Error fetching listed cars: " + e.getMessage());
        }
        return documents;
    }

    public void updateDocument(String collectionName, String filterField,
                               String filterValue, String updateField, String updateValue) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.updateOne(
                Filters.eq(filterField, filterValue),
                Updates.set(updateField, updateValue)
        );
        System.out.println("Document updated in collection: " + collectionName);
    }

    public void deleteDocument(String collectionName, String field, String value) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteOne(Filters.eq(field, value));
        System.out.println("Document deleted from collection: " + collectionName);
    }

    public boolean userHandler(String username, String password, String role, boolean isSignUp, String confirmPassword, String phone,String plan) {
        MongoCollection<Document> usersCollection = database.getCollection("users");

        if (isSignUp) {
            if (usersCollection.find(new Document("username", username)).first() != null) {
                System.out.println("Username already exists.");
                return false;
            }
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match.");
                return false;
            }
            Document newUser = new Document("username", username)
                    .append("password", password) // Ideally, hash passwords
                    .append("role", role)
                    .append("insurance plan",plan)
                    .append("phone number:",phone);
            usersCollection.insertOne(newUser);
            System.out.println("User signed up successfully.");
            return true;
        } else {
            Document user = usersCollection.find(new Document("username", username)).first();
            if (user != null && user.getString("password").equals(password)) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        }
    }



    public Document getUserDocument(String field, String value) {
        MongoCollection<Document> usersCollection = database.getCollection("users");
        return usersCollection.find(Filters.eq(field, value)).first();
    }



    public void updateCarState(String collectionName, String carID, String newState) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.updateOne(
                Filters.eq("carID", carID),
                Updates.set("state", newState)
        );
        System.out.println("Car state updated to: " + newState);
    }

    public List<Document> findCarsByPhoneNumber(String phoneNumber) {
        List<Document> carList = new ArrayList<>();
        MongoCollection<Document> carsCollection = database.getCollection("ListedCars");

        try (MongoCursor<Document> cursor = carsCollection.find(Filters.eq("phoneNumber", phoneNumber)).iterator()) {
            while (cursor.hasNext()) {
                carList.add(cursor.next());
            }
        } catch (Exception e) {
            System.err.println("Error fetching cars by phone number: " + e.getMessage());
        }

        return carList;
    }




    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }

    @Override
    public void close() {
        closeConnection();
    }
}


