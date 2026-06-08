package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

public class VentanaBusqueda extends JFrame {
    
    private JTextField cajaBusqueda;
    private JButton botonBuscar;
    private JTable tablaInmuebles;
    private DefaultTableModel modeloTabla;
    private JButton botonReservar;

    public VentanaBusqueda() {
    	setTitle("SprintForge - Panel Principal de Búsqueda");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));


        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelSuperior.add(new JLabel("Destino / Dirección:"));
        cajaBusqueda = new JTextField(25);
        panelSuperior.add(cajaBusqueda);
        botonBuscar = new JButton("Buscar Alojamientos");
        panelSuperior.add(botonBuscar);
        add(panelSuperior, BorderLayout.NORTH);


        String[] columnas = {"ID", "Dirección", "Precio / Noche", "Política Cancelación"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaInmuebles = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInmuebles);
        add(scrollPane, BorderLayout.CENTER);


        cargarInmueblesDesdeBD();


        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        botonReservar = new JButton("Solicitar Reserva del Inmueble");
        panelInferior.add(botonReservar);
        add(panelInferior, BorderLayout.SOUTH);


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });

        botonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarReserva();
            }
        });
    }


    private void cargarInmueblesDesdeBD() {
        modeloTabla.setRowCount(0); 

        InmuebleDAO dao = new InmuebleDAO();
        List<Inmueble> listaReal = dao.obtenerTodosInmuebles();

        int idVisual = 1;
        for (Inmueble inm : listaReal) {
            modeloTabla.addRow(new Object[]{
                idVisual++, 
                inm.getDireccion(), 
                inm.getPrecioNoche() + " €", 
                inm.getPoliticaCancelacion()
            });
        }
    }

    private void realizarBusqueda() {
        String texto = cajaBusqueda.getText();
        if (!texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Filtrando alojamientos para: " + texto, "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Introduce un destino para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void gestionarReserva() {
        int filaSeleccionada = tablaInmuebles.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            String direccion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            

            VentanaReservas formulario = new VentanaReservas(direccion);
            formulario.mostrarFormularioReserva();
            
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona primero un inmueble de la tabla.", "Ningún elemento seleccionado", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
