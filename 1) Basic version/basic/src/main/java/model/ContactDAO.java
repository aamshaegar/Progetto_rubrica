package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactDAO {

//    private static final String filePath = "src/main/resources/informazioni.txt";
    private static final String filePath = "informazioni.txt";

    public void saveContacts(List<Persona> contacts) {
        try (PrintStream writer = new PrintStream(new FileOutputStream(filePath))) {
            for (Persona person : contacts) {
                writer.println(person.getNome() + ";" + person.getCognome() + ";" + person.getTelefono() + ";" + person.getIndirizzo() + ";" + person.getEta());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Persona> loadContacts() {
        List<Persona> contacts = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if (fields.length == 5) {
                    String nome = fields[0];
                    String cognome = fields[1];
                    String telefono = fields[2];
                    String indirizzo = fields[3];
                    int eta = Integer.parseInt(fields[4]);
                    Persona persona = new Persona(nome, cognome, telefono, indirizzo, eta);
                    contacts.add(persona);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
