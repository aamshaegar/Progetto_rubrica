package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactDAO {

    private String filePath;
    public ContactDAO(String filePath) {
        this.filePath = filePath;
    }

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
        File file = new File(filePath);

        // Create file if it does not exist
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (Scanner scanner = new Scanner(file)) {
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
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return contacts;
    }


}
