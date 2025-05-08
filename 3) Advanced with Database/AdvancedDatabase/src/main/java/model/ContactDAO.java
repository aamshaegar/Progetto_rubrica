package model;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ContactDAO {

    private final String propertiesPath = "./config.properties";
    private Utente utente;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;


    public ContactDAO(Utente user) {
        this.utente = user;
        loadConfig();
    }

    private void loadConfig(){
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesPath)) {
            props.load(input);
            this.dbUrl = props.getProperty("db.url");
            this.dbUser = props.getProperty("db.user");
            this.dbPassword = props.getProperty("db.password");

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                System.out.println("Errore nel caricamento del file di configurazione. Controlla la sintassi");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento del file di configurazione: " + propertiesPath);
        }
    }

    private Connection connect() throws SQLException {
        try {return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(new JOptionPane(), "Nessuna connessione al server MYSQL", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    private void disconnect(Connection conn) throws SQLException {
        conn.close();
    }

    public List<Persona> selectAllContacts() {
        List<Persona> contacts = new ArrayList<>();
        String sql = "SELECT id, nome, cognome, telefono, indirizzo, eta FROM Informazioni WHERE idUtente = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.utente.getId()); // Bind the userId to the prepared statement
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String cognome = rs.getString("cognome");
                    String telefono = rs.getString("telefono");
                    String indirizzo = rs.getString("indirizzo");
                    int eta = rs.getInt("eta");
                    Persona persona = new Persona(id, nome, cognome, telefono, indirizzo, eta);
                    contacts.add(persona);
                }

            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }


    public void saveContactByUser(Persona person) {
        String sql = "INSERT INTO Informazioni (idUtente, nome, cognome, telefono, indirizzo, eta) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
                PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, this.utente.getId());
                stmt.setString(2, person.getNome());
                stmt.setString(3, person.getCognome());
                stmt.setString(4, person.getTelefono());
                stmt.setString(5, person.getIndirizzo());
                stmt.setInt(6, person.getEta());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            person.setId(generatedKeys.getInt(1));
                        }
                    }
                }
                disconnect(conn);
                System.out.println(person);
                System.out.println("Contatto aggiunto con successo\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyContactByUser(Persona person) {
        String sql = "UPDATE Informazioni SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, eta = ? WHERE id = ? AND idUtente = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, person.getNome());
                stmt.setString(2, person.getCognome());
                stmt.setString(3, person.getIndirizzo());
                stmt.setString(4, person.getTelefono());
                stmt.setInt(5, person.getEta());
                stmt.setInt(6, person.getId());
                stmt.setInt(7, utente.getId());
                stmt.executeUpdate();
                disconnect(conn);
                System.out.println(person);
                System.out.println("Contatto modificato con successo\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeContactByUser(Persona person) {
        String sql = "DELETE FROM Informazioni WHERE id = ? AND idUtente = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, person.getId());
            stmt.setInt(2, utente.getId());
            stmt.executeUpdate();
            System.out.println(person);
            System.out.println("Contatto rimosso con successo\n");
            disconnect(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
