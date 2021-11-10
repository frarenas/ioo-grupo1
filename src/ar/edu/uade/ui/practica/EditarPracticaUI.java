package ar.edu.uade.ui.practica;


import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.model.dto.GrupoPracticaDTO;
import ar.edu.uade.model.dto.PracticaDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    PracticaController practicaController;

    private PracticaDTO practicaGuardada = null;

    public EditarPracticaUI(Window owner, PracticaController practicaController, PracticaDTO practica) {
        super(owner, practica == null? "Nueva Práctica" : "Editar Práctica");

        self = this;
        this.practicaController = practicaController;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);

        List<GrupoPracticaDTO> gruposPracticas = practicaController.getGruposPractica();
        for(GrupoPracticaDTO grupoPracticaDTO: gruposPracticas){
            cbGrupo.addItem(grupoPracticaDTO);
        }

        if(practica != null) {
            txtCodigo.setEnabled(false);
            txtCodigo.setText(String.valueOf(practica.getCodigo()));
            txtNombre.setText(practica.getNombre());
            cbGrupo.setSelectedItem(practica.getGrupo());
            txtValCriticoMin.setText(String.valueOf(practica.getValorCriticoMin()));
            txtValCriticoMax.setText(String.valueOf(practica.getValorCriticoMax()));
            chkReservado.setSelected(practica.getValorReservado());
            txtHorasResultado.setText(String.valueOf(practica.getCantHorasResultado()));
            chkActiva.setSelected(practica.getActiva());
        }

        btnGuardar.addActionListener(e -> {
            guardarPractica(practica);
            self.dispose();
        });

        btnCancelar.addActionListener(e -> self.dispose());
    }

    public PracticaDTO showDialog() {
        setVisible(true);
        return practicaGuardada;
    }

    private void guardarPractica(PracticaDTO practica) {
        //TODO: validar

        if(practica == null){
            practica = new PracticaDTO(
                    Long.valueOf(txtCodigo.getText()),
                    txtNombre.getText(),
                    (GrupoPracticaDTO) cbGrupo.getSelectedItem(),
                    Long.valueOf(txtValCriticoMin.getText()),
                    Long.valueOf(txtValCriticoMax.getText()),
                    chkReservado.isSelected(),
                    Integer.valueOf(txtHorasResultado.getText()),
                    chkActiva.isSelected()
            );

            practicaController.altaPractica(practica);
        }else {
            practica.setCodigo(Long.valueOf(txtCodigo.getText()));
            practica.setNombre(txtNombre.getText());
            practica.setGrupo((GrupoPracticaDTO) cbGrupo.getSelectedItem());
            practica.setValorCriticoMin(Long.valueOf(txtValCriticoMin.getText()));
            practica.setValorCriticoMax(Long.valueOf(txtValCriticoMax.getText()));
            practica.setValorReservado(chkReservado.isSelected());
            practica.setCantHorasResultado(Integer.valueOf(txtHorasResultado.getText()));
            practica.setActiva(chkActiva.isSelected());

            practicaController.modificacionPractica(practica);
        }
        practicaGuardada = practica;
    }
}
