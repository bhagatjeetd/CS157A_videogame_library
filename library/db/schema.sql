USE gamelibrary_db;

-- Video game titles
CREATE TABLE IF NOT EXISTS Game (
                                    GameID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                    Title VARCHAR(255) NOT NULL,
                                    Genre VARCHAR(255) NOT NULL,
                                    ESRB VARCHAR(5) NOT NULL,
                                    ReleaseDate TIMESTAMP NOT NULL,
                                    Publisher VARCHAR(255) NOT NULL,
                                    BaseRentalPrice DECIMAL(10,2) NOT NULL,
                                    CHECK (ESRB IN ('E','E10+','T','M','AO','RP','EC'))
);

-- Available platforms
CREATE TABLE IF NOT EXISTS Platforms (
                                         PlatformID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                         PlatformName VARCHAR(255) NOT NULL
);

-- Many-to-many: which game is on which platform
CREATE TABLE IF NOT EXISTS GamePlatforms (
                                             GameID INTEGER NOT NULL,
                                             PlatformID INTEGER NOT NULL,
                                             FOREIGN KEY (GameID) REFERENCES Game(GameID),
                                             FOREIGN KEY (PlatformID) REFERENCES Platforms(PlatformID),
                                             PRIMARY KEY (GameID, PlatformID)
);

-- Registered customers
CREATE TABLE IF NOT EXISTS Customers (
                                         CustomerID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                         CustomerName VARCHAR(255) NOT NULL,
                                         Email VARCHAR(255) NOT NULL,
                                         Phone VARCHAR(10) NOT NULL,
                                         Address VARCHAR(255) NOT NULL,
                                         Username VARCHAR(255) NOT NULL,
                                         PasswordHash VARCHAR(255) NOT NULL,
                                         RegistrationDate TIMESTAMP NOT NULL
);

-- Staff members who process orders
CREATE TABLE IF NOT EXISTS Staff (
                                     StaffID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                     StaffName VARCHAR(255) NOT NULL,
                                     Email VARCHAR(255) NOT NULL,
                                     Role VARCHAR(255) NOT NULL,
                                     Username VARCHAR(255) NOT NULL,
                                     PasswordHash VARCHAR(255) NOT NULL,
                                     HireDate TIMESTAMP NOT NULL
);

-- Rental orders placed by customers
CREATE TABLE IF NOT EXISTS Orders (
                                      OrderID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                      CustomerID INTEGER NOT NULL,
                                      OrderDate DATETIME NOT NULL,
                                      DueDate TIMESTAMP NOT NULL,
                                      ReturnDate TIMESTAMP NOT NULL,
                                      TotalFee DECIMAL(10,2) NOT NULL,
                                      OrderStatus VARCHAR(255) NOT NULL,
                                      ProcessedBy INTEGER NOT NULL,
                                      FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
                                      FOREIGN KEY (ProcessedBy) REFERENCES Staff(StaffID)
);

-- Individual games within each order
CREATE TABLE IF NOT EXISTS OrderItems (
                                          OrderItemID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                          OrderID INTEGER NOT NULL,
                                          GameID INTEGER NOT NULL,
                                          RentalFee DECIMAL(10,2) NOT NULL,
                                          FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
                                          FOREIGN KEY (GameID) REFERENCES Game(GameID)
);

-- Wishlists for customers
CREATE TABLE IF NOT EXISTS Wishlists (
                                         WishlistID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                         CustomerID INTEGER NOT NULL,
                                         GameID INTEGER NOT NULL,
                                         AddedDate TIMESTAMP NOT NULL,
                                         FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
                                         FOREIGN KEY (GameID) REFERENCES Game(GameID),
                                         CONSTRAINT unique_wishlist UNIQUE (CustomerID, GameID)
);
