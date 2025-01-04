INSERT INTO team (name, balance, commission_percentage) VALUES
                                                            ('Team1', 400000.00, 5.0),
                                                            ('Team2', 120000.00, 3.5),
                                                            ('Team3', 950000.00, 4.0),
                                                            ('Team4', 810000.00, 6.5),
                                                            ('Team5', 420000.00, 2.0),
                                                            ('Team6', 130000.00, 7.0),
                                                            ('Team7', 900000.00, 1.5),
                                                            ('Team8', 515000.00, 8.0);


-- Вставка гравців, які належать до команд
INSERT INTO player (first_name, last_name, date_of_birth, debut_date, team_id) VALUES
                                                                                   ('John', 'Doe', '1995-03-15', '2015-08-01', 1),
                                                                                   ('Alice', 'Smith', '1998-07-10', '2018-05-20', 1),
                                                                                   ('Bob', 'Brown', '1993-12-05', '2013-03-25', 2),
                                                                                   ('Emma', 'Johnson', '2000-01-22', '2020-11-15', 2),
                                                                                   ('Chris', 'Davis', '1997-09-18', '2017-06-05', 3),
                                                                                   ('Olivia', 'Wilson', '1996-05-30', '2016-09-10', 3),
                                                                                   ('Liam', 'Taylor', '1994-11-12', '2014-07-25', 4),
                                                                                   ('Sophia', 'Moore', '1999-03-08', '2019-02-18', 5),
                                                                                   ('James', 'Anderson', '2001-07-25', '2021-06-12', 6),
                                                                                   ('Isabella', 'Thomas', '1992-02-14', '2012-10-30', 7),
                                                                                   ('William', 'Jackson', '1990-08-22', '2010-05-01', 8);

-- Вставка гравців без команди
INSERT INTO player (first_name, last_name, date_of_birth, debut_date, team_id) VALUES
                                                                                   ('Ethan', 'Martin', '1998-04-11', '2018-01-20', NULL),
                                                                                   ('Mia', 'Lee', '2002-12-09', '2022-03-15', NULL),
                                                                                   ('Michael', 'White', '1995-06-19', '2015-08-05', NULL),
                                                                                   ('Charlotte', 'Harris', '1997-09-25', '2017-12-10', NULL);
