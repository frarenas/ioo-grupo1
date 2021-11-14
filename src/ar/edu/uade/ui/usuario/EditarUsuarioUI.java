package ar.edu.uade.ui.usuario;

import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.Rol;
import ar.edu.uade.model.dto.PracticaDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static javax.swing.JOptionPane.showMessageDialog;

public class EditarUsuarioUI extends JDialog {
    private JTextField txtNombreUsuario;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JTextField txtNombre;
    private JTextField txtDomicilio;
    private JFormattedTextField txtDNI;
    private JFormattedTextField txtFechaNacimiento;
    private JComboBox txtRol;

    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipalUsuario;

    private EditarUsuarioUI self;
    private UsuarioDTO usuarioGuardado = null;

    public EditarUsuarioUI(Window owner, UsuarioDTO usuarioDTO) {
        super(owner, usuarioDTO == null ? "Nuevo Usuario" : "Editar Usuario");
        try {
            self = this;

            this.setContentPane(pnlPrincipalUsuario);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(owner);
            this.pack();
            this.setModal(true);
            this.setActions();

            MaskFormatter maskFechaNacimiento = new MaskFormatter("##/##/####");
            maskFechaNacimiento.install(txtFechaNacimiento);

            MaskFormatter maskDni = new MaskFormatter("########");
            maskDni.install(txtDNI);

            loadRoles();

            if (usuarioDTO != null) {
                completarFormulario(usuarioDTO);
            }

            btnGuardar.addActionListener(e -> {
                guardarUsuario();
                self.dispose();
            });
        } catch (ParseException e) {
            e.printStackTrace();
            showMessageDialog(null, "Error al construir el componente");
        }
    }

    private void completarFormulario(UsuarioDTO usuario) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtNombreUsuario.setText(usuario.getDni());
        txtEmail.setText(usuario.getEmail());
        txtContrasena.setText(usuario.getContrasena());
        txtNombre.setText(usuario.getNombre());
        txtDomicilio.setText(usuario.getDomicilio());
        txtDNI.setText(usuario.getDni());
        txtFechaNacimiento.setText(dateFormat.format(usuario.getFechaNacimiento()));
        txtRol.setSelectedItem(usuario.getRol());
    }

    private void loadRoles() {
        txtRol.addItem(Rol.ADMINISTRADOR);
        txtRol.addItem(Rol.LABORISTA);
        txtRol.addItem(Rol.RECEPCION);
    }

    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }

    private void guardarUsuario() {
        try {
            UsuarioController usuarioController = UsuarioController.getInstance();

            if (txtNombreUsuario.getText().trim().isEmpty() ||
                    txtEmail.getText().isEmpty() ||
                    txtContrasena.getPassword().length == 0 ||
                    txtNombre.getText().isEmpty() ||
                    txtDomicilio.getText().isEmpty() ||
                    txtDNI.getText().isEmpty() ||
                    txtEmail.getText().isEmpty() ||
                    txtFechaNacimiento.getText().isEmpty()) {

                showMessageDialog(null, "Debe completar todos los campos para continuar");

            } else {
                usuarioGuardado = usuarioController.altaUsuario(
                        txtNombreUsuario.getText(),
                        txtEmail.getText(),
                        String.valueOf(txtContrasena.getPassword()),
                        txtNombre.getText(),
                        txtDomicilio.getText(),
                        txtDNI.getText(),
                        Date.from(LocalDate.parse(txtFechaNacimiento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        (Rol) txtRol.getSelectedItem());
                self.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessageDialog(null, "Error al guardar el usuario -> " + e.getMessage());
        }
    }

    public UsuarioDTO showDialog() {
        setVisible(true);
        return usuarioGuardado;
    }
}
