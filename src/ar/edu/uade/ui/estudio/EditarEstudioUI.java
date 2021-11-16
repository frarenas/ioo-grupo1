package ar.edu.uade.ui.estudio;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.model.dto.EstudioDTO;
import ar.edu.uade.model.dto.PeticionDTO;
import ar.edu.uade.model.dto.PracticaDTO;
import ar.edu.uade.model.dto.ResultadoPeticionDTO;
import ar.edu.uade.util.FormValidator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class EditarEstudioUI extends JDialog {
    private JPanel pnlPrincipal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JTextField txtCodigo;
    private JComboBox<PracticaDTO> cbPractica;
    private JTextField txtResultado;

    private final EditarEstudioUI self;
    private final PeticionController peticionController;

    private EstudioDTO estudioGuardado = null;

    public EditarEstudioUI(Window owner, PeticionController peticionController, PeticionDTO peticion, EstudioDTO estudio) {
        super(owner, estudio == null? "Nuevo Estudio" : "Editar Estudio");

        self = this;
        this.peticionController = peticionController;

        PracticaController practicaController = PracticaController.getInstance();
        List<PracticaDTO> practicas = practicaController.getPracticas();
        for(PracticaDTO practica: practicas) {
            cbPractica.addItem(practica);
        }

        if(estudio != null) {
            txtCodigo.setEnabled(false);
            txtCodigo.setText(estudio.getCodigo().toString());
            setComboPractica(estudio.getPractica().getCodigo());
            if(estudio.getResultadoPeticion() != null && estudio.getResultadoPeticion().getResultado() != null) {
                txtResultado.setText(estudio.getResultadoPeticion().getResultado().toString());
            }
        }

        btnGuardar.addActionListener(e -> guardarEstudio(estudioGuardado));
        btnCancelar.addActionListener(e -> self.dispose());

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        setModal(true);
    }

    public EstudioDTO showDialog() {
        setVisible(true);
        return estudioGuardado;
    }

    private void guardarEstudio(EstudioDTO estudio) {
        if(!validar()) {
            return;
        }

        if(estudio == null){
            estudio = new EstudioDTO(
                    Integer.valueOf(txtCodigo.getText()),
                    (PracticaDTO) cbPractica.getSelectedItem(),
                    txtResultado.getText().isEmpty() ? null : new ResultadoPeticionDTO(Double.valueOf(txtResultado.getText()))
            );
        }else {
            estudio.setPractica((PracticaDTO) cbPractica.getSelectedItem());
            estudio.setResultadoPeticion(
                    txtResultado.getText().isEmpty() ? null : new ResultadoPeticionDTO(Double.valueOf(txtResultado.getText()))
            );
        }

        estudioGuardado = estudio;
        self.dispose();
    }

    private boolean validar() {
        if(!FormValidator.validateNumber(txtCodigo.getText())) {
            JOptionPane.showMessageDialog(null, "Revise el código.");
            return false;
        }
        if(!txtResultado.getText().isEmpty() && !FormValidator.validateLong(txtResultado.getText())) {
            JOptionPane.showMessageDialog(null, "Revise el resultado.");
            return false;
        }
        return true;
    }

    private void setComboPractica(long codPractica) {
        for (int i = 0; i < cbPractica.getItemCount(); i++) {
            if(Objects.equals(cbPractica.getItemAt(i).getCodigo(), codPractica)) {
                cbPractica.setSelectedIndex(i);
            }
        }
    }
}
