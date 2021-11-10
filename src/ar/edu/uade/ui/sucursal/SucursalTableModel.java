package ar.edu.uade.ui.sucursal;

import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.model.Sucursal;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SucursalTableModel extends AbstractTableModel {

    private final List<SucursalDTO> sucursales;
    protected String[] columnNames = new String[] { "Número", "Dirección", "Responsable Técnico", "Editar", "Eliminar" };
    protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, JButton.class, JButton.class};

    public SucursalTableModel(List<SucursalDTO> sucursales) {
        this.sucursales = sucursales;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return sucursales.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return sucursales.get(rowIndex).getNumero();
            case 1: return sucursales.get(rowIndex).getDireccion();
            case 2: return sucursales.get(rowIndex).getResponsableTecnico().getNombre();
            case 3: return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 4: return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default: return null;
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarSucursalUI editarSucursalUI = new EditarSucursalUI(
                    JOptionPane.getFrameForComponent(button),
                    sucursales.get(rowIndex)
            );
            SucursalDTO sucursalGuardada = editarSucursalUI.showDialog();
            if (sucursalGuardada != null){
                sucursales.set(rowIndex, sucursalGuardada);
                fireTableDataChanged();
            }
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            SucursalController sucursalController = SucursalController.getInstance();

            SeleccionarSucursalUI seleccionarSucursalUI = new SeleccionarSucursalUI(
                    JOptionPane.getFrameForComponent(button),
                    sucursales.get(rowIndex)
            );
            SucursalDTO sucursalGuardada = seleccionarSucursalUI.showDialog();
            if (sucursalGuardada != null){
                //TODO: Necesito obtener la sucursal a la que le pasaría las órdenes de esta sucursal
                sucursalController.bajaSucursal(sucursales.get(rowIndex).getNumero(), sucursalGuardada);
                sucursales.remove(rowIndex);
                fireTableDataChanged();
            }
        });
        return button;
    }

    public void actualizarTabla(SucursalDTO sucursal){
        this.sucursales.add(sucursal);
        fireTableDataChanged();
    }
}
