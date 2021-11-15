package ar.edu.uade.ui.sucursal;

import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.controller.SucursalController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.List;

public class SucursalUI {
    public JPanel pnlPrincipal;
    private JButton btnAgregar;
    private JTable tblSucursales;

    public SucursalUI(SucursalController sucursalController) {

        //Datos tabla
        List<SucursalDTO> sucursales = sucursalController.getSucursales();
        SucursalTableModel sucursalTableModel = new SucursalTableModel(sucursalController, sucursales);
        tblSucursales.setModel(sucursalTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblSucursales.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblSucursales.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblSucursales.addMouseListener(new JTableButtonMouseListener(tblSucursales));

        //Agregar
        btnAgregar.addActionListener(e -> {
            EditarSucursalUI editarSucursalUI = new EditarSucursalUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal),
                    sucursalController,
                    null
            );
            SucursalDTO sucursalGuardada = editarSucursalUI.showDialog();
            if(sucursalGuardada != null) {
                sucursalTableModel.actualizarTabla(sucursalGuardada);
            }
        });
    }
}
