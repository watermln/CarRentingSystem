package client.gui;

import client.control.CarRentalController;
import org.bson.Document;
import server.database.MongoDBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JFrame frame;
    public static String currentLoggedInUser = null; // Static variable to hold the username


    private void onLoginSuccess() {

        GUI homePage = new GUI();
        homePage.createAndShowGUI();
    }

    public void createAndShowLoginGUI() {
        JFrame loginFrame = new JFrame("Login/Sign Up Page");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(400, 450);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); // Use absolute positioning for components
        formPanel.setBounds(0, 0, 400, 450);
        formPanel.setBackground(new Color(220, 220, 255)); // Default color for Login as Renter
        loginFrame.add(formPanel);

        JLabel titleLabel = new JLabel("Login as Renter", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        formPanel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 70, 100, 25);
        formPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 70, 200, 25);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 25);
        formPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 25);
        formPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(20, 150, 120, 25);
        confirmPasswordLabel.setVisible(false);
        formPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(150, 150, 200, 25);
        confirmPasswordField.setVisible(false);
        formPanel.add(confirmPasswordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 190, 100, 25);
        roleLabel.setVisible(false);
        formPanel.add(roleLabel);

        JComboBox<String> roleDropdown = new JComboBox<>(new String[]{"Renter", "Lister"});
        roleDropdown.setBounds(150, 190, 200, 25);
        roleDropdown.setVisible(false);
        formPanel.add(roleDropdown);

        JLabel planLabel = new JLabel("Insurance Plan:");
        planLabel.setBounds(50, 230, 120, 25);
        planLabel.setVisible(false);
        formPanel.add(planLabel);

        JComboBox<String> planDropdown = new JComboBox<>(new String[]{"Free", "Premium", "Platinum"});
        planDropdown.setBounds(150, 230, 200, 25);
        planDropdown.setVisible(false);
        formPanel.add(planDropdown);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 270, 120, 25);
        phoneLabel.setVisible(false);
        formPanel.add(phoneLabel);

        JTextField phoneField = new JTextField(); // Changed from JPasswordField to JTextField
        phoneField.setBounds(150, 270, 200, 25);
        phoneField.setVisible(false);
        formPanel.add(phoneField);

        JToggleButton roleToggleButton = new JToggleButton("Renter");
        roleToggleButton.setFocusPainted(false);
        roleToggleButton.setBounds(145, 310, 100, 30); // Adjusted width to 120 for better spacing
        formPanel.add(roleToggleButton);

        JButton submitButton = new JButton("Login");
        submitButton.setFocusPainted(false);
        submitButton.setBounds(250, 310, 100, 30); // Adjusted horizontally for alignment
        submitButton.setFocusPainted(false);
        formPanel.add(submitButton);

        JToggleButton authToggleButton = new JToggleButton("Login/Sign Up");
        authToggleButton.setBounds(20, 310, 120, 30); // Aligned horizontally with proper spacing
        authToggleButton.setFocusPainted(false);
        formPanel.add(authToggleButton);

        CarRentalController controller = new CarRentalController();

        // Role Toggle Button ActionListener
        roleToggleButton.addActionListener(e ->
                controller.handleRoleToggle(roleToggleButton, titleLabel, formPanel)
        );

        // Auth Toggle Button ActionListener
        authToggleButton.addActionListener(e ->
                controller.handleAuthToggle(authToggleButton, titleLabel, submitButton, confirmPasswordLabel,
                        confirmPasswordField, phoneLabel, phoneField, roleLabel, roleDropdown, planDropdown, planLabel, roleToggleButton)
        );

        // Submit Button ActionListener
        submitButton.addActionListener(e -> {
            boolean success = controller.handleSubmitButton(loginFrame, usernameField, phoneField,
                    passwordField, confirmPasswordField, roleDropdown, planDropdown, authToggleButton, roleToggleButton);
            if (success) {
                onLoginSuccess();
                loginFrame.dispose();
            }
        });

        loginFrame.setVisible(true);

    }
    public JFrame getMainFrame() {
        return frame;
    }

}