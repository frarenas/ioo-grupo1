package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SeleccionarSucursalUI extends JDialog {
    private JPanel pnlPrincipal;
    private JComboBox cbSucursalDestino;
    private JButton btnCancelar;
    private JButton btnEliminarSucursal;

    //Esto es para instanciar el cuadro de diálogo, porque sino no lo puedo referenciar con this más adelante.
    private SeleccionarSucursalUI self;

    private SucursalDTO sucursalGuardada = null;

    public SeleccionarSucursalUI(Window owner, SucursalDTO sucursalEliminable) {
        super(owner, sucursalEliminable == null? "" : "Derivar peticiones activas de la sucursal " + sucursalEliminable.getNumero());
        SucursalController sucursalController = SucursalController.getInstance();

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();

        setModal(true);

        setActions();

        List<SucursalDTO> sucursales = sucursalController.listarSucursales();
        for(SucursalDTO sucursal: sucursales) {
            if(sucursal.getNumero() != sucursalEliminable.getNumero()) {
                cbSucursalDestino.addItem(sucursal.getNumero() + " - " + sucursal.getDireccion());
            }
        }
    }
    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });

        btnEliminarSucursal.addActionListener(e -> {
            SucursalController sucursalController = SucursalController.getInstance();
            SucursalDTO sucursalNueva = sucursalController.buscarSucursal(Long.valueOf(cbSucursalDestino.getSelectedItem().toString().split(" - ")[0]));
            sucursalGuardada = sucursalNueva;
            self.dispose();
        });
    }

    public SucursalDTO showDialog() {
        setVisible(true);
        return sucursalGuardada;
    }

}
