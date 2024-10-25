package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton modificarButton;
    private JButton eliminarButton;
    private JPanel panelPasajero;
    private JList<PasajeroModel> pasajerosList;

    private final PasajeroController pasajeroController;
    private DefaultListModel<PasajeroModel> listModel;

    public PasajerosView(PasajeroController controller) {
        this.pasajeroController = controller;
        setContentPane(panelPasajero);
        setTitle("Gestión de Pasajeros");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Configuración de la lista
        listModel = new DefaultListModel<>();
        pasajerosList.setModel(listModel);
        pasajerosList.setCellRenderer(new PasajeroListRenderer());

        loadPasajerosList();

        // Listener para el botón "Agregar"
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

                    JOptionPane.showMessageDialog(null, "¡Pasajero agregado correctamente!");
                    clearFields();
                    loadPasajerosList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                }
            }
        });

        // Listener para el botón "Modificar"
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = pasajerosList.getSelectedIndex();
                if (selectedIndex != -1) {
                    try {
                        PasajeroModel selectedPasajero = pasajerosList.getSelectedValue();
                        int id = selectedPasajero.getId();
                        int dni = Integer.parseInt(dniField.getText());
                        String name = nameField.getText();
                        String surname = surnameField.getText();
                        String cel = celField.getText();
                        String mail = mailField.getText();

                        PasajeroModel pasajero = new PasajeroModel(id, dni, name, surname, cel, mail);
                        pasajeroController.updatePasajero(pasajero, id);

                        JOptionPane.showMessageDialog(null, "¡Pasajero modificado correctamente!");
                        clearFields();
                        loadPasajerosList();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un pasajero para modificar.");
                }
            }
        });

        // Listener para el botón "Eliminar"
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = pasajerosList.getSelectedIndex();
                if (selectedIndex != -1) {
                    PasajeroModel selectedPasajero = pasajerosList.getSelectedValue();
                    pasajeroController.deletePasajero(selectedPasajero.getId());
                    JOptionPane.showMessageDialog(null, "¡Pasajero eliminado correctamente!");
                    loadPasajerosList();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un pasajero para eliminar.");
                }
            }
        });

        // Listener para seleccionar un elemento en la lista
        pasajerosList.addListSelectionListener(e -> {
            PasajeroModel selectedPasajero = pasajerosList.getSelectedValue();
            if (selectedPasajero != null) {
                dniField.setText(String.valueOf(selectedPasajero.getDni()));
                nameField.setText(selectedPasajero.getName());
                surnameField.setText(selectedPasajero.getSurname());
                celField.setText(selectedPasajero.getCel());
                mailField.setText(selectedPasajero.getMail());
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

    private void loadPasajerosList() {
        listModel.clear();
        List<PasajeroModel> pasajeros = pasajeroController.getAllPasajeros();
        for (PasajeroModel p : pasajeros) {
            listModel.addElement(p);
        }
    }

    // Renderer personalizado para mostrar datos de pasajeros
    private static class PasajeroListRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof PasajeroModel) {
                PasajeroModel pasajero = (PasajeroModel) value;
                setText(pasajero.getName() + " " + pasajero.getSurname() + " - DNI: " + pasajero.getDni());
            }
            return this;
        }
    }
}


















