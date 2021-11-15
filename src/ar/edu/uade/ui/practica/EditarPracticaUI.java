package ar.edu.uade.ui.practica;


import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.model.dto.GrupoPracticaDTO;
import ar.edu.uade.model.dto.PracticaDTO;
import ar.edu.uade.util.FormValidator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class EditarPracticaUI extends JDialog {
    private JCheckBox chkActiva;
    private JCheckBox chkReservado;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox<GrupoPracticaDTO> cbGrupo;
    private JTextField txtValCriticoMin;
    private JTextField txtValCriticoMax;
    private JTextField txtHorasResultado;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private final EditarPracticaUI self;
    private final PracticaController practicaController;

    private PracticaDTO practicaGuardada = null;

    public EditarPracticaUI(Window owner, PracticaController practicaController, PracticaDTO practica) {
        super(owner, practica == null? "Nueva Práctica" : "Editar Práctica");

        self = this;
        this.practicaController = practicaController;

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);

        setModal(true);

        List<GrupoPracticaDTO> gruposPracticas = practicaController.getGruposPractica();
        for(GrupoPracticaDTO grupoPracticaDTO: gruposPracticas){
            cbGrupo.addItem(grupoPracticaDTO);
        }

        if(practica != null) {
            txtCodigo.setEnabled(false);
            txtCodigo.setText(String.valueOf(practica.getCodigo()));
            txtNombre.setText(practica.getNombre());
            setComboGrupo(practica.getGrupo().getId());
            txtValCriticoMin.setText(String.valueOf(practica.getValorCriticoMin()));
            txtValCriticoMax.setText(String.valueOf(practica.getValorCriticoMax()));
            chkReservado.setSelected(practica.getValorReservado());
            txtHorasResultado.setText(String.valueOf(practica.getCantHorasResultado()));
            chkActiva.setSelected(practica.getActiva());
        }

        btnGuardar.addActionListener(e -> guardarPractica(practica));

        btnCancelar.addActionListener(e -> self.dispose());
    }

    public PracticaDTO showDialog() {
        setVisible(true);
        return practicaGuardada;
    }

    private void guardarPractica(PracticaDTO practica) {
        if(!validar()) {
            return;
        }

        if(practica == null){
            practica = new PracticaDTO(
                    Long.valueOf(txtCodigo.getText()),
                    txtNombre.getText(),
                    (GrupoPracticaDTO) cbGrupo.getSelectedItem(),
                    Double.valueOf(txtValCriticoMin.getText()),
                    Double.valueOf(txtValCriticoMax.getText()),
                    chkReservado.isSelected(),
                    Integer.valueOf(txtHorasResultado.getText()),
                    chkActiva.isSelected()
            );

            practicaController.altaPractica(practica);
        }else {
            practica.setCodigo(Long.valueOf(txtCodigo.getText()));
            practica.setNombre(txtNombre.getText());
            practica.setGrupo((GrupoPracticaDTO) cbGrupo.getSelectedItem());
            practica.setValorCriticoMin(Double.valueOf(txtValCriticoMin.getText()));
            practica.setValorCriticoMax(Double.valueOf(txtValCriticoMax.getText()));
            practica.setValorReservado(chkReservado.isSelected());
            practica.setCantHorasResultado(Integer.valueOf(txtHorasResultado.getText()));
            practica.setActiva(chkActiva.isSelected());

            practicaController.modificacionPractica(practica);
        }
        practicaGuardada = practica;
        self.dispose();
    }

    private void setComboGrupo(Long idGrupo) {
        for (int i = 0; i < cbGrupo.getItemCount(); i++) {
            if(Objects.equals(cbGrupo.getItemAt(i).getId(), idGrupo)) {
                cbGrupo.setSelectedIndex(i);
            }
        }
    }

    private boolean validar() {
        if(!FormValidator.validateNumber(txtCodigo.getText())) {
            JOptionPane.showMessageDialog(this, "Revise el código.");
            return false;
        }
        if(txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Revise el nombre.");
            return false;
        }
        if(!FormValidator.validateLong(txtValCriticoMin.getText())) {
            JOptionPane.showMessageDialog(null, "Revise el valor crítico mínimo.");
            return false;
        }
        if(!FormValidator.validateLong(txtValCriticoMax.getText())) {
            JOptionPane.showMessageDialog(null, "Revise el valor crítico máximo.");
            return false;
        }
        if(!FormValidator.validateNumber(txtHorasResultado.getText())) {
            JOptionPane.showMessageDialog(null, "Revise las horas para obtener el resultado.");
            return false;
        }
        return true;
    }
}
