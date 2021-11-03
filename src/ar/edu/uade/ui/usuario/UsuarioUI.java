package ar.edu.uade.ui.usuario;

import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.Usuario;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class UsuarioUI {
    public JPanel pnlPrincipal;
    private JTable tblUsuarios;
    private JButton btnAgregar;

    public UsuarioUI() {

        //Datos tabla
        List<Usuario> usuarios = new ArrayList<>(UsuarioController.usuarioDB.values());
        UsuarioTableModel usuarioTableModel = new UsuarioTableModel(usuarios);
        tblUsuarios.setModel(usuarioTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblUsuarios.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblUsuarios.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblUsuarios.addMouseListener(new JTableButtonMouseListener(tblUsuarios));

        //Agregar
        btnAgregar.addActionListener(e -> {
            EditarUsuarioUI editarUsuarioUI = new EditarUsuarioUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal),
                    null
            );
            editarUsuarioUI.setVisible(true);
        });
    }

}
