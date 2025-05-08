package view;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField ageField;

    public CustomPanel() {

        Color backgroundColor = new Color(245, 245, 245);
        Color foregroundColor = new Color(20, 20, 20);
        Color fieldBackgroundColor = Color.WHITE;
        Color labelColor = new Color(30, 30, 30);
        Font labelFont = new Font("Calibri", Font.PLAIN, 16);
        Font fieldFont = new Font("Calibri", Font.PLAIN, 16);

        setLayout(new GridLayout(0, 1, 0, 5));
        setBackground(backgroundColor);


        nameField = createTextField(fieldFont, fieldBackgroundColor, foregroundColor);
        surnameField = createTextField(fieldFont, fieldBackgroundColor, foregroundColor);
        ageField = createTextField(fieldFont, fieldBackgroundColor, foregroundColor);
        addressField = createTextField(fieldFont, fieldBackgroundColor, foregroundColor);
        phoneField = createTextField(fieldFont, fieldBackgroundColor, foregroundColor);

        add(createLabeledComponent("Nome:", nameField, labelFont, labelColor));
        add(createLabeledComponent("Cognome:", surnameField, labelFont, labelColor));
        add(createLabeledComponent("Età:", ageField, labelFont, labelColor));
        add(createLabeledComponent("Indirizzo:", addressField, labelFont, labelColor));
        add(createLabeledComponent("Telefono:", phoneField, labelFont, labelColor));
    }

    private JTextField createTextField(Font font, Color bg, Color fg) {
        JTextField field = new JTextField();
        field.setFont(font);
        field.setBackground(bg);
        field.setForeground(fg);
        field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return field;
    }

    private JPanel createLabeledComponent(String labelText, JComponent component, Font font, Color fg) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(fg);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(getBackground());
        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    public String getName() { return nameField.getText(); }
    public String getSurname() { return surnameField.getText(); }
    public String getAddress() { return addressField.getText(); }
    public String getPhone() { return phoneField.getText(); }
    public String getAge() { return ageField.getText(); }
    public void setName(String name) { this.nameField.setText(name); }
    public void setSurname(String surname) { this.surnameField.setText(surname); }
    public void setAddress(String address) { this.addressField.setText(address); }
    public void setPhone(String phone) { this.phoneField.setText(phone); }
    public void setAge(String age) { this.ageField.setText(age); }
}
