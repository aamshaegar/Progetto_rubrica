package main;

import controller.LoginController;
import model.LoginDAO;
import view.LoginView;

public class Main {

    public static void main(String[] args) {
        new LoginController(new LoginView(), new LoginDAO());
    }
}