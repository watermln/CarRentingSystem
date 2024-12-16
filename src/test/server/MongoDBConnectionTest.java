/*
package test.server;

import org.bson.Document;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import server.database.MongoDBConnection;

public class MongoDBConnectionTest {

    private static MongoDBConnection mongoConn;

    @BeforeAll
    static void setUp() {
        mongoConn = new MongoDBConnection();
        System.out.println("MongoDB connection initialized.");
    }

    @AfterAll
    static void tearDown() {
        if (mongoConn != null) {
            mongoConn.closeConnection();
            System.out.println("MongoDB connection closed.");
        }
    }

    @Test
    void testInsertDocument() {
        Document carDoc = new Document("company", "Toyota")
                .append("model", "Camry")
                .append("year", "2023")
                .append("price", "25000");

        try {
            mongoConn.insertDocument("TestCars", carDoc);
            System.out.println("Document inserted successfully into 'TestCars' collection.");
        } catch (Exception e) {
            fail("Insertion failed: " + e.getMessage());
        }
    }

    @Test
    void testFindAllDocuments() {
        try {
            List<Document> documents = mongoConn.findAllListedCars();
            assertNotNull(documents, "Documents should not be null");
            System.out.println("Fetched Documents: " + documents);
        } catch (Exception e) {
            fail("Finding documents failed: " + e.getMessage());
        }
    }

    @Test
    void testCreateCollection() {
        try {
            mongoConn.createCollection("TestCollection");
            System.out.println("Collection 'TestCollection' created successfully.");
        } catch (Exception e) {
            fail("Collection creation failed: " + e.getMessage());
        }
    }
}
*/
