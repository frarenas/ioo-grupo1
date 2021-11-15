package ar.edu.uade.ui.peticion;

import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.model.dto.PeticionDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class PeticionesCriticasUI extends JDialog {

    private JPanel pnlPrincipal;
    private JTable tblPeticionesCriticas;

    private PeticionesCriticasUI self;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public PeticionesCriticasUI(Window owner) {
        super(owner, "Peticiones criticas");

        self = this;

        this.setContentPane(pnlPrincipal);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();
        this.setModal(true);
        this.setSize(new Dimension(700, 300));

        String[] columns = {"Id", "DNI", "Obra social", "Fecha carga", "Fecha entrega", "Nro sucursal"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        tblPeticionesCriticas.setModel(model);

        cargarPeticionesCriticas(model);
    }

    private void cargarPeticionesCriticas(DefaultTableModel model) {
        for (PeticionDTO peticionCritica : PeticionController.getInstance().obtenerPeticionesCriticas()) {
            Vector row = new Vector();
            row.add(peticionCritica.getId());
            row.add(peticionCritica.getPaciente().getDni());
            row.add(peticionCritica.getObraSocial());
            row.add(dateFormat.format(peticionCritica.getFechaCarga()));
            row.add(dateFormat.format(peticionCritica.getFechaEntrega()));
            row.add(peticionCritica.getSucursal().getNumero());
            model.addRow(row);
        }
    }
}
