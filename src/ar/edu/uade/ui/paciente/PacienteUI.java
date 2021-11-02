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

    public PacienteUI() {

        //Datos tabla
        List<PacienteDTO> pacientes = PacienteDTO.fromEntities(new ArrayList<>(PacienteController.pacienteDB.values()));
        PacienteTableModel pacienteTableModel = new PacienteTableModel(pacientes);
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
                    null
            );
            editarPacienteUI.setVisible(true);
        });
    }

}
