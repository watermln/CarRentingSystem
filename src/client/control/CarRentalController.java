package client.control;

import javax.swing.*;
import client.gui.LoginPage;
import org.bson.Document;
import server.database.MongoDBConnection;
import server.state.BookedState;
import shared.dto.CarDTO;

import java.awt.*;
import java.util.List;

public class CarRentalController {

    private final MongoDBConnection mongoConn;

    // Handle Login/Logout Action
    public void handleLoginSignupAction(JFrame frame, JButton loginSignupButton) {
        if (LoginPage.currentLoggedInUser != null) { // User is logged in
            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                LoginPage.currentLoggedInUser = null; // Clear the logged-in user
                loginSignupButton.setText("Login / SignUp"); // Update button text
                frame.dispose(); // Close the main window
                SwingUtilities.invokeLater(() -> new LoginPage().createAndShowLoginGUI()); // Open login page
            }
        } else { // No user is logged in
            frame.dispose();
            SwingUtilities.invokeLater(() -> new LoginPage().createAndShowLoginGUI());
        }
    }

    // Handle Open Insurance Plans Page
    public void handleInsurancePlansAction() {
        SwingUtilities.invokeLater(() -> new client.gui.GUI().showInsurancePlans());
    }

    // Handle Open Login Page Directly
    public void handleDirectLoginAction() {
        SwingUtilities.invokeLater(() -> new LoginPage().createAndShowLoginGUI());
    }

    // Handle Rent Car Action
    public void handleRentCarAction() {
        SwingUtilities.invokeLater(() -> new client.gui.GUI().showAvailableCars());
    }

    // Handle List Car Action
    public void handleListCarAction() {
        SwingUtilities.invokeLater(() -> new client.gui.GUI().showListCarForm());
    }

    // Handle Chat Page Action
    public void handleChatPageAction() {
        SwingUtilities.invokeLater(() -> new client.gui.GUI().createChatPage());
    }

    public List<Document> fetchAvailableCars() {
        try {
            MongoDBConnection mongoConn = new MongoDBConnection();
            List<Document> carDocuments = mongoConn.findAllListedCars();
            mongoConn.closeConnection();
            return carDocuments; // Return fetched documents
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle errors gracefully
        }
    }

    public void populateCarList(JPanel mainPanel, JFrame carFrame) {
        try {
            MongoDBConnection mongoConn = new MongoDBConnection();
            List<Document> carDocuments = mongoConn.findAllListedCars();

            for (Document carDoc : carDocuments) {
                JPanel carPanel = createCarPanel(carDoc, carFrame);
                mainPanel.add(carPanel);
            }

            mongoConn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(carFrame, "Error fetching car data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to create individual car panels
    private JPanel createCarPanel(Document carDoc, JFrame carFrame) {
        JPanel carPanel = new JPanel();
        carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
        carPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        carPanel.setPreferredSize(new Dimension(200, 300));
        carPanel.setBackground(Color.WHITE);

        String company = carDoc.getString("company");
        String model = carDoc.getString("model");
        String price = carDoc.getString("price");
        int year = parseYear(carDoc.get("year"));

        JLabel companyLabel = new JLabel("Company: " + company, JLabel.CENTER);
        JLabel modelLabel = new JLabel("Model: " + model, JLabel.CENTER);
        JLabel yearLabel = new JLabel("Year: " + year, JLabel.CENTER);
        JLabel priceLabel = new JLabel("Price: $" + price, JLabel.CENTER);

        companyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        carPanel.add(companyLabel);
        carPanel.add(modelLabel);
        carPanel.add(yearLabel);
        carPanel.add(priceLabel);

        JPanel buttonPanel = createButtonPanel(carDoc, carFrame, model, year);
        carPanel.add(Box.createVerticalStrut(10));
        carPanel.add(buttonPanel);
        carPanel.add(Box.createVerticalGlue());

        return carPanel;
    }

    // Helper method to create action buttons for each car
    private JPanel createButtonPanel(Document carDoc, JFrame carFrame, String model, int year) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        JButton contactOwnerButton = new JButton("Contact Owner");
        contactOwnerButton.setFocusPainted(false);
        contactOwnerButton.setBackground(new Color(255, 69, 0));
        contactOwnerButton.setForeground(Color.WHITE);
        contactOwnerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactOwnerButton.addActionListener(e -> {
            String phoneNumber = carDoc.getString("phoneNumber");
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                phoneNumber = "Not Available";
            }
            JOptionPane.showMessageDialog(carFrame, "Phone Number: " + phoneNumber, "Owner Contact", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton bookCarButton = new JButton("Book this Car");
        bookCarButton.setFocusPainted(false);
        bookCarButton.setBackground(new Color(34, 193, 195));
        bookCarButton.setForeground(Color.WHITE);
        bookCarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        CarDTO car = new CarDTO("C001", model, year, "OwnerPhone");
        bookCarButton.addActionListener(e -> new client.gui.GUI().showBookingFormWithState(car));

        buttonPanel.add(contactOwnerButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(bookCarButton);

        return buttonPanel;
    }

    // Helper method to parse year
    private int parseYear(Object yearObj) {
        int year = 0;
        if (yearObj instanceof Integer) {
            year = (Integer) yearObj;
        } else if (yearObj instanceof String) {
            try {
                year = Integer.parseInt(((String) yearObj).trim());
            } catch (NumberFormatException e) {
                System.err.println("Invalid year format: " + yearObj);
            }
        }
        return year;
    }

    public void handleChatSendButton(JTextField inputField, JTextArea chatArea) {
        String userMessage = inputField.getText().trim();
        if (!userMessage.isEmpty()) {
            chatArea.append("User: " + userMessage + "\n");
            inputField.setText(""); // Clear the input field
        }
    }

    public void handleSubmitCarListing(String shape, String model, String yearStr, String company, String engineCapacity,
                                       String seats, String horsepower, String torque, String price, String km, JFrame frame) {
        if (shape.isEmpty() || model.isEmpty() || yearStr.isEmpty() || company.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int yearInt;
        try {
            yearInt = Integer.parseInt(yearStr);
            if (yearInt <= 0) {
                throw new NumberFormatException("Year must be a positive number.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Year must be a valid positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            MongoDBConnection mongoConn = new MongoDBConnection();
            mongoConn.createCollection("ListedCars");

            // Assuming phoneNumber is fetched once
            String phoneNumber = fetchLoggedInUserPhoneNumber();

            Document carDocument = new Document("shape", shape)
                    .append("model", model)
                    .append("year", yearInt)
                    .append("company", company)
                    .append("price", price)
                    .append("engine capacity", engineCapacity)
                    .append("horse power", horsepower)
                    .append("torque", torque)
                    .append("kilometers driven", km)
                    .append("seats", seats)
                    .append("phoneNumber", phoneNumber);

            mongoConn.insertDocument("ListedCars", carDocument);
            JOptionPane.showMessageDialog(frame, "Car listing submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mongoConn.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to submit listing. Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String fetchLoggedInUserPhoneNumber() {
        MongoDBConnection mongoConn = new MongoDBConnection();
        Document userDoc = mongoConn.getUserDocument("username", LoginPage.currentLoggedInUser);
        String phoneNumber = (userDoc != null) ? userDoc.getString("phone number:") : "Not Available";
        mongoConn.closeConnection();
        return phoneNumber;
    }

    public CarRentalController() {
        mongoConn = new MongoDBConnection(); // Single connection instance
    }

    // Fetch the user's current insurance plan
    public String getCurrentUserPlan(String username) {
        try {
            Document userDoc = mongoConn.getUserDocument("username", username);
            return (userDoc != null && userDoc.getString("insurance plan") != null)
                    ? userDoc.getString("insurance plan")
                    : "Free Plan"; // Default to Free Plan
        } catch (Exception e) {
            e.printStackTrace();
            return "Free Plan";
        }
    }

    // Update the user's insurance plan
    public boolean updateUserPlan(String username, String newPlan) {
        try {
            mongoConn.updateDocument(
                    "users",        // Collection name
                    "username",     // Field to filter
                    username,       // Filter value
                    "insurance plan", newPlan // Field and value to update
            );
            System.out.println("Insurance plan updated successfully for user: " + username);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to update insurance plan.");
            return false;
        }
    }

    public void closeController() {
        mongoConn.closeConnection();
    }

    public boolean handleCarBooking(CarDTO car, String name, String phone) {
        if (name.isEmpty() || phone.isEmpty()) {
            System.out.println("Booking failed: Name or phone is empty.");
            return false;
        }

        try (MongoDBConnection dbConnection = new MongoDBConnection()) {
            dbConnection.updateDocument(
                    "ListedCars",         // Collection name
                    "carID",              // Filter field
                    car.getCarID(),       // Filter value
                    "state", "Booked"     // Update field and value
            );
            car.setRentalState(new BookedState());
            System.out.println("Booking successful for car: " + car.getCarID());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during booking: " + e.getMessage());
            return false;
        }
    }


    public void handleRoleToggle(JToggleButton roleToggleButton, JLabel titleLabel, JPanel formPanel) {
        if (roleToggleButton.isSelected()) {
            roleToggleButton.setText("Lister");
            titleLabel.setText("Login as Lister");
            formPanel.setBackground(new Color(255, 220, 220)); // Color for Lister
        } else {
            roleToggleButton.setText("Renter");
            titleLabel.setText("Login as Renter");
            formPanel.setBackground(new Color(220, 220, 255)); // Color for Renter
        }
    }

    // Handle Auth Toggle Action
    public void handleAuthToggle(JToggleButton authToggleButton, JLabel titleLabel, JButton submitButton,
                                 JLabel confirmPasswordLabel, JPasswordField confirmPasswordField,
                                 JLabel phoneLabel, JTextField phoneField, JLabel roleLabel,
                                 JComboBox<String> roleDropdown, JComboBox<String> planDropdown,
                                 JLabel planLabel, JToggleButton roleToggleButton) {
        if (authToggleButton.isSelected()) {
            // Sign-Up Mode
            titleLabel.setText("Login/Sign Up");
            submitButton.setText("Sign Up");
            confirmPasswordLabel.setVisible(true);
            confirmPasswordField.setVisible(true);
            phoneLabel.setVisible(true);
            phoneField.setVisible(true);
            roleLabel.setVisible(true);
            roleDropdown.setVisible(true);
            planDropdown.setVisible(true);
            planLabel.setVisible(true);
            roleToggleButton.setVisible(false); // Hide Renter/Lister toggle
        } else {
            // Login Mode
            titleLabel.setText("Login/Sign Up");
            submitButton.setText("Login");
            confirmPasswordLabel.setVisible(false);
            confirmPasswordField.setVisible(false);
            phoneLabel.setVisible(false);
            phoneField.setVisible(false);
            roleLabel.setVisible(false);
            roleDropdown.setVisible(false);
            planDropdown.setVisible(false);
            planLabel.setVisible(false);
            roleToggleButton.setVisible(true); // Show Renter/Lister toggle
        }
    }

    // Handle Submit Button Action
    public boolean handleSubmitButton(JFrame loginFrame, JTextField usernameField, JTextField phoneField,
                                      JPasswordField passwordField, JPasswordField confirmPasswordField,
                                      JComboBox<String> roleDropdown, JComboBox<String> planDropdown,
                                      JToggleButton authToggleButton, JToggleButton roleToggleButton) {
        // Fetch input values
        String username = usernameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = confirmPasswordField.isVisible() ? new String(confirmPasswordField.getPassword()).trim() : null;
        String role = roleDropdown.isVisible() ? (String) roleDropdown.getSelectedItem() : null;
        String plan = planDropdown.isVisible() ? (String) planDropdown.getSelectedItem() : null;

        boolean isSignUp = authToggleButton.isSelected();

        // Input Validation
        if (username.isEmpty() || password.isEmpty() || (isSignUp && (confirmPassword == null || confirmPassword.isEmpty()))) {
            JOptionPane.showMessageDialog(loginFrame, "Fields cannot be empty. Please fill all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Call MongoDB for user validation or registration
        try (MongoDBConnection mongoDB = new MongoDBConnection()) {
            boolean result = mongoDB.userHandler(username, password, role, isSignUp, confirmPassword, phone, plan);

            if (result) {
                Document userDoc = mongoDB.getUserDocument("username", username);
                String userRoleFromDB = (userDoc != null) ? userDoc.getString("role") : null;
                String selectedRole = roleToggleButton.isSelected() ? "Lister" : "Renter";

                if (userRoleFromDB != null && !userRoleFromDB.equalsIgnoreCase(selectedRole)) {
                    JOptionPane.showMessageDialog(loginFrame,
                            "Role mismatch! Please select the correct role: " + userRoleFromDB,
                            "Role Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                LoginPage.currentLoggedInUser = username;
                JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(loginFrame, isSignUp ? "Sign-Up Failed! Username may already exist."
                        : "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    public void handleViewMyListedCars() {
        try (MongoDBConnection mongoConn = new MongoDBConnection()) {
            Document userDoc = mongoConn.getUserDocument("username", LoginPage.currentLoggedInUser);
            String phoneNumber = (userDoc != null) ? userDoc.getString("phone number:") : null;

            if (phoneNumber == null || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No phone number found for the current user!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Document> myCars = mongoConn.findCarsByPhoneNumber(phoneNumber);

            // Setup JFrame for My Listed Cars
            JFrame myCarsFrame = new JFrame("My Listed Cars");
            myCarsFrame.setSize(800, 600);
            myCarsFrame.setLocationRelativeTo(null);
            myCarsFrame.setLayout(new BorderLayout());

            JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
            JScrollPane scrollPane = new JScrollPane(mainPanel,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            myCarsFrame.add(scrollPane, BorderLayout.CENTER);

            if (myCars.isEmpty()) {
                JLabel noCarsLabel = new JLabel("You have not listed any cars yet.", SwingConstants.CENTER);
                noCarsLabel.setFont(new Font("Arial", Font.BOLD, 18));
                myCarsFrame.add(noCarsLabel, BorderLayout.CENTER);
            } else {
                for (Document car : myCars) {
                    JPanel carPanel = new JPanel();
                    carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
                    carPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    carPanel.setPreferredSize(new Dimension(200, 250));
                    carPanel.setBackground(Color.WHITE);

                    // Car Details
                    String model = car.getString("model");
                    String year = String.valueOf(car.get("year"));
                    String company = car.getString("company");
                    String price = car.getString("price");

                    JLabel modelLabel = new JLabel("Model: " + model, SwingConstants.CENTER);
                    JLabel yearLabel = new JLabel("Year: " + year, SwingConstants.CENTER);
                    JLabel companyLabel = new JLabel("Company: " + company, SwingConstants.CENTER);
                    JLabel priceLabel = new JLabel("Price: $" + price, SwingConstants.CENTER);

                    modelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    companyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // Add labels to panel
                    carPanel.add(Box.createVerticalStrut(10));
                    carPanel.add(modelLabel);
                    carPanel.add(yearLabel);
                    carPanel.add(companyLabel);
                    carPanel.add(priceLabel);
                    carPanel.add(Box.createVerticalGlue());

                    mainPanel.add(carPanel);
                }
            }

            myCarsFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching your listed cars!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleShowBookingConfirmation(CarDTO car, JFrame parentFrame) {
        // Fetch the renter's phone number dynamically
        String renterPhoneNumber = fetchRenterPhoneNumber(LoginPage.currentLoggedInUser); // Replace with actual fetch method
        String ownerPhoneNumber = car.getOwnerPhone() != null ? car.getOwnerPhone() : "Not Available";

        // Build the booking confirmation form
        JFrame bookingFrame = new JFrame("Confirm Booking");
        bookingFrame.setSize(400, 300);
        bookingFrame.setLocationRelativeTo(parentFrame);
        bookingFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Display car details
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Car Details"));
        detailsPanel.add(new JLabel("Company: " + car.getModel()));
        detailsPanel.add(new JLabel("Model: " + car.getModel()));
        detailsPanel.add(new JLabel("Year: " + car.getYear()));
        detailsPanel.add(new JLabel("Owner Phone: " + ownerPhoneNumber));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        bookingFrame.add(detailsPanel, gbc);

        // Confirm Booking button
        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setBackground(new Color(0, 128, 0));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);

        gbc.gridy = 1; gbc.gridwidth = 2;
        bookingFrame.add(confirmButton, gbc);

        confirmButton.addActionListener(e -> {
            // Generate the receipt with all required details
            String receipt = "Booking Confirmation\n" +
                    "-----------------------------\n" +
                    "Car: " + car.getModel() + "\n" +
                    "Year: " + car.getYear() + "\n" +
                    "Owner Phone: " + ownerPhoneNumber + "\n" +
                    "Renter Phone: " + renterPhoneNumber + "\n" +
                    "Thank you for booking with us!";

            // Show receipt
            JOptionPane.showMessageDialog(bookingFrame, receipt, "Booking Receipt", JOptionPane.INFORMATION_MESSAGE);
            bookingFrame.dispose();
        });

        bookingFrame.setVisible(true);
    }

    // Helper method to fetch renter's phone number
    private String fetchRenterPhoneNumber(String currentUser) {
        try (MongoDBConnection dbConnection = new MongoDBConnection()) {
            Document userDoc = dbConnection.getUserDocument("username", currentUser);
            return userDoc != null ? userDoc.getString("phone number:") : "Not Available";
        } catch (Exception e) {
            e.printStackTrace();
            return "Not Available";
        }
    }







}
