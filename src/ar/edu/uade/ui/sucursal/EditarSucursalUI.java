package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditarSucursalUI extends JDialog {
    private JPanel pnlPrincipal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JTextField txtDireccion;
    private JComboBox cbResponsableTecnico;
    private JTextField txtNumero;
    private JPanel pnlCampos;

    //Esto es para instanciar el cuadro de diálogo, porque sino no lo puedo referenciar con this más adelante.
    private EditarSucursalUI self;

    private SucursalDTO sucursalGuardada = null;

    public EditarSucursalUI(Window owner, SucursalDTO sucursal) {
        super(owner, sucursal == null? "Nueva Sucursal" : "Editar Sucursal");
        UsuarioController usuarioController = UsuarioController.getInstance();

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();

        setModal(true);

        //Añade los eventos de los botones
        setActions();

        List<UsuarioDTO> usuarios = usuarioController.listarUsuarios();
        for(UsuarioDTO usuario: usuarios) {
            cbResponsableTecnico.addItem(usuario.getNombre() + " DNI: " + usuario.getDni());
        }

        if(sucursal != null) {
            txtNumero.setEnabled(false);
            txtNumero.setText(sucursal.getNumero().toString());
            txtDireccion.setText(sucursal.getDireccion());
            cbResponsableTecnico.setSelectedItem(sucursal.getResponsableTecnico().getNombre() + " DNI: " + sucursal.getResponsableTecnico().getDni());
            sucursalGuardada = sucursal;
        }
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });

        btnGuardar.addActionListener(e -> {
            //TODO: Validar datos
            SucursalController sucursalController = SucursalController.getInstance();
            UsuarioController usuarioController = UsuarioController.getInstance();
            UsuarioDTO responsableTecnico = usuarioController.buscarUsuario(cbResponsableTecnico.getSelectedItem().toString().split(": ")[1]);

            if(sucursalGuardada == null) {
                sucursalGuardada = new SucursalDTO(
                        Long.valueOf(txtNumero.getText()),
                        txtDireccion.getText(),
                        usuarioController.buscarUsuario(responsableTecnico.getDni())
                );

                sucursalController.altaSucursal(
                        Long.valueOf(txtNumero.getText()),
                        txtDireccion.getText(),
                        usuarioController.buscarUsuario(responsableTecnico.getDni())
                );
            }
            else {
                sucursalGuardada.setDireccion(txtDireccion.getText());
                sucursalGuardada.setResponsableTecnico(responsableTecnico);

                sucursalController.modificarSucursal(
                        Long.valueOf(txtNumero.getText()),
                        txtDireccion.getText(),
                        usuarioController.buscarUsuario(responsableTecnico.getDni())
                );
            }
            self.dispose();
        });
    }

    public SucursalDTO showDialog() {
        setVisible(true);
        return sucursalGuardada;
    }
}
