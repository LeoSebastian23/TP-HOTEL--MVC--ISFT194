package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Models.PasajeroModel;
import Controllers.PasajeroController;

import static java.lang.Integer.parseInt;

public class PasajerosView extends JFrame {
    private JTextField dniField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField celField;
    private JTextField mailField;
    private JButton agregarPasajero;
    private JPanel panelPasajero;
    private JList list1;
    private JButton modificarButton;
    private JButton eliminarButton;

    private final PasajeroController pasajeroController;

    public PasajerosView(PasajeroController controller) {
        this.pasajeroController = controller;
        setContentPane(panelPasajero);
        setTitle("Gestión de Pasajeros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        agregarPasajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int dni = parseInt(dniField.getText());
                    String name = nameField.getText();
                    String surname = surnameField.getText();
                    String cel = celField.getText();
                    String mail = mailField.getText();

                    PasajeroModel pasajero = new PasajeroModel(4, dni, name, surname, cel, mail);
                    pasajeroController.addPasajero(pasajero);

                    JOptionPane.showMessageDialog(null, "Pasajero agregado correctamente!");
                    clearFields();
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
}




