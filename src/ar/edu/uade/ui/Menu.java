package ar.edu.uade.ui;

import ar.edu.uade.ui.paciente.PacienteUI;
import ar.edu.uade.ui.sucursal.SucursalUI;

import javax.swing.*;

public class Menu {

    JFrame rootFrame;
    private JPanel pnlPrincipal;


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

        mnuPacientes.addActionListener(e -> mostrarPantalla(new PacienteUI().pnlPrincipal));
        mnuSucursales.addActionListener(e -> mostrarPantalla(new SucursalUI().pnlPrincipal));

        mnuAbm.add(mnuPacientes);
        mnuAbm.add(mnuSucursales);
        mb.add(mnuAbm);
        mb.add(mnuConsultas);

        return mb;
    }

    private void mostrarPantalla(JPanel panel) {
        rootFrame.setContentPane(panel);
    }

}
