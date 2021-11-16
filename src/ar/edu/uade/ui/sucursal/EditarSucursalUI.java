package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class EditarSucursalUI extends JDialog {
    private JPanel pnlPrincipal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JTextField txtDireccion;
    private JComboBox<UsuarioDTO> cbResponsableTecnico;
    private JTextField txtNumero;

    private final EditarSucursalUI self;
    private final SucursalController sucursalController;

    private SucursalDTO sucursalGuardada = null;

    public EditarSucursalUI(Window owner, SucursalController sucursalController, SucursalDTO sucursal) {
        super(owner, sucursal == null? "Nueva Sucursal" : "Editar Sucursal");

        self = this;
        this.sucursalController = sucursalController;

        UsuarioController usuarioController = UsuarioController.getInstance();
        List<UsuarioDTO> usuarios = usuarioController.listarUsuarios();
        for(UsuarioDTO usuario: usuarios) {
            cbResponsableTecnico.addItem(usuario);
        }

        if(sucursal != null) {
            txtNumero.setEnabled(false);
            txtNumero.setText(sucursal.getNumero().toString());
            txtDireccion.setText(sucursal.getDireccion());
            setComboResponsableTecnico(sucursal.getResponsableTecnico().getDni());
            sucursalGuardada = sucursal;
        }

        btnCancelar.addActionListener(e -> self.dispose());
        btnGuardar.addActionListener(e -> guardarSucursal(sucursal));

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        setModal(true);
    }

    public SucursalDTO showDialog() {
        setVisible(true);
        return sucursalGuardada;
    }

    private void guardarSucursal(SucursalDTO sucursal){
        if(!validar()){
            return;
        }

        UsuarioDTO responsableTecnico = (UsuarioDTO) cbResponsableTecnico.getSelectedItem();
        if(sucursal == null) {
            sucursal = new SucursalDTO(
                    Long.valueOf(txtNumero.getText()),
                    txtDireccion.getText(),
                    responsableTecnico
            );

            sucursalController.altaSucursal(sucursal);
        }
        else {
            sucursalGuardada.setDireccion(txtDireccion.getText());
            sucursalGuardada.setResponsableTecnico(responsableTecnico);

            sucursalController.modificarSucursal(sucursal);
        }
        sucursalGuardada = sucursal;
        self.dispose();
    }

    private boolean validar() {
        if(txtNumero.getText().isEmpty()) {
            showMessageDialog(null, "No pudimos reconocer el número de sucursal.");
            return false;
        }
        if(txtDireccion.getText().isEmpty()) {
            showMessageDialog(null, "No pudimos reconocer la dirección ingresada.");
            return false;
        }
        return true;
    }

    private void setComboResponsableTecnico(String dniResponsableTectico) {
        for (int i = 0; i < cbResponsableTecnico.getItemCount(); i++) {
            if(Objects.equals(cbResponsableTecnico.getItemAt(i).getDni(), dniResponsableTectico)) {
                cbResponsableTecnico.setSelectedIndex(i);
            }
        }
    }
}
