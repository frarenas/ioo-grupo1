package ar.edu.uade.ui.usuario;

import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.Rol;
import ar.edu.uade.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EditarUsuarioUI extends JDialog {
    private JTextField txtNombreUsuario;
    private JTextField txtEmail;
    private JTextField txtContrasena;
    private JTextField txtNombre;
    private JTextField txtDomicilio;
    private JTextField txtDNI;
    private JTextField txtFechaNacimiento;
    private JComboBox txtRol;

    private JButton btnCancelar;
    private JButton btnGuardar;
    private JPanel pnlPrincipalUsuario;

    private EditarUsuarioUI self;

    public EditarUsuarioUI(Window owner, UsuarioDTO usuario) {
        super(owner, usuario == null ? "Nuevo Usuario" : "Editar Usuario");

        self = this;

        this.setContentPane(pnlPrincipalUsuario);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(owner);
        this.pack();

        setModal(true);
        setActions();

        txtRol.addItem(Rol.ADMINISTRADOR);
        txtRol.addItem(Rol.LABORISTA);
        txtRol.addItem(Rol.RECEPCION);

        if (usuario != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(usuario.getFechaNacimiento());

            txtNombreUsuario.setText(usuario.getDni());
            txtEmail.setText(usuario.getEmail());
            txtContrasena.setText(usuario.getContrasena());
            txtNombre.setText(usuario.getNombre());
            txtDomicilio.setText(usuario.getDomicilio());
            txtDNI.setText(usuario.getDni());
            txtFechaNacimiento.setText(strDate);
            txtRol.setSelectedItem(usuario.getRol());
        }

        btnGuardar.addActionListener(e -> {
            guardarUsuario(usuario);
            self.dispose();
        });
    }


    private void setActions() {
        btnCancelar.addActionListener(e -> {
            self.dispose();
        });
    }


    private void guardarUsuario(UsuarioDTO usuarioDTO){
        //TODO: validar
        try {
            UsuarioController usuarioController = new UsuarioController();

            usuarioController.altaUsuario(
                    txtNombreUsuario.getText(),
                    txtEmail.getText(),
                    txtContrasena.getText(),
                    txtNombre.getText(),
                    txtDomicilio.getText(),
                    txtDNI.getText(),
                    Date.from(LocalDate.parse(txtFechaNacimiento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    (Rol) txtRol.getSelectedItem());

        } catch (Exception e) {
            //TODO validar
            e.printStackTrace();
        }
    }
}
