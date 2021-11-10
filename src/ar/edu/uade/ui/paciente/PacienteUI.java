package ar.edu.uade.ui.paciente;

import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;
import ar.edu.uade.controller.PacienteController;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class PacienteUI {
    public JPanel pnlPrincipal;
    private JTable tblPacientes;
    private JButton btnAgregar;

    public PacienteUI(PacienteController pacienteController) {

        //Datos tabla
        List<PacienteDTO> pacientes = pacienteController.getPacientes();
        PacienteTableModel pacienteTableModel = new PacienteTableModel(pacienteController, pacientes);
        tblPacientes.setModel(pacienteTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblPacientes.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblPacientes.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblPacientes.addMouseListener(new JTableButtonMouseListener(tblPacientes));

        //Agregar
        btnAgregar.addActionListener(e -> {
            EditarPacienteUI editarPacienteUI = new EditarPacienteUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal),
                    pacienteController,
                    null
            );
            PacienteDTO pacienteGuardado = editarPacienteUI.showDialog();
            if (pacienteGuardado != null){
                pacienteTableModel.actualizarTabla(pacienteGuardado);
            }
        });
    }
}
