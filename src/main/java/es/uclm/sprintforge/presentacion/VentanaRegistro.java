package es.uclm.sprintforge.presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.uclm.sprintforge.dominio.Usuario;
import es.uclm.sprintforge.persistencia.UsuarioDAO;

public class VentanaRegistro extends JFrame {
    

	private JTextField cajaUsuario;
    private JPasswordField cajaPassword;
    private JButton botonRegistrar;

    public VentanaRegistro() {
        setTitle("SprintForge - Nuevo Registro");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 2, 10, 10)); 


        add(new JLabel("  Nombre de Usuario:"));
        cajaUsuario = new JTextField();
        add(cajaUsuario);

        add(new JLabel("  Contraseña:"));
        cajaPassword = new JPasswordField();
        add(cajaPassword);


        botonRegistrar = new JButton("Registrar");
        add(new JLabel("")); 
        add(botonRegistrar);


        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuarioEnBD();
            }
        });
    }


    private void guardarUsuarioEnBD() {
        String loginEscrito = cajaUsuario.getText();
        String passEscrita = new String(cajaPassword.getPassword());

        if (!loginEscrito.isEmpty() && !passEscrita.isEmpty()) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setLogin(loginEscrito);
            nuevoUsuario.setPassword(passEscrita);


            es.uclm.sprintforge.negocio.GestorUsuarios gestor = new es.uclm.sprintforge.negocio.GestorUsuarios();
            gestor.registrarUsuario(nuevoUsuario);
            
            
            JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito en la base de datos!");
            dispose(); 
            
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos antes de registrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void mostrar() {
        setVisible(true);
    }
}

