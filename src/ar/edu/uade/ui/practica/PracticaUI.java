package ar.edu.uade.ui.practica;

import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.model.dto.PracticaDTO;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.List;

public class PracticaUI {
    public JPanel pnlPrincipal;
    private JTable tblPracticas;
    private JButton btnAgregar;

    public PracticaUI(PracticaController practicaController) {

        //Datos tabla
        List<PracticaDTO> practicas = practicaController.getPracticas();
        PracticaTableModel practicaTableModel = new PracticaTableModel(practicaController, practicas);
        tblPracticas.setModel(practicaTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblPracticas.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblPracticas.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblPracticas.addMouseListener(new JTableButtonMouseListener(tblPracticas));

        //Agregar
        btnAgregar.addActionListener(e -> {
            EditarPracticaUI editarPracticaUI = new EditarPracticaUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal),
                    practicaController,
                    null
            );
            PracticaDTO practicaGuardada = editarPracticaUI.showDialog();
            if (practicaGuardada != null){
                practicaTableModel.actualizarTabla(practicaGuardada);
            }
        });
    }
}
