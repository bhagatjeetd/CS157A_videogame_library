USE gamelibrary_db;

-- Populate Game
INSERT INTO Game (Title, Genre, ESRB, ReleaseDate, Publisher, BaseRentalPrice) VALUES
                                                                                   ('Halo Infinite', 'Shooter', 'M', '2021-12-08 00:00:00', 'Xbox Game Studios', 5.99),
                                                                                   ('God of War', 'Action', 'M', '2018-04-20 00:00:00', 'Sony Interactive Entertainment', 4.99),
                                                                                   ('Mario Kart 8', 'Racing', 'E', '2014-05-30 00:00:00', 'Nintendo', 3.99),
                                                                                   ('The Witcher 3', 'RPG', 'M', '2015-05-19 00:00:00', 'CD Projekt', 5.99),
                                                                                   ('Minecraft', 'Sandbox', 'E10+', '2011-11-18 00:00:00', 'Mojang', 2.99),
                                                                                   ('FIFA 22', 'Sports', 'E', '2021-10-01 00:00:00', 'EA Sports', 3.49),
                                                                                   ('Call of Duty: MW', 'Shooter', 'M', '2019-10-25 00:00:00', 'Activision', 5.49),
                                                                                   ('Elden Ring', 'RPG', 'M', '2022-02-25 00:00:00', 'Bandai Namco', 6.49),
                                                                                   ('Overwatch 2', 'Shooter', 'T', '2022-10-04 00:00:00', 'Blizzard', 4.49),
                                                                                   ('Stardew Valley', 'Simulation', 'E10+', '2016-02-26 00:00:00', 'ConcernedApe', 2.49),
                                                                                   ('Horizon Zero Dawn', 'RPG', 'T', '2017-02-28 00:00:00', 'Sony', 4.99),
                                                                                   ('Among Us', 'Party', 'E10+', '2018-06-15 00:00:00', 'InnerSloth', 1.99),
                                                                                   ('Valorant', 'Shooter', 'T', '2020-06-02 00:00:00', 'Riot Games', 4.25),
                                                                                   ('GTA V', 'Action', 'M', '2013-09-17 00:00:00', 'Rockstar Games', 5.99),
                                                                                   ('Zelda: BOTW', 'Adventure', 'E10+', '2017-03-03 00:00:00', 'Nintendo', 5.75);

-- Platforms
INSERT INTO Platforms (PlatformName) VALUES
                                         ('PC'), ('PS5'), ('Xbox Series X'), ('Nintendo Switch'), ('PS4'), ('Xbox One'),
                                         ('Xbox 360'), ('PS3'), ('PS2'), ('PS1'), ('Nintendo Wii'), ('Nintendo DS'),
                                         ('Nintendo GameCube'), ('PS Vita'), ('Nintendo 3DS'), ('Steam Deck');

-- GamePlatforms
INSERT INTO GamePlatforms (GameID, PlatformID) VALUES
                                                   (1, 3), (2, 2), (3, 4), (4, 1), (5, 1), (6, 1), (7, 3), (8, 1), (9, 1),
                                                   (10, 1), (11, 2), (12, 1), (13, 1), (14, 3), (15, 4);

-- TODO create init data for staff, customers, staff, orders, orderitems, wishlists
-- 4) Populate Customers (15 entries)
INSERT INTO Customers (CustomerName,Email,Phone,Address,Username,PasswordHash,RegistrationDate) VALUES
                                                                                                    ('Mario','Mario1@example.com','555-01001','101 Game St','user1','hash1','2022-02-02 00:00:00'),
                                                                                                    ('Luigi','Luigi2@example.com','555-01002','102 Game St','user2','hash2','2022-03-03 00:00:00'),
                                                                                                    ('Peach','Peach3@example.com','555-01003','103 Game St','user3','hash3','2022-04-04 00:00:00'),
                                                                                                    ('Daisy','Daisy4@example.com','555-01004','104 Game St','user4','hash4','2022-05-05 00:00:00'),
                                                                                                    ('Bowser','Bowser5@example.com','555-01005','105 Game St','user5','hash5','2022-06-06 00:00:00'),
                                                                                                    ('Boo','Boo6@example.com','555-01006','106 Game St','user6','hash6','2022-07-07 00:00:00'),
                                                                                                    ('Koopa','Koopa7@example.com','555-01007','107 Game St','user7','hash7','2022-08-08 00:00:00'),
                                                                                                    ('ShyGuy','ShyGuy8@example.com','555-01008','108 Game St','user8','hash8','2022-09-09 00:00:00'),
                                                                                                    ('Zelda','Zelda9@example.com','555-01009','109 Game St','user9','hash9','2022-10-10 00:00:00'),
                                                                                                    ('Link','Link10@example.com','555-01010','110 Game St','user10','hash10','2022-11-11 00:00:00'),
                                                                                                    ('Ganondorf','Ganondorf11@example.com','555-01011','111 Game St','user11','hash11','2022-12-12 00:00:00'),
                                                                                                    ('Godwhyn','Godwhyn12@example.com','555-01012','112 Game St','user12','hash12','2022-01-13 00:00:00'),
                                                                                                    ('Mohg','Mohg13@example.com','555-01013','113 Game St','user13','hash13','2022-02-14 00:00:00'),
                                                                                                    ('Radahn','Radahn@example.com','555-01014','114 Game St','user14','hash14','2022-03-15 00:00:00'),
                                                                                                    ('Messmer','Messmer@example.com','555-01015','115 Game St','user15','hash15','2022-04-16 00:00:00');

-- 5) Populate Staff (15 entries)
INSERT INTO Staff (StaffName,Email,Role,Username,PasswordHash,HireDate) VALUES
                                                                            ('Staff1','staff1@example.com','Admin','staffuser1','staffhash1','2020-02-02 00:00:00'),
                                                                            ('Staff2','staff2@example.com','Clerk','staffuser2','staffhash2','2020-03-03 00:00:00'),
                                                                            ('Staff3','staff3@example.com','Admin','staffuser3','staffhash3','2020-04-04 00:00:00'),
                                                                            ('Staff4','staff4@example.com','Clerk','staffuser4','staffhash4','2020-05-05 00:00:00'),
                                                                            ('Staff5','staff5@example.com','Admin','staffuser5','staffhash5','2020-06-06 00:00:00'),
                                                                            ('Staff6','staff6@example.com','Clerk','staffuser6','staffhash6','2020-07-07 00:00:00'),
                                                                            ('Staff7','staff7@example.com','Admin','staffuser7','staffhash7','2020-08-08 00:00:00'),
                                                                            ('Staff8','staff8@example.com','Clerk','staffuser8','staffhash8','2020-09-09 00:00:00'),
                                                                            ('Staff9','staff9@example.com','Admin','staffuser9','staffhash9','2020-10-10 00:00:00'),
                                                                            ('Staff10','staff10@example.com','Clerk','staffuser10','staffhash10','2020-11-11 00:00:00'),
                                                                            ('Staff11','staff11@example.com','Admin','staffuser11','staffhash11','2020-12-12 00:00:00'),
                                                                            ('Staff12','staff12@example.com','Clerk','staffuser12','staffhash12','2020-01-13 00:00:00'),
                                                                            ('Staff13','staff13@example.com','Admin','staffuser13','staffhash13','2020-02-14 00:00:00'),
                                                                            ('Staff14','staff14@example.com','Clerk','staffuser14','staffhash14','2020-03-15 00:00:00'),
                                                                            ('Staff15','staff15@example.com','Admin','staffuser15','staffhash15','2020-04-16 00:00:00');

-- 6) Populate Orders (15 entries)
INSERT INTO Orders (CustomerID,OrderDate,DueDate,ReturnDate,TotalFee,OrderStatus,ProcessedBy) VALUES
                                                                                                  (1,'2023-02-02 00:00:00','2023-03-03 00:00:00','2023-04-04 00:00:00',3.49,'Rented',1),
                                                                                                  (2,'2023-03-03 00:00:00','2023-04-04 00:00:00','2023-05-05 00:00:00',3.99,'Returned',2),
                                                                                                  (3,'2023-04-04 00:00:00','2023-05-05 00:00:00','2023-06-06 00:00:00',4.49,'Rented',3),
                                                                                                  (4,'2023-05-05 00:00:00','2023-06-06 00:00:00','2023-07-07 00:00:00',4.99,'Returned',4),
                                                                                                  (5,'2023-06-06 00:00:00','2023-07-07 00:00:00','2023-08-08 00:00:00',5.49,'Rented',5),
                                                                                                  (6,'2023-07-07 00:00:00','2023-08-08 00:00:00','2023-09-09 00:00:00',5.99,'Returned',6),
                                                                                                  (7,'2023-08-08 00:00:00','2023-09-09 00:00:00','2023-10-10 00:00:00',6.49,'Rented',7),
                                                                                                  (8,'2023-09-09 00:00:00','2023-10-10 00:00:00','2023-11-11 00:00:00',6.99,'Returned',8),
                                                                                                  (9,'2023-10-10 00:00:00','2023-11-11 00:00:00','2023-12-12 00:00:00',7.49,'Rented',9),
                                                                                                  (10,'2023-11-11 00:00:00','2023-12-12 00:00:00','2024-01-13 00:00:00',7.99,'Returned',10),
                                                                                                  (11,'2023-12-12 00:00:00','2024-01-13 00:00:00','2024-02-14 00:00:00',8.49,'Rented',11),
                                                                                                  (12,'2024-01-13 00:00:00','2024-02-14 00:00:00','2024-03-15 00:00:00',8.99,'Returned',12),
                                                                                                  (13,'2024-02-14 00:00:00','2024-03-15 00:00:00','2024-04-16 00:00:00',9.49,'Rented',13),
                                                                                                  (14,'2024-03-15 00:00:00','2024-04-16 00:00:00','2024-05-17 00:00:00',9.99,'Returned',14),
                                                                                                  (15,'2024-04-16 00:00:00','2024-05-17 00:00:00','2024-06-18 00:00:00',10.49,'Rented',15);

-- 7) Populate OrderItems (15 entries)
INSERT INTO OrderItems (OrderID,GameID,RentalFee) VALUES
                                                      (1,1,2.24),
                                                      (2,2,2.49),
                                                      (3,3,2.74),
                                                      (4,4,2.99),
                                                      (5,5,3.24),
                                                      (6,6,3.49),
                                                      (7,7,3.74),
                                                      (8,8,3.99),
                                                      (9,9,4.24),
                                                      (10,10,4.49),
                                                      (11,11,4.74),
                                                      (12,12,4.99),
                                                      (13,13,5.24),
                                                      (14,14,5.49),
                                                      (15,15,5.74);

-- 8) Populate Wishlists (15 entries)
INSERT INTO Wishlists (CustomerID,GameID,AddedDate) VALUES
                                                        (1,15,'2024-05-07 00:00:00'),
                                                        (2,14,'2024-06-08 00:00:00'),
                                                        (3,13,'2024-07-09 00:00:00'),
                                                        (4,12,'2024-08-10 00:00:00'),
                                                        (5,11,'2024-09-11 00:00:00'),
                                                        (6,10,'2024-10-12 00:00:00'),
                                                        (7,9 ,'2024-11-13 00:00:00'),
                                                        (8,8 ,'2024-12-14 00:00:00'),
                                                        (9,7 ,'2025-01-15 00:00:00'),
                                                        (10,6,'2025-02-16 00:00:00'),
                                                        (11,5,'2025-03-17 00:00:00'),
                                                        (12,4,'2025-04-18 00:00:00'),
                                                        (13,3,'2025-05-19 00:00:00'),
                                                        (14,2,'2025-06-20 00:00:00'),
                                                        (15,1,'2025-07-21 00:00:00');