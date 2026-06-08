package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importamos el Gestor
import es.uclm.sprintforge.negocio.GestorUsuarios;

public class VentanaLogin extends JFrame {
    
    private JTextField cajaUsuario;
    private JPasswordField cajaPassword;
    private JButton botonLogin;

    public VentanaLogin() {
        setTitle("SprintForge - Iniciar Sesión");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("  Usuario:"));
        cajaUsuario = new JTextField();
        add(cajaUsuario);

        add(new JLabel("  Contraseña:"));
        cajaPassword = new JPasswordField();
        add(cajaPassword);

        botonLogin = new JButton("Iniciar Sesión");
        add(new JLabel("")); 
        add(botonLogin);

        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarLogin();
            }
        });
    }

    private void procesarLogin() {
        String loginEscrito = cajaUsuario.getText();
        String passEscrita = new String(cajaPassword.getPassword());

        if (!loginEscrito.isEmpty() && !passEscrita.isEmpty()) {
            
            // 2. Usamos el GestorUsuarios
            GestorUsuarios gestor = new GestorUsuarios();
            boolean esValido = gestor.comprobarCredenciales(loginEscrito, passEscrita);

            if (esValido) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido/a a SprintForge!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 
                
                VentanaBusqueda principal = new VentanaBusqueda();
                principal.mostrar(); 
                
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}

