package ar.edu.uade.ui.peticion;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.controller.PeticionController;
import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.model.dto.PeticionDTO;
import ar.edu.uade.model.dto.SucursalDTO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.showMessageDialog;

public class EditarPeticionUI extends JDialog {
    private JTextField txtId;
    private JComboBox txtDniPaciente;
    private JTextField txtObraSocial;
    private JFormattedTextField txtFechaCarga;
    private JFormattedTextField txtFechaEntrega;
    private JComboBox txtNroSucursal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private EditarPeticionUI self;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EditarPeticionUI(Window owner, PeticionDTO peticionDto) {
        super(owner, peticionDto == null ? "Nueva peticion" : "Editar peticion");
        try {
            self = this;

            this.setContentPane(pnlPrincipal);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(owner);
            this.pack();
            this.setModal(true);

            String DATE_MASK = "##/##/####";
            MaskFormatter maskFechaCarga = new MaskFormatter(DATE_MASK);
            MaskFormatter maskFechaEntrega = new MaskFormatter(DATE_MASK);

            maskFechaCarga.install(txtFechaCarga);
            maskFechaEntrega.install(txtFechaEntrega);

            cargarDni();
            cargarSucursales();
            setActions();

            if (peticionDto != null) {
                cargarFormulario(peticionDto);
            }

            btnGuardar.addActionListener(e -> {
                guardarPeticion();
                self.dispose();
            });
        } catch (ParseException e) {
            e.printStackTrace();
            showMessageDialog(null, "Error al construir el componente");
        }
    }

    private void cargarFormulario(PeticionDTO peticionDto) {
        txtId.setText(peticionDto.getId().toString());
        txtObraSocial.setText(peticionDto.getObraSocial());
        txtFechaCarga.setText(dateFormat.format(peticionDto.getFechaCarga()));
        txtFechaEntrega.setText(dateFormat.format(peticionDto.getFechaCarga()));
    }

    private void cargarSucursales() {
        for (SucursalDTO sucursalDto : SucursalController.getInstance().listarSucursales()) {
            txtNroSucursal.addItem(sucursalDto.getNumero());
        }
    }

    private void cargarDni() {
        for (PacienteDTO pacienteDTO : PacienteController.getInstance().listarPacientes()) {
            txtDniPaciente.addItem(pacienteDTO.getDni());
        }
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }

    private void guardarPeticion() {
        try {
            PeticionController peticionController = PeticionController.getInstance();
            PacienteController pacienteController = PacienteController.getInstance();
            SucursalController sucursalController = SucursalController.getInstance();

            PacienteDTO pacienteDto = pacienteController.buscarPaciente((String) txtDniPaciente.getSelectedItem());
            SucursalDTO sucursalDto = sucursalController.buscarSucursal((Long) txtNroSucursal.getSelectedItem());

            if (txtId.getText().trim().isEmpty() ||
                    txtObraSocial.getText().trim().isEmpty() ||
                    txtFechaCarga.getText().trim().isEmpty() ||
                    txtFechaEntrega.getText().trim().isEmpty()) {
                showMessageDialog(null, "Debe completar todos los campos para continuar");
            } else {
                peticionController.altaPeticion(
                        Long.parseLong(txtId.getText()),
                        pacienteDto,
                        txtObraSocial.getText(),
                        obtenerFecha(txtFechaCarga),
                        obtenerFecha(txtFechaEntrega),
                        sucursalDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog(null, "Error al guardar la petición -> "+e.getMessage());
        }
    }

    private Date obtenerFecha(JFormattedTextField txtFechaCarga) {
        Date dateParsed = new Date();
        try {
            dateParsed = dateFormat.parse(txtFechaCarga.getText());
        } catch (ParseException e) {
            showMessageDialog(null, "El formato de fecha no es válido");
        }
        return dateParsed;
    }
}
