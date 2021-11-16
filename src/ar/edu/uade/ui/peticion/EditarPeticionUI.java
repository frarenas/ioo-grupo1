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
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class EditarPeticionUI extends JDialog {
    private JTextField txtId;
    private JComboBox<PacienteDTO> cbPaciente;
    private JTextField txtObraSocial;
    private JFormattedTextField txtFechaCarga;
    private JFormattedTextField txtFechaEntrega;
    private JComboBox<SucursalDTO> cbSucursal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipal;

    private final EditarPeticionUI self;
    private final PeticionController peticionController;
    private PeticionDTO peticionGuardada = null;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public EditarPeticionUI(Window owner, PeticionController peticionController, PeticionDTO peticionDto) {
        super(owner, peticionDto == null ? "Nueva peticion" : "Editar peticion");

        self = this;
        this.peticionController = peticionController;

        pnlPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.setContentPane(pnlPrincipal);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(owner);
        this.setModal(true);

        try {
            String DATE_MASK = "##/##/####";
            MaskFormatter maskFechaCarga = new MaskFormatter(DATE_MASK);
            MaskFormatter maskFechaEntrega = new MaskFormatter(DATE_MASK);

            maskFechaCarga.install(txtFechaCarga);
            maskFechaEntrega.install(txtFechaEntrega);
        }catch (ParseException ignored){

        }


        cargarDni();
        cargarSucursales();

        if (peticionDto != null) {
            cargarFormulario(peticionDto);
        }

        btnGuardar.addActionListener(e -> guardarPeticion(peticionDto));

        btnCancelar.addActionListener(e -> self.dispose());
    }

    private void cargarFormulario(PeticionDTO peticionDto) {
        txtId.setText(peticionDto.getId().toString());
        txtObraSocial.setText(peticionDto.getObraSocial());
        txtFechaCarga.setText(dateFormat.format(peticionDto.getFechaCarga()));
        txtFechaEntrega.setText(dateFormat.format(peticionDto.getFechaCarga()));
        setComboPaciente(peticionDto.getPaciente().getDni());
        setComboSucursal(peticionDto.getSucursal().getNumero());
    }

    private void cargarSucursales() {
        for (SucursalDTO sucursalDto : SucursalController.getInstance().listarSucursales()) {
            cbSucursal.addItem(sucursalDto);
        }
    }

    private void cargarDni() {
        for (PacienteDTO pacienteDTO : PacienteController.getInstance().listarPacientes()) {
            cbPaciente.addItem(pacienteDTO);
        }
    }

    private void guardarPeticion(PeticionDTO peticionDTO) {
        try {
            if (txtId.getText().trim().isEmpty() ||
                    txtObraSocial.getText().trim().isEmpty() ||
                    txtFechaCarga.getText().trim().isEmpty() ||
                    txtFechaEntrega.getText().trim().isEmpty()) {
                showMessageDialog(null, "Debe completar todos los campos para continuar");
            } else {
                peticionGuardada = peticionController.altaPeticion(
                        Long.parseLong(txtId.getText()),
                        (PacienteDTO) cbPaciente.getSelectedItem(),
                        txtObraSocial.getText(),
                        obtenerFecha(txtFechaCarga),
                        obtenerFecha(txtFechaEntrega),
                        (SucursalDTO) cbSucursal.getSelectedItem());
            }

            if(peticionDTO == null){
                peticionDTO = new PeticionDTO(
                        Long.parseLong(txtId.getText()),
                        (PacienteDTO) cbPaciente.getSelectedItem(),
                        txtObraSocial.getText(),
                        obtenerFecha(txtFechaCarga),
                        null,
                        obtenerFecha(txtFechaEntrega),
                        (SucursalDTO) cbSucursal.getSelectedItem()
                );

                peticionController.altaPeticion(peticionDTO);
            }else {
                peticionDTO.setPaciente((PacienteDTO) cbPaciente.getSelectedItem());
                peticionDTO.setObraSocial(txtObraSocial.getText());
                peticionDTO.setFechaCarga(obtenerFecha(txtFechaCarga));
                peticionDTO.setFechaEntrega(obtenerFecha(txtFechaEntrega));
                peticionDTO.setSucursal((SucursalDTO) cbSucursal.getSelectedItem());

                peticionController.modificarPeticion(peticionDTO);
            }

            peticionGuardada = peticionDTO;
            self.dispose();
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

    public PeticionDTO showDialog() {
        setVisible(true);
        return peticionGuardada;
    }

    private void setComboPaciente(String dniPaciente) {
        for (int i = 0; i < cbPaciente.getItemCount(); i++) {
            if(Objects.equals(cbPaciente.getItemAt(i).getDni(), dniPaciente)) {
                cbPaciente.setSelectedIndex(i);
            }
        }
    }

    private void setComboSucursal(Long nroSucursal) {
        for (int i = 0; i < cbSucursal.getItemCount(); i++) {
            if(Objects.equals(cbSucursal.getItemAt(i).getNumero(), nroSucursal)) {
                cbSucursal.setSelectedIndex(i);
            }
        }
    }
}
