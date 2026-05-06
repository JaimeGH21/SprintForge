package es.uclm.sprintforge.presentation;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal(String usuarioLogueado) {
        setTitle("SprintForge - Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuarioLogueado, SwingConstants.CENTER);
        add(lblBienvenida);

        JButton btnGestionar = new JButton("Gestionar Mis Inmuebles (Propietario)");
        JButton btnBuscar = new JButton("Buscar Viviendas (Inquilino)");

        add(btnGestionar);
        add(btnBuscar);

        // Acción para ir a la ventana de alta que crearemos luego
        btnGestionar.addActionListener(e -> {
            // Nueva VentanaAltaInmueble().setVisible(true);
            JOptionPane.showMessageDialog(this, "Accediendo a gestión de inmuebles...");
        });

        setLocationRelativeTo(null);
    }
}
