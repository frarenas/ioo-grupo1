package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;

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
            SucursalController sucursalController = SucursalController.getInstance();
            UsuarioController usuarioController = UsuarioController.getInstance();
            UsuarioDTO responsableTecnico = usuarioController.buscarUsuario(cbResponsableTecnico.getSelectedItem().toString().split(": ")[1]);


            //long valor = ;
            //TODO: Validar datos
            if(txtNumero.getText().length() > 0) {
                if(sucursalController.buscarSucursal(Long.valueOf(txtNumero.getText())) != null) {
                    if(txtDireccion.getText().length() > 0) {
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
                    }
                    else {
                        //No ingresó nada en el campo de dirección.
                        showMessageDialog(null, "No pudimos reconocer la dirección ingresada.");
                    }
                }
                else {
                    //El número de sucursal ingresado ya está usado por otra sucursal.
                    showMessageDialog(null, "El número de sucursal ingresado le pertenece a otra sucursal.");
                }
            }
            else {
                //No ingresó un número de sucursal.
                showMessageDialog(null, "No pudimos reconocer el número de sucursal.");
            }
        });
    }

    public SucursalDTO showDialog() {
        setVisible(true);
        return sucursalGuardada;
    }
}
