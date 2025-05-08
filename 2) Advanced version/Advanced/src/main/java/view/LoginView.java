package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginView() {

        Color backgroundColor = new Color(245, 245, 245);
        Color foregroundColor = new Color(20, 20, 20);
        Font font = new Font("Calibri", Font.PLAIN, 16);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(backgroundColor);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        userLabel.setForeground(foregroundColor);
        usernameField = new JTextField();
        usernameField.setFont(font);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(font);
        passLabel.setForeground(foregroundColor);
        passwordField = new JPasswordField();
        passwordField.setFont(font);
        loginButton = new JButton("Login");
        loginButton.setFont(font);

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty cell
        panel.add(loginButton);

        add(panel);

        setTitle("Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
