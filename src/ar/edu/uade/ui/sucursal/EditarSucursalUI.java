package ar.edu.uade.ui.sucursal;

import ar.edu.uade.model.dto.SucursalDTO;

import javax.swing.*;
import java.awt.*;

public class EditarSucursalUI extends JDialog {
    private JPanel pnlPrincipal;
    private JButton btnCancelar;
    private JButton guardarButton;
    private JTextField txtDireccion;
    private JComboBox cbResponsableTecnico;
    private JTextField txtNumero;
    private JPanel pnlCampos;

    //Esto es para instanciar el cuadro de diálogo, porque sino no lo puedo referenciar con this más adelante.
    private EditarSucursalUI self;

    private SucursalDTO sucursalGuardada = null;

    public EditarSucursalUI(Window owner, SucursalDTO sucursal) {
        super(owner, sucursal == null? "Nueva Sucursal" : "Editar Sucursal");

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();

        setModal(true);

        //Añade los eventos de los botones
        setActions();

        if(sucursal != null) {
            txtNumero.setEnabled(false);
            txtNumero.setText(sucursal.getNumero().toString());
            txtDireccion.setText(sucursal.getDireccion());
            cbResponsableTecnico.setSelectedItem(sucursal.getResponsableTecnico().getNombre());
        }
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }

    public SucursalDTO showDialog() {
        setVisible(true);
        return sucursalGuardada;
    }
}
