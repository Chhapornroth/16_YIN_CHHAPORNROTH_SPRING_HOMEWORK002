INSERT INTO instructors VALUES
                            (DEFAULT, 'Tan Dara', 'tan.dara@gmail.com'),
                            (DEFAULT, 'Kheng Sovannak', 'kheng.sovannak@gmail.com'),
                            (DEFAULT, 'Chhun Rothanakkosal', 'chhun.rothanakkosal@gmail.com'),
                            (DEFAULT, 'Srey Sitharo', 'srey.sitharo@gmial.com');

INSERT INTO students VALUES
                         (DEFAULT, 'Luke Dunphy', 'luke.danphy@gmail.com', '012-345-678'),
                         (DEFAULT, 'Sebastian Milder', 'sebastian.milder@gmail.com', '098-765-432'),
                         (DEFAULT, 'Mia Dolan', 'mia.dolan@gmail.com', '432-991-123'),
                         (DEFAULT, 'David White', 'david.white@example.com', '456-789-0123'),
                         (DEFAULT, 'Emma Wilson', 'emma.wilson@example.com', '567-890-1234'),
                         (DEFAULT, 'Frank Thomas', 'frank.thomas@example.com', '678-901-2345'),
                         (DEFAULT, 'Grace Hall', 'grace.hall@example.com', '789-012-3456'),
                         (DEFAULT, 'Henry Adams', 'henry.adams@example.com', '890-123-4567'),
                         (DEFAULT, 'Isabella Scott', 'isabella.scott@example.com', '901-234-5678'),
                         (DEFAULT, 'Jack Turner', 'jack.turner@example.com', '012-345-6789');

INSERT INTO courses VALUES
                        (DEFAULT, 'Web Design', 'Covers on the basic and advanced of HTML, CSS, and Javascript.', 3),
                        (DEFAULT, 'Database', 'Focuses on database modeling, and PostgresSQL.', 4),
                        (DEFAULT, 'Core of Java', 'Covering OOP, exception handling, multithreading, collections, file handling, and JDBC.', 1),
                        (DEFAULT, 'Github', 'Provides a centralized place for storing code, managing repositories, and facilitating team collaboration.', 2),
                        (DEFAULT, 'Basic Spring Boot', 'Providing production-ready, stand-alone applications with minimal configuration.', 2);

INSERT INTO student_courses VALUES
                                (DEFAULT, 1, 1),
                                (DEFAULT, 1, 3),
                                (DEFAULT, 1, 5),
                                (DEFAULT, 2, 5),
                                (DEFAULT, 3, 1),
                                (DEFAULT, 3, 2),
                                (DEFAULT, 4, 4),
                                (DEFAULT, 5, 5),
                                (DEFAULT, 6, 2),
                                (DEFAULT, 6, 3),
                                (DEFAULT, 7, 5),
                                (DEFAULT, 8, 2),
                                (DEFAULT, 9, 4),
                                (DEFAULT, 9, 5),
                                (DEFAULT, 10, 5);
