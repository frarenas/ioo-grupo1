package ar.edu.uade.ui;

import ar.edu.uade.ui.paciente.PacienteUI;
import ar.edu.uade.ui.peticion.PeticionUI;
import ar.edu.uade.ui.usuario.UsuarioUI;

import javax.swing.*;

public class Menu {

    JFrame rootFrame;
    private JPanel pnlPrincipal;


    private JButton button1;

    public Menu() {
        rootFrame = new JFrame("Clínica");
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
    }

    private JMenuBar setMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu mnuAbm = new JMenu("ABM");
        JMenu mnuConsultas = new JMenu("Consultas");
        JMenuItem mnuPacientes = new JMenuItem("Pacientes");
        JMenuItem mnuSucursales = new JMenuItem("Sucursales");
        JMenuItem mnuUsuarios = new JMenuItem("Usuarios");
        JMenuItem mnuPeticiones = new JMenuItem("Peticiones");

        mnuPacientes.addActionListener(e -> mostrarPantalla(new PacienteUI().pnlPrincipal));
        mnuUsuarios.addActionListener(e -> mostrarPantalla(new UsuarioUI().pnlPrincipal));
        mnuPeticiones.addActionListener(e -> mostrarPantalla(new PeticionUI().pnlPrincipal));

        mnuAbm.add(mnuPacientes);
        mnuAbm.add(mnuSucursales);
        mnuAbm.add(mnuUsuarios);
        mnuAbm.add(mnuPeticiones);

        mb.add(mnuAbm);
        mb.add(mnuConsultas);

        return mb;
    }

    private void mostrarPantalla(JPanel panel) {
        rootFrame.setContentPane(panel);
    }

}
