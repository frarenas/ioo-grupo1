package ar.edu.uade.ui.estudio;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.model.dto.EstudioDTO;
import ar.edu.uade.model.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EstudioTableModel extends AbstractTableModel {
    private final PeticionController peticionController;

    private final List<EstudioDTO> estudios;
    private final PeticionDTO peticion;
    protected String[] columnNames = new String[] { "Código", "Práctica", "Resultado", "Editar", "Eliminar" };
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, JButton.class, JButton.class};

    public EstudioTableModel(PeticionController peticionController, PeticionDTO peticion) {
        this.peticionController = peticionController;
        this.peticion = peticion;
        this.estudios = peticion.getEstudios();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return estudios.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return estudios.get(rowIndex).getCodigo();
            case 1: return estudios.get(rowIndex).getPractica().getNombre();
            case 2: return getResultado(estudios.get(rowIndex));
            case 3: return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 4: return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default: return null;
        }
    }

    private String getResultado(EstudioDTO estudio){
        if(estudio.getResultadoPeticion() != null && estudio.getResultadoPeticion().getResultado() != null) {
            if(estudio.getPractica().getValorReservado()) {
                return "Retirar por sucursal.";
            }else {
                return estudio.getResultadoPeticion().getResultado().toString();
            }
        }else {
            return "";
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarEstudioUI editarEstudioUI = new EditarEstudioUI(
                    JOptionPane.getFrameForComponent(button),
                    peticionController,
                    peticion,
                    estudios.get(rowIndex)
            );
            EstudioDTO estudioGuardado = editarEstudioUI.showDialog();
            if (estudioGuardado != null){
                estudios.set(rowIndex, estudioGuardado);
                fireTableDataChanged();
            }
            peticion.setEstudios(estudios);
            peticionController.modificarPeticion(peticion);
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            estudios.remove(rowIndex);
            fireTableDataChanged();
            peticion.setEstudios(estudios);
            peticionController.modificarPeticion(peticion);
        });
        return button;
    }

    public void actualizarTabla(EstudioDTO estudio){
        this.estudios.add(estudio);
        fireTableDataChanged();
        peticion.setEstudios(estudios);
        peticionController.modificarPeticion(peticion);
    }
}
