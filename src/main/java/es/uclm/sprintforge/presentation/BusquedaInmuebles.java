package es.uclm.sprintforge.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import es.uclm.sprintforge.controller.GestorInmuebles;
import es.uclm.sprintforge.entity.Inmueble;

public class BusquedaInmuebles extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private GestorInmuebles gestorInmuebles;

    public BusquedaInmuebles() {
        gestorInmuebles = new GestorInmuebles();
        
        setTitle("SprintForge - Buscador de Viviendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Modelo de la tabla
        String[] columnas = {"ID", "Dirección", "Precio/Noche"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        
        // Cargar los inmuebles desde la BD al abrir la ventana
        refrescarTabla();

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botón para cerrar o reservar
        JPanel panelBotones = new JPanel();
        JButton btnCerrar = new JButton("Volver");
        JButton btnReservar = new JButton("Solicitar Reserva");
        
        panelBotones.add(btnCerrar);
        panelBotones.add(btnReservar);
        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());
        
        btnReservar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                String id = modelo.getValueAt(fila, 0).toString();
                JOptionPane.showMessageDialog(this, "Has seleccionado el inmueble: " + id + "\nFuncionalidad de reserva en desarrollo.");
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
            }
        });
    }

    private void refrescarTabla() {
        modelo.setRowCount(0); // Limpiar tabla
        List<Inmueble> inmuebles = gestorInmuebles.obtenerTodos(); // Llama al controlador
        for (Inmueble i : inmuebles) {
            modelo.addRow(new Object[]{i.getId(), i.getDireccion(), i.getPrecio()});
        }
    }
}
