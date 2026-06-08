package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.uclm.sprintforge.dominio.Inmueble;
import es.uclm.sprintforge.dominio.PoliticaCancelacion;
import es.uclm.sprintforge.persistencia.InmuebleDAO;

public class VentanaAltaInmuebles extends JFrame {
    
    private JTextField cajaDireccion;
    private JTextField cajaPrecio;
    private JComboBox<String> comboPolitica;
    private JTextField cajaPropietario;
    private JButton botonPublicar;

    public VentanaAltaInmuebles() {
        setTitle("SprintForge - Publicar Nuevo Inmueble");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(5, 2, 10, 10)); 

        add(new JLabel("  Dirección del Inmueble:"));
        cajaDireccion = new JTextField();
        add(cajaDireccion);

        add(new JLabel("  Precio por Noche (€):"));
        cajaPrecio = new JTextField();
        add(cajaPrecio);

        add(new JLabel("  Política de Cancelación:"));
       
        
        String[] opcionesPolitica = {"NO_REEMBOLSABLE", "REEMBOLSABLE", "REEMBOLSABLE_50_PER"};
        comboPolitica = new JComboBox<>(opcionesPolitica);
        add(comboPolitica);

        add(new JLabel("  Propietario (Login):"));
        cajaPropietario = new JTextField("nuevo_inquilino"); 
        add(cajaPropietario);

        botonPublicar = new JButton("Publicar Inmueble");
        add(new JLabel("")); 
        add(botonPublicar);

        botonPublicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicarInmuebleEnBD();
            }
        });
    }

    private void publicarInmuebleEnBD() {
        String direccion = cajaDireccion.getText();
        String precioTexto = cajaPrecio.getText();
        String politicaSeleccionada = (String) comboPolitica.getSelectedItem();
        String propietario = cajaPropietario.getText();

        if (!direccion.isEmpty() && !precioTexto.isEmpty() && !propietario.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioTexto);

                Inmueble nuevoInmueble = new Inmueble();
                nuevoInmueble.setDireccion(direccion);
                nuevoInmueble.setPrecioNoche(precio);
                

                nuevoInmueble.setPoliticaCancelacion(PoliticaCancelacion.valueOf(politicaSeleccionada));

                InmuebleDAO dao = new InmuebleDAO();
                dao.registrarInmueble(nuevoInmueble, propietario);

                JOptionPane.showMessageDialog(this, "¡Inmueble publicado y guardado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce un precio numérico válido (ejemplo: 50.0 o 45.50).", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error con el tipo de política de cancelación seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos antes de publicar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}

