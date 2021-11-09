package ar.edu.uade.ui.paciente;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.model.Sexo;
import ar.edu.uade.model.dto.PacienteDTO;

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

    private PacienteDTO pacienteGuardado = null;

    public EditarPacienteUI(Window owner, PacienteDTO paciente) {
        super(owner, paciente == null? "Nuevo Paciente" : "Editar Paciente");

        self = this;

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

        btnGuardar.addActionListener(e -> {
            //setVisible(false);
            guardarPaciente(paciente);
            self.dispose();
        });

        btnCancelar.addActionListener(e -> {
            //setVisible(false);
            self.dispose();
        });
    }

    public PacienteDTO showDialog() {
        setVisible(true);
        return pacienteGuardado;
    }

    private void guardarPaciente(PacienteDTO paciente) {
        //TODO: validar
        PacienteController pacienteController = PacienteController.getInstance();

        if(paciente == null){
            paciente = new PacienteDTO(
                    txtDni.getText(),
                    txtNombre.getText(),
                    txtDomicilio.getText(),
                    txtEmail.getText(),
                    (Sexo) cbSexo.getSelectedItem(),
                    Integer.valueOf(txtEdad.getText())
            );

            pacienteController.altaPaciente(
                    txtDni.getText(),
                    txtNombre.getText(),
                    txtDomicilio.getText(),
                    txtEmail.getText(),
                    (Sexo) cbSexo.getSelectedItem(),
                    Integer.valueOf(txtEdad.getText())
            );
        }else {
            paciente.setNombre(txtNombre.getText());
            paciente.setDomicilio(txtDomicilio.getText());
            paciente.setEmail(txtEmail.getText());
            paciente.setSexo((Sexo) cbSexo.getSelectedItem());
            paciente.setEdad(Integer.valueOf(txtEdad.getText()));

            pacienteController.modificarPaciente(
                    txtDni.getText(),
                    txtNombre.getText(),
                    txtDomicilio.getText(),
                    txtEmail.getText(),
                    (Sexo) cbSexo.getSelectedItem(),
                    Integer.valueOf(txtEdad.getText())
            );
        }
        pacienteGuardado = paciente;
    }
}
