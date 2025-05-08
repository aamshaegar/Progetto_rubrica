package model;

public class Utente {

    private int id;
    private String username;
    private String password;


    public Utente() {
    }

    public Utente(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {return id;}

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
