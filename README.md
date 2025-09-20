# CarRental System

[![Java](https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=openjdk)](https://openjdk.java.net/)
[![MongoDB](https://img.shields.io/badge/MongoDB-3.12%2B-green?style=flat-square&logo=mongodb)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)
[![RMI](https://img.shields.io/badge/Architecture-RMI-red?style=flat-square)](https://docs.oracle.com/javase/tutorial/rmi/)

CarRental is a Java-based car rental management system built with a client-server architecture using RMI (Remote Method Invocation). The system provides a foundation for car rental operations including user authentication, car management, and basic rental processing.

## Features

### 🚗 Core Features
- **Car Management**: Add, list, and manage rental cars with basic specifications
- **Rental Processing**: Basic rental workflow with state management (Booked, Rented, Returned)
- **Car Listing**: Car owners can list their vehicles for rent
- **Multi-user Support**: Client-server architecture using Java RMI

### 👤 User Management
- **User Authentication**: Basic registration and login with username/password
- **Role-based Access**: Support for Renters, Car Owners (Listers), and Administrators
- **User Profiles**: Basic user profiles with contact information and insurance plans
- **Insurance Plans**: Insurance plan selection during user registration

### 💳 Payment System
- **Payment Method Classes**: Credit Card, PayPal, and Bank Transfer payment classes
- **State-based Processing**: Payment processing integrated with rental state management
- **Basic Payment Flow**: Payment method selection and processing framework

### 🎨 User Experience
- **Swing GUI**: Java Swing-based graphical user interface
- **Login System**: User registration and login screens
- **Car Browsing**: View available cars and car details
- **Car Listing Form**: Form for car owners to list their vehicles
- **Insurance Plans Display**: Basic insurance plan information

### 🔧 Technical Features
- **Distributed Architecture**: Client-server separation using Java RMI
- **Database Integration**: MongoDB for data persistence
- **State Pattern**: Rental state management (BookedState, RentedState, ReturnedState)
- **Observer Pattern**: Basic notification system framework
- **Design Patterns**: Facade, DTO, and Singleton patterns implemented

## Tech Stack

### 🖥️ Backend
- **Language**: Java 8+
- **Architecture**: Java RMI (Remote Method Invocation)
- **Database**: MongoDB (localhost:27017)
- **Design Patterns**: State, Observer, Facade, DTO, Singleton

### 🖼️ Frontend
- **GUI Framework**: Java Swing
- **Layout Management**: Absolute positioning with custom layouts
- **Event Handling**: Action listeners for button interactions

### 📚 Key Dependencies
- **MongoDB Driver**: mongodb-driver-3.12.11.jar
- **BSON Processing**: bson-3.12.11.jar
- **JSON Processing**: gson-2.10.jar
- **MongoDB Core**: mongodb-driver-core-3.12.11.jar

## Getting Started

For detailed installation and setup instructions, see [DEVELOPER_GUIDE.md](./DEVELOPER_GUIDE.md).

**Quick Start:**
1. Clone the repository and ensure MongoDB is running
2. Compile the project: `javac -cp "lib/*:src" -d out/production/untitled1 src/**/*.java`
3. Start the RMI Server: `java -cp "lib/*:out/production/untitled1" server.server.RMIServer`
4. Run the Client: `java -cp "lib/*:out/production/untitled1" client.Main`

The system works with MongoDB immediately - no additional setup required for basic functionality.

## 📸 Screenshots

### Login Screen
![Login Screen](screenshots/login-screen.png)
*User authentication interface with role selection (Renter/Lister/Admin)*

### Car Browsing
![Car Browsing](screenshots/car-browsing.png)
*Browse available cars with detailed specifications and rental options*

### Car Listing Form
![Car Listing Form](screenshots/car-listing-form.png)
*Car owners can list their vehicles with comprehensive details*

### Main Dashboard
![Main Dashboard](screenshots/main-dashboard.png)
*Main interface showing role-based navigation and system features*

*Note: Screenshots will be added to the `screenshots/` directory once the application is running.*

## 🚀 Future Development

For planned features and development roadmap, see [ROADMAP.md](./ROADMAP.md).

## Contributing

See [DEVELOPER_GUIDE.md](./DEVELOPER_GUIDE.md) for contributing guidelines.

## Acknowledgments

- MongoDB team for the excellent NoSQL database
- Java RMI framework for distributed computing capabilities
- Swing framework for GUI development
- GSON library for JSON processing
