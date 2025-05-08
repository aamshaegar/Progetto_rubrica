package main;

import controller.LoginController;
import model.LoginModel;
import view.LoginView;


public class Main {

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();
        new LoginController(loginView, loginModel);
    }
}