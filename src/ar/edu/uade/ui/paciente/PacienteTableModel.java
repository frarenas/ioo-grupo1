package ar.edu.uade.ui.paciente;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.model.Paciente;
import ar.edu.uade.ui.Login;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {

    private final List<Paciente> pacientes;
    protected String[] columnNames = new String[] { "Nombre", "DNI", "Email", "Editar", "Eliminar" };
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, JButton.class, JButton.class};

    public PacienteTableModel(List<Paciente> pacientes) {
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
                    pacientes.get(rowIndex)
            );
            editarPacienteUI.setVisible(true);
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            PacienteController pacienteController = PacienteController.getInstance();
            pacienteController.bajaPaciente(pacientes.get(rowIndex).getDni());
            pacientes.remove(rowIndex);
            fireTableDataChanged();
        });
        return button;
    }
}
