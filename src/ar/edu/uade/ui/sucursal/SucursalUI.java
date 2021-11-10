package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.model.Sucursal;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class SucursalUI {
    public JPanel pnlPrincipal;
    private JButton btnAgregar;
    private JTable tblSucursales;

    public SucursalUI() {

        //Datos tabla
        List<SucursalDTO> sucursales = SucursalDTO.fromEntities(new ArrayList<>(SucursalController.sucursalDB.values()));
        SucursalTableModel sucursalTableModel = new SucursalTableModel(sucursales);
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
                    null
            );
            SucursalDTO sucursalGuardada = editarSucursalUI.showDialog();
            if(sucursalGuardada != null) {
                sucursalTableModel.actualizarTabla(sucursalGuardada);
            }
        });
    }
}
