# CarRental – Developer Guide
Technical reference for setup, configuration, APIs, customization, and deployment.

---

## ⚙️ Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MongoDB Server 3.12+ (running on localhost:27017)
- IDE (IntelliJ IDEA, Eclipse, or similar)
- Git for version control

### Installation Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/CarRental.git
   cd CarRental
   ```

2. **Set up MongoDB**
   ```bash
   # Install MongoDB (if not already installed)
   # Start MongoDB service
   mongod --dbpath /path/to/your/data/directory
   ```

3. **Configure the database**
   - Ensure MongoDB is running on `localhost:27017`
   - The application will automatically create the required database and collections
   - Default database name: `your_database_name` (configurable in `MongoDBConnection.java`)

4. **Compile the project**
   ```bash
   # Navigate to the project directory
   javac -cp "lib/*:src" -d out/production/untitled1 src/**/*.java
   ```

5. **Start the RMI Server**
   ```bash
   java -cp "lib/*:out/production/untitled1" server.server.RMIServer
   ```

6. **Run the Client Application**
   ```bash
   java -cp "lib/*:out/production/untitled1" client.Main
   ```

---

## 🔧 Configuration

### Database Configuration

`src/server/database/MongoDBConnection.java`:

```java
public MongoDBConnection() {
    mongoClient = new MongoClient("localhost", 27017);
    database = mongoClient.getDatabase("your_database_name");
    gson = new Gson();
}
```

### RMI Configuration

`src/server/server/RMIServer.java`:

```java
// RMI Registry runs on port 1099
Registry registry = LocateRegistry.createRegistry(1099);
registry.rebind("CarService", carService);
registry.rebind("RentalService", rentalService);
registry.rebind("CustomerService", customerService);
```

### Client Connection

`src/client/client/RMIClient.java`:

```java
CarRemote carService = (CarRemote) Naming.lookup("rmi://localhost:1099/CarService");
RentalRemote rentalService = (RentalRemote) Naming.lookup("rmi://localhost:1099/RentalService");
CustomerRemote customerService = (CustomerRemote) Naming.lookup("rmi://localhost:1099/CustomerService");
```

---

## 🧪 RMI Services

### Car Services (RMI)

* `getAvailableCars()` → retrieve all available cars from MongoDB
* `addCar(CarDTO car)` → add new car to system
* `removeCar(String carId)` → remove car from system

### Customer Services (RMI)

* `registerCustomer(CustomerDTO customer)` → register new customer
* `getCustomer(String customerId)` → retrieve customer information

### Rental Services (RMI)

* `createRental(RentalDTO rental)` → create new rental booking
* `getRental(String rentalId)` → retrieve rental information
* `updateRentalStatus(String rentalId, String status)` → update rental status

### Database Collections

* **users** → user authentication and profiles
* **ListedCars** → car listings from owners
* **cars** → available cars for rental

---

## 🎨 Customization

### Adding New Car Types

1. Create new car class in `src/server/business/`
2. Extend the abstract `Car` class
3. Implement required methods (`showDetails()`)
4. Update car creation logic in `CarRemoteImpl`

### Adding New Payment Methods

1. Create new payment class in `src/server/payment/`
2. Implement `PaymentMethod` interface
3. Add payment processing logic
4. Update payment selection in GUI

### Adding New GUI Screens

1. Create new GUI class in `src/client/gui/`
2. Add navigation logic in `CarRentalController`
3. Update main GUI with new buttons/options
4. Implement event handlers

### Database Operations

1. Update `MongoDBConnection` class for new collections
2. Modify DTOs in `src/shared/dto/`
3. Update RMI implementations in `src/server/remote/impl/`
4. Test data persistence and retrieval

---

## 🐛 Troubleshooting (Common Issues)

**RMI Connection Failed**
```bash
# Check if RMI server is running
netstat -an | grep 1099

# Restart RMI server
java -cp "lib/*:out/production/untitled1" server.server.RMIServer
```

**MongoDB Connection Error**
```bash
# Check MongoDB status
sudo systemctl status mongod

# Start MongoDB service
sudo systemctl start mongod

# Check MongoDB logs
tail -f /var/log/mongodb/mongod.log
```

**Compilation Errors**
```bash
# Clean and recompile
rm -rf out/production/untitled1/*
javac -cp "lib/*:src" -d out/production/untitled1 src/**/*.java
```

**GUI Not Displaying Properly**
- Check Java Swing dependencies and versions
- Ensure proper thread handling for GUI updates
- Verify display settings and screen resolution compatibility

---

## 📝 Development Notes

* **Architecture**: Client-Server with RMI communication
* **State Management**: State pattern for rental lifecycle (BookedState, RentedState, ReturnedState)
* **Testing**: Basic unit tests in `src/test/` directory (GUITest, MongoDBConnectionTest)
* **Database**: MongoDB with basic CRUD operations
* **Security**: Basic username/password authentication (passwords stored in plain text)

---

## 🔄 Deployment

### Production Setup

1. **Database Configuration**
   ```java
   // Update MongoDB connection for production
   mongoClient = new MongoClient("production-mongodb-host", 27017);
   database = mongoClient.getDatabase("carrental_production");
   ```

2. **RMI Server Deployment**
   ```bash
   # Run as service with proper logging
   nohup java -cp "lib/*:out/production/untitled1" server.server.RMIServer > server.log 2>&1 &
   ```

3. **Client Distribution**
   ```bash
   # Create JAR file for easy distribution
   jar -cvf CarRentalClient.jar -C out/production/untitled1 .
   ```

### Environment Variables

```bash
# Set environment variables for different environments
export MONGODB_HOST=localhost
export MONGODB_PORT=27017
export RMI_PORT=1099
export DATABASE_NAME=carrental
```

---

## 📚 Resources

* [Java RMI Documentation](https://docs.oracle.com/javase/tutorial/rmi/)
* [MongoDB Java Driver](https://mongodb.github.io/mongo-java-driver/)
* [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
* [GSON Documentation](https://github.com/google/gson)
* [Design Patterns in Java](https://refactoring.guru/design-patterns/java)

---

## 🧪 Testing

### Running Tests

```bash
# Compile test classes
javac -cp "lib/*:out/production/untitled1:src" -d out/production/untitled1 src/test/**/*.java

# Run GUI tests
java -cp "lib/*:out/production/untitled1" test.client.GUITest

# Run database tests
java -cp "lib/*:out/production/untitled1" test.server.MongoDBConnectionTest
```

### Test Coverage
- **Unit Tests**: Individual component testing
- **Integration Tests**: Client-server communication testing
- **GUI Tests**: User interface functionality testing
- **Database Tests**: Data persistence and retrieval testing

---

## 📋 Code Style Guidelines

### Naming Conventions
- **Classes**: PascalCase (e.g., `CarRentalController`)
- **Methods**: camelCase (e.g., `getAvailableCars()`)
- **Variables**: camelCase (e.g., `carId`, `rentalStatus`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_PORT`)

### File Organization
- **Business Logic**: `src/server/business/`
- **GUI Components**: `src/client/gui/`
- **Remote Interfaces**: `src/shared/remote/`
- **Data Transfer Objects**: `src/shared/dto/`
- **Database Access**: `src/server/database/`

### Documentation
- Add JavaDoc comments for all public methods
- Include parameter descriptions and return values
- Document exceptions that methods may throw
- Provide usage examples for complex methods

---

## 🔒 Security Considerations

### Authentication
- **Current State**: Passwords stored in plain text (see MongoDBConnection.java line 100)
- **Needed**: Implement password hashing (bcrypt/Argon2)
- **Needed**: Add session management for user authentication
- **Current**: Basic logout functionality exists

### Data Validation
- **Needed**: Validate all user inputs before processing
- **Needed**: Sanitize database queries to prevent injection
- **Current**: Basic error handling with user-friendly messages

### Network Security
- **Current**: RMI communication on localhost
- **Needed**: Consider SSL/TLS for production RMI communication
- **Needed**: Implement proper firewall rules for RMI ports

---

## 📊 Performance Optimization

### Database Optimization
- Implement connection pooling for MongoDB
- Add database indexes for frequently queried fields
- Use efficient queries and avoid N+1 problems

### Memory Management
- Implement proper resource cleanup in database connections
- Use object pooling for frequently created objects
- Monitor memory usage and implement garbage collection tuning

### GUI Performance
- Use SwingWorker for long-running operations
- Implement proper threading for GUI updates
- Optimize rendering with double buffering where needed
