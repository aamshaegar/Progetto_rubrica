package main;

import controller.ViewController;
import model.ContactsModel;
import view.RubricaView;

public class Main {

    public static void main(String[] args) {
        ContactsModel model = new ContactsModel();
        RubricaView view = new RubricaView();
        new ViewController(view, model);
    }
}