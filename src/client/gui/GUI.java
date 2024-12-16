package client.gui;

import client.control.CarRentalController;
import server.database.MongoDBConnection;
import org.bson.Document;
import server.state.BookedState;
import shared.dto.CarDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class GUI {

    private CarRentalController controller = new CarRentalController();
    private JFrame frame;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        MongoDBConnection mongoConn = new MongoDBConnection();
        Document userDoc = mongoConn.getUserDocument("username", LoginPage.currentLoggedInUser);
        String userRole = (userDoc != null) ? userDoc.getString("role") : "Renter";
        mongoConn.closeConnection();

        frame.getContentPane().setBackground(new Color(245, 245, 245));
        JLabel titleLabel = new JLabel("Car Rental System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        frame.add(titleLabel);

        JButton loginSignupButton = new JButton(LoginPage.currentLoggedInUser == null ? "Login / SignUp" : "Logout");
        loginSignupButton.setFocusPainted(false);
        loginSignupButton.setBounds(10, 10, 150, 40);
        loginSignupButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginSignupButton.setBackground(new Color(0, 123, 255));
        loginSignupButton.setForeground(Color.WHITE);
        frame.add(loginSignupButton);

        JButton rentCarButton = new JButton("Rent a Car");
        rentCarButton.setFont(new Font("Arial", Font.BOLD, 16));
        rentCarButton.setBackground(new Color(34, 193, 195));
        rentCarButton.setForeground(Color.WHITE);
        rentCarButton.setFocusPainted(false);
        frame.add(rentCarButton);

        // Dynamically adjust button positions
        int buttonYPosition = 150;
        rentCarButton.setBounds(300, buttonYPosition, 200, 50);
        frame.add(rentCarButton);

        JButton listCarButton = new JButton("List a Car for Rent");
        listCarButton.setFont(new Font("Arial", Font.BOLD, 16));
        listCarButton.setBackground(new Color(255, 138, 0));
        listCarButton.setForeground(Color.WHITE);
        listCarButton.setFocusPainted(false);

        // Check user role and adjust visibility
        if ("Lister".equalsIgnoreCase(userRole)) {
            listCarButton.setBounds(300, buttonYPosition + 70, 200, 50);
            frame.add(listCarButton);
            buttonYPosition += 70; // Increment position only if visible
        }

        JButton insuranceButton = new JButton("Insurance Plans");
        insuranceButton.setFont(new Font("Arial", Font.BOLD, 16));
        insuranceButton.setBackground(new Color(100, 149, 237));
        insuranceButton.setForeground(Color.WHITE);
        insuranceButton.setFocusPainted(false);
        frame.add(insuranceButton);

        // Position dynamically after listCarButton or rentCarButton
        insuranceButton.setBounds(300, buttonYPosition + 70, 200, 50);
        frame.add(insuranceButton);

        // Dynamically position buttons at the bottom-right
        frame.setLayout(null);

// "Chat with Us" Button
        JButton chatButton = new JButton("Chat with Us");
        chatButton.setFont(new Font("Arial", Font.BOLD, 14));
        chatButton.setBackground(new Color(0, 204, 255));
        chatButton.setForeground(Color.WHITE);
        chatButton.setFocusPainted(false);
        frame.add(chatButton);

// "View My Listed Cars" Button for Listers
        JButton viewMyCarsButton = null;
        if ("Lister".equalsIgnoreCase(userRole)) {
            viewMyCarsButton = new JButton("View My Listed Cars");
            viewMyCarsButton.setFont(new Font("Arial", Font.BOLD, 14));
            viewMyCarsButton.setBackground(new Color(34, 139, 34)); // Green for distinction
            viewMyCarsButton.setForeground(Color.WHITE);
            viewMyCarsButton.setFocusPainted(false);
            frame.add(viewMyCarsButton);

            // Action Listener
            viewMyCarsButton.addActionListener(e -> {
                CarRentalController controller = new CarRentalController();
                controller.handleViewMyListedCars();
            });
        }

// Adjust button positions dynamically
        JButton finalViewMyCarsButton = viewMyCarsButton; // Final for lambda use
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                int chatButtonWidth = chatButton.getFontMetrics(chatButton.getFont()).stringWidth(chatButton.getText()) + 40;
                int chatButtonHeight = 40;

                chatButton.setBounds(frameWidth - chatButtonWidth - 20, frameHeight - chatButtonHeight - 45, chatButtonWidth, chatButtonHeight);

                if (finalViewMyCarsButton != null) {
                    int viewButtonWidth = finalViewMyCarsButton.getFontMetrics(finalViewMyCarsButton.getFont()).stringWidth(finalViewMyCarsButton.getText()) + 40;
                    int viewButtonHeight = 40;
                    finalViewMyCarsButton.setBounds(
                            frameWidth - chatButtonWidth - viewButtonWidth - 30,
                            frameHeight - viewButtonHeight - 45,
                            viewButtonWidth,
                            viewButtonHeight
                    );
                }
            }
        });

        // Trigger resize logic initially
        frame.setSize(frame.getSize());


        CarRentalController controller = new CarRentalController();

        loginSignupButton.addActionListener(e ->
                controller.handleLoginSignupAction(frame, loginSignupButton));

        insuranceButton.addActionListener(e ->
                controller.handleInsurancePlansAction());

        rentCarButton.addActionListener(e ->
                controller.handleRentCarAction());

        listCarButton.addActionListener(e ->
                controller.handleListCarAction());

        chatButton.addActionListener(e ->
                controller.handleChatPageAction());


        // Adjust button positions dynamically
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = frame.getWidth();

                int buttonWidth = 200;
                int buttonHeight = 50;
                int spacing = 20;

                int centerX = (frameWidth - buttonWidth) / 2;
                int centerY = (frame.getHeight() - (buttonHeight * 3 + spacing * 2)) / 2;

                // Keep title centered horizontally but fixed at the top
                titleLabel.setBounds(0, 20, frameWidth, 40); // Fixed Y-axis at 20px, dynamic X-axis

                // Center buttons
                rentCarButton.setBounds(centerX, centerY, buttonWidth, buttonHeight);

                if ("Lister".equalsIgnoreCase(userRole)) {
                    listCarButton.setBounds(centerX, centerY + buttonHeight + spacing, buttonWidth, buttonHeight);
                }

                insuranceButton.setBounds(centerX, centerY + (buttonHeight + spacing) * ("Lister".equalsIgnoreCase(userRole) ? 2 : 1), buttonWidth, buttonHeight);
            }
        });

// Trigger initial layout adjustment
        frame.setSize(frame.getSize());


// Set the frame visible
        frame.setVisible(true);

    }


    public void showAvailableCars() {
        JFrame carFrame = new JFrame("Available Cars");
        carFrame.setSize(800, 600);
        carFrame.setLocationRelativeTo(null); // Center the window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // Keeps them side-by-side

        JScrollPane scrollPane = new JScrollPane(
                mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER, // Disable vertical scrollbar
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS // Enable horizontal scrollbar
        );
        carFrame.add(scrollPane);

        // Fetch car data using the controller
        CarRentalController controller = new CarRentalController();
        List<Document> carDocuments = controller.fetchAvailableCars();

        if (carDocuments != null) {
            for (Document carDoc : carDocuments) {
                JPanel carPanel = new JPanel();
                carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
                carPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                carPanel.setPreferredSize(new Dimension(200, 300));
                carPanel.setBackground(Color.WHITE);

                String company = carDoc.getString("company");
                String model = carDoc.getString("model");
                String price = carDoc.getString("price");
                String listedBy = carDoc.getString("`listedBy`");

                int year = 0;
                Object yearObj = carDoc.get("year");
                if (yearObj instanceof Integer) {
                    year = (Integer) yearObj;
                } else if (yearObj instanceof String) {
                    try {
                        year = Integer.parseInt(((String) yearObj).trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid year format: " + yearObj);
                    }
                }

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

                carPanel.add(Box.createVerticalStrut(10));

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                buttonPanel.setOpaque(false);

                JButton contactOwnerButton = new JButton("Contact Owner");
                contactOwnerButton.setBackground(new Color(255, 69, 0));
                contactOwnerButton.setForeground(Color.WHITE);
                contactOwnerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                contactOwnerButton.setFocusPainted(false);

                // Fetch phoneNumber safely
                final String phoneNumber = carDoc.getString("phoneNumber") != null && !carDoc.getString("phoneNumber").isEmpty()
                        ? carDoc.getString("phoneNumber")
                        : "Not Available";

                contactOwnerButton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(carFrame, "Phone Number: " + phoneNumber,
                            "Owner Contact", JOptionPane.INFORMATION_MESSAGE);
                });

                buttonPanel.add(contactOwnerButton);

                buttonPanel.add(Box.createVerticalStrut(10)); // Spacing

                JButton bookCarButton = new JButton("Book this Car");
                bookCarButton.setBackground(new Color(34, 193, 195));
                bookCarButton.setForeground(Color.WHITE);
                bookCarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                bookCarButton.setFocusPainted(false);


                final String ownerPhoneNumber = carDoc.getString("phoneNumber") != null && !carDoc.getString("phoneNumber").isEmpty()
                        ? carDoc.getString("phoneNumber")
                        : "Not Available";

                CarDTO car = new CarDTO("C001", model, year, ownerPhoneNumber);
                bookCarButton.addActionListener(e -> controller.handleShowBookingConfirmation(car, carFrame));


                buttonPanel.add(bookCarButton);

                carPanel.add(buttonPanel);

                carPanel.add(Box.createVerticalGlue());

                mainPanel.add(carPanel);
            }
        } else {
            JOptionPane.showMessageDialog(carFrame, "No cars available to display.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        carFrame.setVisible(true);
    }

    public void createChatPage() {
        JFrame chatFrame = new JFrame("Chat with Us");
        chatFrame.setSize(500, 600);
        chatFrame.setLocationRelativeTo(null); // Center the window
        chatFrame.setLayout(new BorderLayout());

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setText("Agent: Welcome! How can I assist you today?\nUser: I'm interested in renting a car.\nAgent: Great! We have a variety of cars available.\n");

        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatFrame.add(chatScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        // Use the controller to handle the send button logic
        CarRentalController controller = new CarRentalController();
        sendButton.addActionListener(e -> controller.handleChatSendButton(inputField, chatArea));

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatFrame.add(inputPanel, BorderLayout.SOUTH);
        chatFrame.setVisible(true);
    }

    public void showListCarForm() {
        JFrame listCarFrame = new JFrame("List a Car for Rent");
        listCarFrame.setSize(600, 600);
        listCarFrame.setLocationRelativeTo(null);
        listCarFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // GUI Components
        JLabel shapeLabel = new JLabel("Car Shape:");
        JComboBox<String> shapeDropdown = new JComboBox<>(new String[]{"Hatchback", "Sedan", "SUV"}); // Replace TextField with Dropdown
        JLabel modelLabel = new JLabel("Car Model:");
        JTextField modelField = new JTextField(20);
        JLabel yearLabel = new JLabel("Year Made:");
        JTextField yearField = new JTextField(20);
        JLabel companyLabel = new JLabel("Company:");
        JTextField companyField = new JTextField(20);
        JLabel engineCapacityLabel = new JLabel("Engine Capacity (L):");
        JTextField engineCapacityField = new JTextField(20);
        JLabel seatsLabel = new JLabel("Number of Seats:");
        JTextField seatsField = new JTextField(20);
        JLabel horsepowerLabel = new JLabel("Horsepower (HP):");
        JTextField horsepowerField = new JTextField(20);
        JLabel torqueLabel = new JLabel("Torque (Nm):");
        JTextField torqueField = new JTextField(20);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(20);
        JLabel kmDrivenLabel = new JLabel("Kilometers Driven:");
        JTextField kmDrivenField = new JTextField(20);
        JButton uploadButton = new JButton("Upload Image");

        // Add components to layout
        gbc.gridx = 0; gbc.gridy = 0; listCarFrame.add(shapeLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(shapeDropdown, gbc); // Add dropdown instead of text field

        gbc.gridx = 0; gbc.gridy = 1; listCarFrame.add(modelLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(modelField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; listCarFrame.add(yearLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(yearField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; listCarFrame.add(companyLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(companyField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; listCarFrame.add(engineCapacityLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(engineCapacityField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; listCarFrame.add(seatsLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(seatsField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; listCarFrame.add(horsepowerLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(horsepowerField, gbc);

        gbc.gridx = 0; gbc.gridy = 7; listCarFrame.add(torqueLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(torqueField, gbc);

        gbc.gridx = 0; gbc.gridy = 8; listCarFrame.add(priceLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 9; listCarFrame.add(kmDrivenLabel, gbc);
        gbc.gridx = 1; listCarFrame.add(kmDrivenField, gbc);

        JButton submitButton = new JButton("Submit Listing");
        gbc.gridx = 0; gbc.gridy = 10; gbc.gridwidth = 2;
        listCarFrame.add(submitButton, gbc);

        // Call controller to handle form submission
        CarRentalController controller = new CarRentalController();
        submitButton.addActionListener(e -> controller.handleSubmitCarListing(
                (String) shapeDropdown.getSelectedItem(), // Get selected shape
                modelField.getText().trim(),
                yearField.getText().trim(),
                companyField.getText().trim(),
                engineCapacityField.getText().trim(),
                seatsField.getText().trim(),
                horsepowerField.getText().trim(),
                torqueField.getText().trim(),
                priceField.getText().trim(),
                kmDrivenField.getText().trim(),
                listCarFrame
        ));

        listCarFrame.setVisible(true);
    }


    public void showInsurancePlans() {
        JFrame insuranceFrame = new JFrame("Insurance Plans");
        insuranceFrame.setSize(600, 400);
        insuranceFrame.setLocationRelativeTo(null);
        insuranceFrame.setLayout(new GridLayout(0, 1, 10, 10));

        String currentUserPlan = new CarRentalController().getCurrentUserPlan(LoginPage.currentLoggedInUser);

        String[][] insurancePlans = {
                {"Free Plan", "No coverage", "$0/month"},
                {"Standard Plan", "Covers up to $10,000", "$75/month"},
                {"Premium Plan", "Covers up to $20,000", "$100/month"}
        };

        for (String[] plan : insurancePlans) {
            JPanel planPanel = new JPanel();
            planPanel.setLayout(new GridLayout(3, 1));
            planPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            planPanel.setBackground(plan[0].equals(currentUserPlan) ? new Color(173, 216, 230) : new Color(230, 230, 250));

            JLabel planName = new JLabel("Plan: " + plan[0], JLabel.CENTER);
            JLabel coverage = new JLabel("Coverage: " + plan[1], JLabel.CENTER);
            JLabel price = new JLabel("Price: " + plan[2], JLabel.CENTER);

            JButton selectPlanButton = new JButton("Select");
            selectPlanButton.setFocusPainted(false);
            selectPlanButton.addActionListener(e -> {
                new CarRentalController().updateUserPlan(LoginPage.currentLoggedInUser, plan[0]);
                JOptionPane.showMessageDialog(insuranceFrame, "Your plan has been updated to: " + plan[0]);
                insuranceFrame.dispose(); // Close and refresh
                showInsurancePlans(); // Reload
            });

            planPanel.add(planName);
            planPanel.add(coverage);
            planPanel.add(price);
            planPanel.add(selectPlanButton);

            insuranceFrame.add(planPanel);
        }

        insuranceFrame.setVisible(true);
    }

    public void showBookingFormWithState(CarDTO car) {
        JFrame bookingFrame = new JFrame("Book a Car");
        bookingFrame.setSize(400, 500);
        bookingFrame.setLocationRelativeTo(null);
        bookingFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Book this Car", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bookingFrame.add(titleLabel, gbc);

        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Car Details"));

        detailsPanel.add(new JLabel("Company: " + car.getModel()));
        detailsPanel.add(new JLabel("Model: " + car.getModel()));
        detailsPanel.add(new JLabel("Year: " + car.getYear()));
        detailsPanel.add(new JLabel("Owner Phone: " + car.getOwnerPhone()));

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        bookingFrame.add(detailsPanel, gbc);

        JLabel nameLabel = new JLabel("Your Name:");
        JTextField nameField = new JTextField(15);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        bookingFrame.add(nameLabel, gbc);

        gbc.gridx = 1;
        bookingFrame.add(nameField, gbc);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(15);
        gbc.gridy = 3;
        gbc.gridx = 0;
        bookingFrame.add(phoneLabel, gbc);

        gbc.gridx = 1;
        bookingFrame.add(phoneField, gbc);

        JButton confirmButton = new JButton("Confirm Booking");
        confirmButton.setBackground(new Color(0, 123, 255));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(bookingFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    car.setRentalState(new BookedState());
                    MongoDBConnection dbConnection = new MongoDBConnection();
                    dbConnection.updateDocument("ListedCars", "carID", car.getCarID(), "state", "Booked");

                    JOptionPane.showMessageDialog(bookingFrame,
                            "Booking Confirmed!\n" +
                                    "Name: " + name + "\n" +
                                    "Phone: " + phone + "\n" +
                                    "Car: " + car.getModel() + "\n" +
                                    "Year: " + car.getYear() + "\n" +
                                    "Owner Phone: " + car.getOwnerPhone(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    bookingFrame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(bookingFrame, "Error during booking: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        bookingFrame.add(confirmButton, gbc);

        bookingFrame.setVisible(true);
    }

    public JFrame getMainFrame() {
        return frame;
    }
}
