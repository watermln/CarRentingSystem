package client;

import client.gui.LoginPage;
import server.database.MongoDBConnection;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        MongoDBConnection mongoConn = new MongoDBConnection();
        System.out.println("MongoDB connection initialized.");

        try {
            LoginPage login =new LoginPage();
            login.createAndShowLoginGUI();


            JFrame frame = login.getMainFrame();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    mongoConn.closeConnection();
                    System.out.println("\nConnection to MongoDB closed.");
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
