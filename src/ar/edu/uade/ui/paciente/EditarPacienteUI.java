package ar.edu.uade.ui.paciente;

import ar.edu.uade.model.Paciente;

import javax.swing.*;
import java.awt.*;

public class EditarPacienteUI extends JDialog {
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtDomicilio;
    private JTextField txtEmail;
    private JComboBox cbSexo;
    private JTextField txtEdad;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private EditarPacienteUI self;

    public EditarPacienteUI(Window owner, Paciente paciente) {
        super(owner, paciente == null? "Nuevo Paciente" : "Editar Paciente");

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);

        setActions();

        if(paciente != null) {
            txtDni.setEnabled(false);
            txtDni.setText(paciente.getDni());
            txtNombre.setText(paciente.getNombre());
            txtDomicilio.setText(paciente.getDomicilio());
            txtEmail.setText(paciente.getEmail());
            txtEdad.setText(paciente.getEdad().toString());
        }
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }
}
