package controller;

import model.LoginModel;
import model.ContactsModel;
import model.Utente;
import view.LoginView;
import view.RubricaView;

import javax.swing.*;

public class LoginController {

    private final LoginView loginView;
    private final LoginModel loginModel;

    public LoginController(LoginView loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
        initController();
    }

    private void initController() {
        loginView.getLoginButton().addActionListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Utente user = loginModel.authenticate(username, password);

            if (user != null) {
                loginView.dispose();
                String filePath = "./informazioni/" + user.getUsername() + "_" + user.getPassword() + "_" + "informazioni.txt";
                RubricaView rubricaView = new RubricaView();
                ContactsModel contactsModel = new ContactsModel(filePath);
                new ViewController(rubricaView, contactsModel);
            } else {
                JOptionPane.showMessageDialog(loginView, "Credenziali errate", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
