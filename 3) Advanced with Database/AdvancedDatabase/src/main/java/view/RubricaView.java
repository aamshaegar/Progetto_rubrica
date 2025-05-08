package view;

import model.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class RubricaView extends JFrame {

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton logoutButton;

    private JTable table;
    private DefaultTableModel tableModel;


    public RubricaView() {
        this.setLayout(new BorderLayout());

        // Light color
        Color backgroundColor = new Color(245, 245, 245);
        Color foregroundColor = new Color(20, 20, 20);
        Color tableHeaderColor = new Color(230, 230, 230);
        Color tableColor = Color.WHITE;
        Color buttonColor = new Color(220, 220, 220);
        Color gridColor = new Color(200, 200, 200);
        Color selectionColor = new Color(180, 200, 255);
        Color scrollThumbColor = new Color(200, 200, 200);
        Color scrollTrackColor = new Color(240, 240, 240);

        Font buttonFont = new Font("Calibri", Font.BOLD, 16);
        addButton = new JButton("Nuovo");
        editButton = new JButton("Modifica");
        deleteButton = new JButton("Elimina");
        logoutButton = new JButton("Logout");
        deleteButton.setToolTipText("Elimina il contatto selezionato");
        addButton.setToolTipText("Aggiungi un nuovo contatto");
        editButton.setToolTipText("Modifica il contatto selezionato");
        logoutButton.setToolTipText("Logout");


        for (JButton btn : new JButton[]{addButton, editButton, deleteButton,logoutButton}) {
            btn.setFont(buttonFont);
            btn.setPreferredSize(new Dimension(150, 35));
            btn.setBackground(buttonColor);
            btn.setForeground(foregroundColor);
        }

        // init table
        String[] columnNames = {"Nome", "Cognome", "Eta", "Indirizzo", "Telefono"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {return false;}
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Calibri", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.setRowHeight(30);
        table.setBackground(tableColor);
        table.setForeground(foregroundColor);
        table.getTableHeader().setBackground(tableHeaderColor);
        table.getTableHeader().setForeground(foregroundColor);
        table.setGridColor(gridColor);
        table.setSelectionBackground(selectionColor);
        table.setSelectionForeground(foregroundColor);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, gridColor));


        // Central panel
        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.setBackground(backgroundColor);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(tableColor);
        scrollPane.setBackground(tableColor);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = scrollThumbColor;
                this.trackColor = scrollTrackColor;
            }
        });
        centralPanel.add(scrollPane, BorderLayout.CENTER);


        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);
        bottomPanel.add(editButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(logoutButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.setBackground(backgroundColor);


        // Add all panels to the View
        add(centralPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(backgroundColor);


        // Add general setting to the View
        setTitle("Progetto Rubrica");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }



    public void displayContacts(List<Persona> contacts) {
        this.tableModel.setRowCount(0);
        for (Persona p : contacts) {
            this.tableModel.addRow(new Object[]{
                    p.getNome(),
                    p.getCognome(),
                    p.getEta(),
                    p.getIndirizzo(),
                    p.getTelefono()});
        }
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getLogoutButton() {return logoutButton;}

    public JTable getTable() {
        return table;
    }

}