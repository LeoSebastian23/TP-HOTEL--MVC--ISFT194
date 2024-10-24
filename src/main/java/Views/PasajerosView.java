package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import Models.PasajeroModel;
import Controllers.PasajeroController;
import java.util.List;

public class PasajerosView extends JFrame {
    private JTextField dniField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField celField;
    private JTextField mailField;
    private JButton agregarPasajero;
    private JPanel panelPasajero;
    private JTable pasajerosTable;
    private JButton modificarButton;
    private JButton eliminarButton;

    private final PasajeroController pasajeroController;
    private DefaultTableModel tableModel;

    public PasajerosView(PasajeroController controller) {
        this.pasajeroController = controller;
        setContentPane(panelPasajero);
        setTitle("Gestión de Pasajeros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "DNI", "Nombre", "Apellido", "Cel", "Mail"}, 0);
        pasajerosTable.setModel(tableModel);
        loadPasajerosTable();

        agregarPasajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = Integer.parseInt(dniField.getText());
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String cel = celField.getText();
                    String mail = mailField.getText();

                    PasajeroModel pasajero = new PasajeroModel(0, dni, name, surname, cel, mail);
                    pasajeroController.addPasajero(pasajero);

                    JOptionPane.showMessageDialog(null, "Pasajero agregado correctamente!");
                    clearFields();
                    loadPasajerosTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                }
            }
        });
    }

    private void clearFields() {
        dniField.setText("");
        nameField.setText("");
        surnameField.setText("");
        celField.setText("");
        mailField.setText("");
    }

    private void loadPasajerosTable() {
        // Limpia la tabla antes de agregar nuevos datos
        tableModel.setRowCount(0);

        List<PasajeroModel> pasajeros = pasajeroController.getAllPasajeros();
        for (PasajeroModel p : pasajeros) {
            tableModel.addRow(new Object[]{p.getId(), p.getDni(), p.getName(), p.getSurname(), p.getCel(), p.getMail()});
        }
    }
}








