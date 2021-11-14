package ar.edu.uade.ui.paciente;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.model.Sexo;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.util.FormValidator;

import javax.swing.*;
import java.awt.*;

public class EditarPacienteUI extends JDialog {
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtDomicilio;
    private JTextField txtEmail;
    private JComboBox<Sexo> cbSexo;
    private JTextField txtEdad;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private final EditarPacienteUI self;
    private final PacienteController pacienteController;

    private PacienteDTO pacienteGuardado = null;

    public EditarPacienteUI(Window owner, PacienteController pacienteController, PacienteDTO paciente) {
        super(owner, paciente == null? "Nuevo Paciente" : "Editar Paciente");

        self = this;
        this.pacienteController = pacienteController;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);

        cbSexo.addItem(Sexo.FEMENINO);
        cbSexo.addItem(Sexo.MASCULINO);
        cbSexo.addItem(Sexo.OTRO);

        if(paciente != null) {
            txtDni.setEnabled(false);
            txtDni.setText(paciente.getDni());
            txtNombre.setText(paciente.getNombre());
            txtDomicilio.setText(paciente.getDomicilio());
            txtEmail.setText(paciente.getEmail());
            cbSexo.setSelectedItem(paciente.getSexo());
            txtEdad.setText(paciente.getEdad().toString());
        }

        btnGuardar.addActionListener(e -> guardarPaciente(paciente));

        btnCancelar.addActionListener(e -> self.dispose());
    }

    public PacienteDTO showDialog() {
        setVisible(true);
        return pacienteGuardado;
    }

    private void guardarPaciente(PacienteDTO paciente) {
        if(!validar()) {
            return;
        }

        if(paciente == null){
            paciente = new PacienteDTO(
                    txtDni.getText(),
                    txtNombre.getText(),
                    txtDomicilio.getText(),
                    txtEmail.getText(),
                    (Sexo) cbSexo.getSelectedItem(),
                    Integer.valueOf(txtEdad.getText())
            );

            pacienteController.altaPaciente(paciente);
        }else {
            paciente.setNombre(txtNombre.getText());
            paciente.setDomicilio(txtDomicilio.getText());
            paciente.setEmail(txtEmail.getText());
            paciente.setSexo((Sexo) cbSexo.getSelectedItem());
            paciente.setEdad(Integer.valueOf(txtEdad.getText()));

            pacienteController.modificarPaciente(paciente);
        }
        pacienteGuardado = paciente;
        self.dispose();
    }

    private boolean validar() {
        if(txtDni.getText().length() != 8) {
            JOptionPane.showMessageDialog(null, "Revise el DNI.");
            return false;
        }
        if(txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Revise el nombre.");
            return false;
        }
        if(txtDomicilio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Revise el domicilio.");
            return false;
        }
        if(!FormValidator.validateEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(null, "Revise el email.");
            return false;
        }
        if(!FormValidator.validateAge(txtEdad.getText())) {
            JOptionPane.showMessageDialog(null, "Revise la edad.");
            return false;
        }
        return true;
    }
}
