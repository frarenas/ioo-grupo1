package ar.edu.uade.ui.paciente;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.model.ResultadoOperacion;
import ar.edu.uade.model.dto.PacienteDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {

    private final PacienteController pacienteController;

    private final List<PacienteDTO> pacientes;
    protected String[] columnNames = new String[] { "Nombre", "DNI", "Email", "Editar", "Eliminar" };
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, JButton.class, JButton.class};

    public PacienteTableModel(PacienteController pacienteController, List<PacienteDTO> pacientes) {
        this.pacienteController = pacienteController;
        this.pacientes = pacientes;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return pacientes.get(rowIndex).getNombre();
            case 1: return pacientes.get(rowIndex).getDni();
            case 2: return pacientes.get(rowIndex).getEmail();
            case 3: return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 4: return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default: return null;
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarPacienteUI editarPacienteUI = new EditarPacienteUI(
                    JOptionPane.getFrameForComponent(button),
                    pacienteController,
                    pacientes.get(rowIndex)
            );
            PacienteDTO pacienteGuardado = editarPacienteUI.showDialog();
            if (pacienteGuardado != null){
                pacientes.set(rowIndex, pacienteGuardado);
                fireTableDataChanged();
            }
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            ResultadoOperacion resultadoOperacion = pacienteController.bajaPaciente(pacientes.get(rowIndex).getDni());
            if (resultadoOperacion.isExito()) {
                pacientes.remove(rowIndex);
                fireTableDataChanged();
            }
            else {
                JOptionPane.showMessageDialog(null, resultadoOperacion.getMensaje());
            }
        });
        return button;
    }

    public void actualizarTabla(PacienteDTO paciente){
        this.pacientes.add(paciente);
        fireTableDataChanged();
    }
}
