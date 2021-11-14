package ar.edu.uade.ui;

import javax.swing.*;
import java.awt.*;

public class Login extends JDialog {
    private JPanel pnlPrincipal;
    private JTextField txtUsuario;
    private JTextField txtContrasena;
    private JButton btnLogin;

    private final Login self;

    public Login(Window owner, String titulo) {
        super(owner, titulo);

        self = this;

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);

        setModal(true);

        setActions();
    }

    private void setActions() {
        btnLogin.addActionListener(e -> {
            self.dispose();
        });
    }

}
