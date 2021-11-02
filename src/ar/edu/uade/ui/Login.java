package ar.edu.uade.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JPanel pnlPrincipal;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnLogin;

    private Login self;

    public Login(Window owner, String titulo) {
        super(owner, titulo);

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);

        setActions();
    }

    private void setActions() {
        btnLogin.addActionListener(e -> {
            self.dispose();
        });
    }

}
