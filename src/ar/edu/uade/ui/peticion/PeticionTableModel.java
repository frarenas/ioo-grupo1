package ar.edu.uade.ui.peticion;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.model.Peticion;
import ar.edu.uade.model.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PeticionTableModel extends AbstractTableModel {

    private final List<Peticion> peticiones;
    protected String[] columnNames = new String[]{"Id", "Paciente", "Obra social", "Fecha carga", "Fecha entrega", "Sucursal","Editar", "Eliminar"};
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, JButton.class, JButton.class};
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public PeticionTableModel(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return peticiones.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return peticiones.get(rowIndex).getId();
            case 1:
                return peticiones.get(rowIndex).getPaciente().getDni();
            case 2:
                return peticiones.get(rowIndex).getObraSocial();
            case 3:
                return dateFormat.format(peticiones.get(rowIndex).getFechaCarga());
            case 4:
                return dateFormat.format(peticiones.get(rowIndex).getFechaEntrega());
            case 5:
                return peticiones.get(rowIndex).getSucursal().getNumero();
            case 6:
                return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 7:
                return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default:
                return null;
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarPeticionUI editarPeticionUI = new EditarPeticionUI(
                    JOptionPane.getFrameForComponent(button),
                    PeticionDTO.fromEntity(peticiones.get(rowIndex))
            );
            PeticionDTO peticionGuardada = editarPeticionUI.showDialog();
            if (peticionGuardada != null){
                peticiones.set(rowIndex, new Peticion(peticionGuardada));
                fireTableDataChanged();
            }
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            PeticionController peticionController = PeticionController.getInstance();
            peticionController.bajaPeticion(peticiones.get(rowIndex).getId());
            peticiones.remove(rowIndex);
            fireTableDataChanged();
        });
        return button;
    }


    public void actualizarTabla(PeticionDTO peticionDTO){
        this.peticiones.add(new Peticion(peticionDTO));
        fireTableDataChanged();
    }
}
