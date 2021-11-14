package ar.edu.uade.model.dto;

import ar.edu.uade.model.Rol;
import ar.edu.uade.model.Usuario;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private String nombre;
    private String domicilio;
    private String dni;
    private Date fechaNacimiento;
    private Rol rol;

    public UsuarioDTO(String nombreUsuario, String email, String contrasena, String nombre, String domicilio, String dni, Date fechaNacimiento, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
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

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getContrasena(),
                usuario.getNombre(),
                usuario.getDomicilio(),
                usuario.getDni(),
                usuario.getFechaNacimiento(),
                usuario.getRol());
    }

    public static List<UsuarioDTO> fromEntities(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDTO::fromEntity).collect(Collectors.toList());
    }
}
