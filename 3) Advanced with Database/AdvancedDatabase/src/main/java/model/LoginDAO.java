package model;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class LoginDAO {


    private final String propertiesPath = "./config.properties";
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public LoginDAO(){
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

    public Utente authenticate(String username, String password) {
        String sql = "SELECT id, username, password FROM Utente WHERE username = ? AND password = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String user = rs.getString("username");
                    String pass = rs.getString("password");
                    return new Utente(id, user, pass);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
