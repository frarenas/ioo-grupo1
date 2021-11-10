package ar.edu.uade.ui.practica;

import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.model.dto.PracticaDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PracticaTableModel extends AbstractTableModel {

    private final PracticaController practicaController;

    private final List<PracticaDTO> practicas;
    protected String[] columnNames = new String[] { "Codigo", "Nombre", "Grupo", "Editar", "Eliminar" };
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, JButton.class, JButton.class};

    public PracticaTableModel(PracticaController practicaController, List<PracticaDTO> practicas) {
        this.practicas = practicas;
        this.practicaController = practicaController;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return practicas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return practicas.get(rowIndex).getCodigo();
            case 1: return practicas.get(rowIndex).getNombre();
            case 2: return practicas.get(rowIndex).getGrupo().getNombre();
            case 3: return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 4: return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default: return null;
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarPracticaUI editarPracticaUI = new EditarPracticaUI(
                    JOptionPane.getFrameForComponent(button),
                    practicaController,
                    practicas.get(rowIndex)
            );
            PracticaDTO practicaGuardada = editarPracticaUI.showDialog();
            if (practicaGuardada != null){
                practicas.set(rowIndex, practicaGuardada);
                fireTableDataChanged();
            }
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            practicaController.bajaPractica(practicas.get(rowIndex).getCodigo());
            practicas.remove(rowIndex);
            fireTableDataChanged();
        });
        return button;
    }

    public void actualizarTabla(PracticaDTO practica){
        this.practicas.add(practica);
        fireTableDataChanged();
    }
}
