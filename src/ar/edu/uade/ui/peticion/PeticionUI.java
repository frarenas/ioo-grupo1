package ar.edu.uade.ui.peticion;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.model.dto.PeticionDTO;
import ar.edu.uade.util.JTableButtonMouseListener;
import ar.edu.uade.util.JTableButtonRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.List;

public class PeticionUI {
    public JPanel pnlPrincipal;
    private JTable tblPeticiones;
    private JButton btnAgregar;
    private JButton btnListarCriticos;

    public PeticionUI(PeticionController peticionController) {

        //Datos tabla
        List<PeticionDTO> peticiones = peticionController.getPeticiones();
        PeticionTableModel peticionTableModel = new PeticionTableModel(peticionController, peticiones);
        tblPeticiones.setModel(peticionTableModel);

        //Botones tabla
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblPeticiones.getColumn("Resultados").setCellRenderer(buttonRenderer);
        tblPeticiones.getColumn("Editar").setCellRenderer(buttonRenderer);
        tblPeticiones.getColumn("Eliminar").setCellRenderer(buttonRenderer);
        tblPeticiones.addMouseListener(new JTableButtonMouseListener(tblPeticiones));

        //Agregar
        btnAgregar.addActionListener(e -> {
            EditarPeticionUI editarPeticionUI = new EditarPeticionUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal),
                    peticionController,
                    null
            );
            PeticionDTO peticionDto = editarPeticionUI.showDialog();
            if (peticionDto != null){
                peticionTableModel.actualizarTabla(peticionDto);
            }

        });

        //Listar peticiones criticas
        btnListarCriticos.addActionListener(e -> {
            PeticionesCriticasUI peticionesCriticasUI = new PeticionesCriticasUI(
                    JOptionPane.getFrameForComponent(pnlPrincipal)
            );
            peticionesCriticasUI.setVisible(true);
        });
    }

}
