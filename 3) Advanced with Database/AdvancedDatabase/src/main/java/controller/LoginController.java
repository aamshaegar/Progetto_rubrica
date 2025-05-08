package controller;

import model.ContactUserModel;
import model.LoginDAO;
import model.Utente;
import view.LoginView;
import view.RubricaView;

import javax.swing.*;

public class LoginController {

    private final LoginView loginView;
    private final LoginDAO loginDAO;

    public LoginController(LoginView loginView, LoginDAO loginDAO) {
        this.loginView = loginView;
        this.loginDAO = loginDAO;
        initController();
    }

    private void initController() {
        loginView.getLoginButton().addActionListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Utente user = loginDAO.authenticate(username, password);

            if (user != null) {
                loginView.dispose();
                System.out.println("Utente connesso: " + user.getUsername());
                System.out.println("Login eseguito correttamente\n");

                RubricaView rubricaView = new RubricaView();
                ContactUserModel contactUserModel = new ContactUserModel(user);
                new ViewController(rubricaView, contactUserModel);
            } else {
                JOptionPane.showMessageDialog(loginView, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
