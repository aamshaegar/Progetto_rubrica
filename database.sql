CREATE TABLE IF NOT EXISTS Utente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Informazioni (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idUtente INT NOT NULL,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    indirizzo VARCHAR(100),
    telefono VARCHAR(20),
    eta INT,
    FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE CASCADE
);



-- Insert sample users
INSERT INTO Utente (username, password) VALUES 
('mario', '1234'),
('luigi', '5678'),
('anna', 'abcd'),
('sofia', 'qwerty'),
('marco', 'pass123');



-- Mario
INSERT INTO Informazioni (idUtente, nome, cognome, indirizzo, telefono, eta) VALUES
(1, 'Mario', 'Rossi', 'Via Roma 1', '1234567890', 30),
(1, 'Luca', 'Bianchi', 'Via Milano 2', '0987654321', 25),
(1, 'Giovanni', 'Neri', 'Via Torino 10', '3216549870', 40);


-- Luigi
INSERT INTO Informazioni (idUtente, nome, cognome, indirizzo, telefono, eta) VALUES
(2, 'Luigi', 'Verdi', 'Via Napoli 3', '1122334455', 28),
(2, 'Elena', 'Gialli', 'Via Firenze 5', '6677889900', 32);


-- Anna
INSERT INTO Informazioni (idUtente, nome, cognome, indirizzo, telefono, eta) VALUES
(3, 'Anna', 'Ruggiero', 'Corso Venezia 7', '9988776655', 29),
(3, 'Francesco', 'Caro', 'Via Bologna 12', '4455667788', 35),
(3, 'Sara', 'Neri', 'Via Trieste 4', '5566778899', 24);


-- Sofia
INSERT INTO Informazioni (idUtente, nome, cognome, indirizzo, telefono, eta) VALUES
(4, 'Sofia', 'Bianchi', 'Piazza Duomo 8', '3344556677', 22);


-- Marco
INSERT INTO Informazioni (idUtente, nome, cognome, indirizzo, telefono, eta) VALUES
(5, 'Marco', 'Ferrari', 'Viale Libertà 15', '1231231234', 38),
(5, 'Alessio', 'Conti', 'Via Genova 6', '7778889990', 41);
