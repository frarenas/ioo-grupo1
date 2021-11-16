package ar.edu.uade.ui;

import ar.edu.uade.controller.*;
import ar.edu.uade.model.Rol;
import ar.edu.uade.ui.paciente.PacienteUI;
import ar.edu.uade.ui.sucursal.SucursalUI;
import ar.edu.uade.ui.practica.PracticaUI;
import ar.edu.uade.ui.peticion.PeticionUI;
import ar.edu.uade.ui.usuario.UsuarioUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu {

    JFrame rootFrame;
    private JPanel pnlPrincipal;

    private JMenuItem mnuUsuarios;

    public Menu() {
        rootFrame = new JFrame("Laboratorio");
        JMenuBar mb = setMenu();

        rootFrame.setJMenuBar(mb);
        rootFrame.setSize(800, 600);
        rootFrame.setLayout(null);
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setLocationRelativeTo(null);
        rootFrame.add(pnlPrincipal);


        rootFrame.setVisible(true);

        Login login = new Login(this.rootFrame, "Login");
        login.setVisible(true);
        login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                mnuUsuarios.setVisible(UsuarioController.usuarioLogueado.getRol() == Rol.ADMINISTRADOR);
            }
        });
    }

    private JMenuBar setMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu mnuAbm = new JMenu("ABM");
        JMenuItem mnuPacientes = new JMenuItem("Pacientes");
        JMenuItem mnuSucursales = new JMenuItem("Sucursales");
        mnuUsuarios = new JMenuItem("Usuarios");
        JMenuItem mnuPeticiones = new JMenuItem("Peticiones");
        JMenuItem mnuPracticas = new JMenuItem("Prácticas");

        mnuPacientes.addActionListener(e -> mostrarPantalla(new PacienteUI(PacienteController.getInstance()).pnlPrincipal));
        mnuSucursales.addActionListener(e -> mostrarPantalla(new SucursalUI(SucursalController.getInstance()).pnlPrincipal));
        mnuUsuarios.addActionListener(e -> mostrarPantalla(new UsuarioUI().pnlPrincipal));
        mnuPracticas.addActionListener(e -> mostrarPantalla(new PracticaUI(PracticaController.getInstance()).pnlPrincipal));
        mnuPeticiones.addActionListener(e -> mostrarPantalla(new PeticionUI(PeticionController.getInstance()).pnlPrincipal));

        mnuAbm.add(mnuPacientes);
        mnuAbm.add(mnuSucursales);
        mnuAbm.add(mnuUsuarios);
        mnuAbm.add(mnuPracticas);
        mnuAbm.add(mnuPeticiones);

        mb.add(mnuAbm);

        return mb;
    }

    private void mostrarPantalla(JPanel panel) {
        rootFrame.setContentPane(panel);
    }

}
