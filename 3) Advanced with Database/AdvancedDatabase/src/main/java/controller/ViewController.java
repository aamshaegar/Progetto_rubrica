package controller;

import model.ContactUserModel;
import model.LoginDAO;
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
    private final ContactUserModel contactUserModel;

    public ViewController(RubricaView rubricaView, ContactUserModel contactUserModel) {
        this.rubricaView = rubricaView;
        this.contactUserModel = contactUserModel;
        this.addLogics(rubricaView, contactUserModel);
        this.updateView();
    }


    private void addLogics(RubricaView rubricaView, ContactUserModel contactUserModel) {


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
                            contactUserModel.addContact(newPersona);
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
                    contactUserModel.deleteContact(row);
                    updateView();
                }else{JOptionPane.showMessageDialog(rubricaView, "Seleziona un contatto, prima!", "Info", JOptionPane.INFORMATION_MESSAGE);}
            }
        });


        rubricaView.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int row = rubricaView.getTable().getSelectedRow();
                if (row != -1) {
                    Persona selected = contactUserModel.getContacts().get(row);
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
                            newPersona.setId(selected.getId());
                            contactUserModel.modifyContact(newPersona, row); // Add this method if it doesn't exist
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
                System.out.println("Logout eseguito correttamente\n");
                new LoginController(new LoginView(), new LoginDAO());
            }
        });
    }

    private void updateView(){
        List<Persona> contacts = contactUserModel.getContacts();
        rubricaView.displayContacts(contacts);
    }
}
