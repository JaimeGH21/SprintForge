package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPago extends JFrame {

    private JTextField cajaTitular;
    private JTextField cajaTarjeta;
    private JTextField cajaCaducidad;
    private JTextField cajaCVV;
    private JButton botonPagar;

    private String direccionInmueble;
    private String fechaInicio;
    private String fechaFin;


    public VentanaPago(String direccion, String inicio, String fin) {
        this.direccionInmueble = direccion;
        this.fechaInicio = inicio;
        this.fechaFin = fin;


        setTitle("SprintForge - Pasarela de Pago Seguro");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));


        add(new JLabel("  Alojamiento:"));
        add(new JLabel("<html><b>" + direccion + "</b></html>"));
        
        add(new JLabel("  Fechas estancia:"));
        add(new JLabel("<html><b>" + inicio + " al " + fin + "</b></html>"));


        add(new JLabel("  Titular de la tarjeta:"));
        cajaTitular = new JTextField();
        add(cajaTitular);

        add(new JLabel("  Número de tarjeta:"));
        cajaTarjeta = new JTextField();
        add(cajaTarjeta);

        add(new JLabel("  Caducidad (MM/AA):"));
        cajaCaducidad = new JTextField();
        add(cajaCaducidad);

        add(new JLabel("  CVV:"));
        cajaCVV = new JTextField();
        add(cajaCVV);

        botonPagar = new JButton("Confirmar y Pagar");
        add(new JLabel("")); 
        add(botonPagar);


        botonPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarPago();
            }
        });
    }

    
    private void procesarPago() {
        String titular = cajaTitular.getText();
        String tarjeta = cajaTarjeta.getText();
        String caducidad = cajaCaducidad.getText();
        String cvv = cajaCVV.getText();

        if (!titular.isEmpty() && !tarjeta.isEmpty() && !caducidad.isEmpty() && !cvv.isEmpty()) {
            
            es.uclm.sprintforge.persistencia.ReservaDAO dao = new es.uclm.sprintforge.persistencia.ReservaDAO();
            dao.registrarReserva(direccionInmueble, fechaInicio, fechaFin, titular);

            JOptionPane.showMessageDialog(this, 
                "¡Pago aceptado y reserva confirmada!\nTodo ha quedado guardado en el sistema para tu estancia en:\n" + direccionInmueble, 
                "Reserva Completada", JOptionPane.INFORMATION_MESSAGE);
            
            dispose(); 
            
            
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los datos de pago.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrarPasarela() {
        setVisible(true);
    }
}


