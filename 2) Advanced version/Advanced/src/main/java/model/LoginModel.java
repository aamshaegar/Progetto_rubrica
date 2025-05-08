package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginModel {

    private static final String filePath = "utenti.txt";

    public Utente authenticate(String username, String password) {
        List<Utente> users = selectAllUsers();
        for(Utente u : users) {if(u.getUsername().equals(username) && u.getPassword().equals(password)) {return u;}}
        return null;
    }


    public List<Utente> selectAllUsers() {
        List<Utente> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if (fields.length == 2) {
                    String username = fields[0];
                    String password = fields[1];
                    Utente user = new Utente(username, password);
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File non esistente! Creare un file con path: " + filePath);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return users;
    }
}

