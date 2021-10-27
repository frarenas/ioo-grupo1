package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {


    private JPanel jpWindow;

    public Menu() {
        JFrame f = new JFrame("Clínica");
        JMenuBar mb = new JMenuBar();
        JMenu jmPacientes = new JMenu("Pacientes");
        JMenu jmPSucursales = new JMenu("Sucursales");

        mb.add(jmPacientes);
        mb.add(jmPSucursales);

        f.setJMenuBar(mb);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.add(jpWindow);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
