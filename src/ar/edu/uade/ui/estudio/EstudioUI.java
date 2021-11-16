package ar.edu.uade.ui.estudio;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.Rol;
import ar.edu.uade.model.dto.EstudioDTO;
import ar.edu.uade.model.dto.PeticionDTO;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EstudioUI extends JDialog {
    private JPanel pnlPrincipal;
    private JButton btnAgregar;
    private JTable tblEstudios;

    public EstudioUI(Window owner, PeticionController peticionController, PeticionDTO peticion){
        super(owner, "Estudios");

        //Datos tabla
        EstudioTableModel estudioTableModel = new EstudioTableModel(peticionController, peticion);
        tblEstudios.setModel(estudioTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblEstudios.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblEstudios.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblEstudios.addMouseListener(new JTableButtonMouseListener(tblEstudios));

        if(UsuarioController.usuarioLogueado.getRol() == Rol.RECEPCION) {
            btnAgregar.setVisible(false);
            tblEstudios.removeColumn(tblEstudios.getColumn("Editar"));
            tblEstudios.removeColumn(tblEstudios.getColumn("Eliminar"));
        }else {
            btnAgregar.setVisible(true);
            //Agregar
            btnAgregar.addActionListener(e -> {
                EditarEstudioUI editarEstudioUI = new EditarEstudioUI(
                        JOptionPane.getFrameForComponent(pnlPrincipal),
                        peticionController,
                        peticion,
                        null
                );
                EstudioDTO estudioGuardado = editarEstudioUI.showDialog();
                if (estudioGuardado != null) {
                    estudioTableModel.actualizarTabla(estudioGuardado);
                }
            });
        }

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(pnlPrincipal);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setModal(true);
    }
}
