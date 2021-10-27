package model;

import model.dto.UsuarioDTO;

import java.util.Date;

public class Usuario {
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private String nombre;
    private String domicilio;
    private String dni;
    private Date fechaNacimiento;
    private Rol rol;

    public Usuario(String nombreUsuario, String email, String contrasena, String nombre, String domicilio, String dni, Date fechaNacimiento, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
    }

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nombreUsuario = usuarioDTO.getNombreUsuario();
        this.email = usuarioDTO.getEmail();
        this.contrasena = usuarioDTO.getContrasena();
        this.nombre = usuarioDTO.getNombre();
        this.domicilio = usuarioDTO.getDomicilio();
        this.dni = usuarioDTO.getDni();
        this.fechaNacimiento = usuarioDTO.getFechaNacimiento();
        this.rol = usuarioDTO.getRol();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
