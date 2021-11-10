package ar.edu.uade.ui.peticion;

import ar.edu.uade.model.Peticion;

import javax.swing.*;
import java.awt.*;

public class EditarPeticionUI extends JDialog {
    private JTextField txtId;
    private JTextField txtDniPaciente;
    private JTextField txtObraSocial;
    private JTextField txtFechaCarga;
    private JTextField txtFechaEntrega;
    private JTextField txtNroSucursal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private EditarPeticionUI self;

    public EditarPeticionUI(Window owner, Peticion peticion) {
        super(owner, peticion == null? "Nueva peticion" : "Editar peticion");

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);

        setActions();

        if(peticion != null) {
            txtId.setText(peticion.getId().toString());
            txtDniPaciente.setText(peticion.getPaciente().getDni());
            txtObraSocial.setText(peticion.getObraSocial());
            txtFechaCarga.setText(peticion.getFechaCarga().toString());
            txtFechaEntrega.setText(peticion.getFechaEntrega().toString());
            txtNroSucursal.setText(peticion.getSucursal().getNumero().toString());
        }
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }
}
