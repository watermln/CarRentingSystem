package server;

import server.database.MongoDBConnection;


public class Mainapp {
    public static void main(String[] args) {
        MongoDBConnection mongoConn = new MongoDBConnection();


            mongoConn.closeConnection();
            System.out.println("\nConnection to MongoDB closed.");
        }
    }
