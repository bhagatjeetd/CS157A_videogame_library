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