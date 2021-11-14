package ar.edu.uade.ui.usuario;

import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.Usuario;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.model.dto.PracticaDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {

    private final List<Usuario> usuarios;
    protected String[] columnNames = new String[]{"NombreUsuario", "Email", "Nombre", "Domicilio", "DNI", "Fecha nacimiento", "Rol", "Editar", "Eliminar"};
    protected Class[] columnClasses = new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, JButton.class, JButton.class};
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return usuarios.get(rowIndex).getNombreUsuario();
            case 1:
                return usuarios.get(rowIndex).getEmail();
            case 2:
                return usuarios.get(rowIndex).getNombre();
            case 3:
                return usuarios.get(rowIndex).getDomicilio();
            case 4:
                return usuarios.get(rowIndex).getDni();
            case 5:
                return dateFormat.format(usuarios.get(rowIndex).getFechaNacimiento());
            case 6:
                return usuarios.get(rowIndex).getRol();
            case 7:
                return setBotonEditar(getColumnName(columnIndex), rowIndex);
            case 8:
                return setBotonEliminar(getColumnName(columnIndex), rowIndex);
            default:
                return null;
        }
    }

    private JButton setBotonEditar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            EditarUsuarioUI editarUsuarioUI = new EditarUsuarioUI(
                    JOptionPane.getFrameForComponent(button),
                    UsuarioDTO.fromEntity(usuarios.get(rowIndex))
            );
            UsuarioDTO usuarioGuardado = editarUsuarioUI.showDialog();
            if (usuarioGuardado != null){
                usuarios.set(rowIndex, new Usuario(usuarioGuardado));
                fireTableDataChanged();
            }
        });
        return button;
    }

    private JButton setBotonEliminar(String nombre, int rowIndex) {
        final JButton button = new JButton(nombre);
        button.addActionListener(e -> {
            UsuarioController usuarioController = UsuarioController.getInstance();
            usuarioController.bajaUsuario(usuarios.get(rowIndex).getDni());
            usuarios.remove(rowIndex);
            fireTableDataChanged();
        });
        return button;
    }

    public void actualizarTabla(UsuarioDTO usuarioDto){
        this.usuarios.add(new Usuario(usuarioDto));
        fireTableDataChanged();
    }
}
