package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Models.HabitacionModel;
import Controllers.HabitacionController;

public class HabitacionView extends JFrame {
    private JPanel panelHabitacion;
    private JTextField cantidadCamasField;
    private JTextField precioDiaField;
    private JTextField cantidadCamasDoblesField;
    private JTextField cantidadCamasSimplesField;
    private JButton agregarButton;
    private JTextField numeroHabitacionField;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JList<HabitacionModel> habitacionList;
    private DefaultListModel<HabitacionModel> listModel;

    private final HabitacionController habitacionController;

    // Constructor
    public HabitacionView(HabitacionController controller) {
        this.habitacionController = controller;
        setContentPane(panelHabitacion);
        setTitle("Gestión de Habitaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Configurar el modelo de la lista
        listModel = new DefaultListModel<>();
        habitacionList.setModel(listModel);
        habitacionList.setCellRenderer(new HabitacionListCellRenderer());

        // Cargar habitaciones en la lista al iniciar
        loadHabitacionesList();

        // Listener para el botón "Agregar"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numeroHabitacion = Integer.parseInt(numeroHabitacionField.getText());
                    int cantidadCamas = Integer.parseInt(cantidadCamasField.getText());
                    int cantidadCamasSimples = Integer.parseInt(cantidadCamasSimplesField.getText());
                    int cantidadCamasDobles = Integer.parseInt(cantidadCamasDoblesField.getText());
                    int precioDia = Integer.parseInt(precioDiaField.getText());

                    HabitacionModel habitacion = new HabitacionModel(0, numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia);
                    habitacionController.addHabitacion(habitacion);

                    JOptionPane.showMessageDialog(null, "¡Habitación agregada correctamente!");
                    clearFields();
                    loadHabitacionesList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                }
            }
        });

        // Listener para el botón "Eliminar"
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HabitacionModel selectedHabitacion = habitacionList.getSelectedValue();
                if (selectedHabitacion != null) {
                    habitacionController.deleteHabitacion(selectedHabitacion.getId());
                    JOptionPane.showMessageDialog(null, "¡Habitación eliminada correctamente!");
                    loadHabitacionesList();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una habitación para eliminar.");
                }
            }
        });

        // Listener para el botón "Modificar"
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HabitacionModel selectedHabitacion = habitacionList.getSelectedValue();
                if (selectedHabitacion != null) {
                    try {
                        int numeroHabitacion = Integer.parseInt(numeroHabitacionField.getText());
                        int cantidadCamas = Integer.parseInt(cantidadCamasField.getText());
                        int cantidadCamasSimples = Integer.parseInt(cantidadCamasSimplesField.getText());
                        int cantidadCamasDobles = Integer.parseInt(cantidadCamasDoblesField.getText());
                        int precioDia = Integer.parseInt(precioDiaField.getText());

                        // Actualizar los valores en el modelo de la habitación seleccionada
                        selectedHabitacion.setNumeroHabitacion(numeroHabitacion);
                        selectedHabitacion.setCantidadCamas(cantidadCamas);
                        selectedHabitacion.setCantidadCamasSimples(cantidadCamasSimples);
                        selectedHabitacion.setCantidadCamasDobles(cantidadCamasDobles);
                        selectedHabitacion.setPrecioDia(precioDia);

                        // Actualizar la habitación en la base de datos
                        habitacionController.updateHabitacion(selectedHabitacion, selectedHabitacion.getId());
                        JOptionPane.showMessageDialog(null, "¡Habitación actualizada correctamente!");
                        clearFields();
                        loadHabitacionesList();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa valores válidos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una habitación para modificar.");
                }
            }
        });
        JLabel textoModificarLabel = new JLabel("<html>1ro seleccione la habitación a actualizar, luego ingrese los datos en el panel y por ultimo presione el boton Modificar para guardar los cambios.</html>");
        textoModificarLabel.setPreferredSize(new Dimension(200, 100)); // Establecer tamaño preferido para el JLabel


        // Listener para seleccionar un elemento en la lista
        habitacionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                HabitacionModel selectedHabitacion = habitacionList.getSelectedValue();
                if (selectedHabitacion != null) {
                    numeroHabitacionField.setText(String.valueOf(selectedHabitacion.getNumeroHabitacion()));
                    cantidadCamasField.setText(String.valueOf(selectedHabitacion.getCantidadCamas()));
                    cantidadCamasSimplesField.setText(String.valueOf(selectedHabitacion.getCantidadCamasSimples()));
                    cantidadCamasDoblesField.setText(String.valueOf(selectedHabitacion.getCantidadCamasDobles()));
                    precioDiaField.setText(String.valueOf(selectedHabitacion.getPrecioDia()));
                }
            }
        });
    }

    private void clearFields() {
        numeroHabitacionField.setText("");
        cantidadCamasField.setText("");
        cantidadCamasSimplesField.setText("");
        cantidadCamasDoblesField.setText("");
        precioDiaField.setText("");
    }

    private void loadHabitacionesList() {
        listModel.clear();
        List<HabitacionModel> habitaciones = habitacionController.getAllHabitacion();
        for (HabitacionModel habitacion : habitaciones) {
            listModel.addElement(habitacion);
        }
    }

    // Custom ListCellRenderer para mostrar los datos de forma más amigable
    private static class HabitacionListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            HabitacionModel habitacion = (HabitacionModel) value;
            String displayText = String.format("Nº: %d, Camas: %d (S: %d, D: %d), Precio: $%d/día",
                    habitacion.getNumeroHabitacion(), habitacion.getCantidadCamas(),
                    habitacion.getCantidadCamasSimples(), habitacion.getCantidadCamasDobles(), habitacion.getPrecioDia());
            return super.getListCellRendererComponent(list, displayText, index, isSelected, cellHasFocus);
        }
    }
}


