package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaReservas extends JFrame {

    private JTextField cajaFechaInicio;
    private JTextField cajaFechaFin;
    private JButton botonConfirmar;
    private String direccionInmueble;


    public VentanaReservas(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;


        setTitle("SprintForge - Tramitar Reserva");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));


        add(new JLabel("  Inmueble seleccionado:"));
        add(new JLabel("<html><b>" + direccionInmueble + "</b></html>")); 

        add(new JLabel("  Fecha Llegada (DD/MM/AAAA):"));
        cajaFechaInicio = new JTextField();
        add(cajaFechaInicio);

        add(new JLabel("  Fecha Salida (DD/MM/AAAA):"));
        cajaFechaFin = new JTextField();
        add(cajaFechaFin);

        botonConfirmar = new JButton("Confirmar Reserva");
        add(new JLabel("")); 
        add(botonConfirmar);


        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarReserva();
            }
        });
    }

    private void procesarReserva() {
        String inicio = cajaFechaInicio.getText();
        String fin = cajaFechaFin.getText();

        if (!inicio.isEmpty() && !fin.isEmpty()) {
            
            dispose(); 
            

            VentanaPago pasarela = new VentanaPago(direccionInmueble, inicio, fin);
            pasarela.mostrarPasarela();
            
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, introduce las fechas de tu estancia.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        }
    }


    public void mostrarFormularioReserva() {
        setVisible(true);
    }
}
