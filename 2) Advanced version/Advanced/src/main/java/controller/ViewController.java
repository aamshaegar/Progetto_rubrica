package controller;

import main.Main;
import model.ContactsModel;
import model.LoginModel;
import model.Persona;
import view.CustomPanel;
import view.LoginView;
import view.RubricaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewController {

    private final RubricaView rubricaView;
    private final ContactsModel contactsModel;

    public ViewController(RubricaView rubricaView, ContactsModel contactsModel) {
        this.rubricaView = rubricaView;
        this.contactsModel = contactsModel;
        this.addLogics(rubricaView, contactsModel);
        this.updateView();
    }


    private void addLogics(RubricaView rubricaView, ContactsModel contactsModel) {


        rubricaView.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomPanel customPanel = new CustomPanel();
                int result = JOptionPane.showConfirmDialog(
                        rubricaView, customPanel, "Editor Persona", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                );
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        String nome = customPanel.getName();
                        String cognome = customPanel.getSurname();
                        String telefono = customPanel.getPhone();
                        String indirizzo = customPanel.getAddress();
                        int eta = Integer.parseInt(customPanel.getAge());

                        if(nome == null){
                            JOptionPane.showMessageDialog(rubricaView, "Inserisci almeno un nome o un cognome", "Errore", JOptionPane.ERROR_MESSAGE);
                        }else {
                            Persona newPersona = new Persona(nome, cognome, indirizzo, telefono, eta);
                            contactsModel.addContact(newPersona);
                            updateView();
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rubricaView, "Età non valida", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });



        rubricaView.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = rubricaView.getTable().getSelectedRow();
                if(row != -1) {
                    contactsModel.deleteContact(row);
                    updateView();
                }else{
                    JOptionPane.showMessageDialog(rubricaView, "Seleziona un contatto, prima!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        rubricaView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = rubricaView.getTable().getSelectedRow();
                if (row != -1) {
                    Persona selected = contactsModel.getContacts().get(row);
                    CustomPanel customPanel = new CustomPanel();
                    customPanel.setName(selected.getNome());
                    customPanel.setSurname(selected.getCognome());
                    customPanel.setAddress(selected.getIndirizzo());
                    customPanel.setPhone(selected.getTelefono());
                    customPanel.setAge(String.valueOf(selected.getEta()));

                    int result = JOptionPane.showConfirmDialog(
                            rubricaView, customPanel, "Modifica Contatto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            String nome = customPanel.getName();
                            String cognome = customPanel.getSurname();
                            String telefono = customPanel.getPhone();
                            String indirizzo = customPanel.getAddress();
                            int eta = Integer.parseInt(customPanel.getAge());
                            Persona newPersona = new Persona(nome, cognome, indirizzo, telefono, eta);
                            contactsModel.modifyContact(newPersona, row); // Add this method if it doesn't exist
                            updateView();

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(rubricaView, "Età non valida", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rubricaView, "Seleziona un contatto, prima!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });



        rubricaView.getLogoutButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                rubricaView.dispose();
                LoginView loginView = new LoginView();
                LoginModel loginModel = new LoginModel();
                new LoginController(loginView, loginModel);
            }
        });
    }

    private void updateView(){
        List<Persona> contacts = contactsModel.getContacts();
        rubricaView.displayContacts(contacts);
    }
}
