package ar.edu.uade.controller;

import ar.edu.uade.model.Rol;
import ar.edu.uade.model.Usuario;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController {

    public static Map<String, Usuario> usuarioDB = new HashMap<>();

    public void altaUsuario(
            String nombreUsuario,
            String email,
            String contrasena,
            String nombre,
            String domicilio,
            String dni,
            Date fechaNacimiento,
            Rol rol
    ) {
        Usuario usuario = new Usuario(nombreUsuario,email,contrasena,nombre,domicilio,dni, fechaNacimiento, rol);
        usuarioDB.put(dni, usuario);
    }

    public void modificarUsuario(
            String nombreUsuario,
            String email,
            String contrasena,
            String nombre,
            String domicilio,
            String dni,
            Date fechaNacimiento,
            Rol rol
    ) {
        Usuario usuario = usuarioDB.get(dni);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario.setNombre(nombre);
        usuario.setDomicilio(domicilio);
        usuario.setDni(dni);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setRol(rol);

        usuarioDB.put(dni, usuario);
    }

    public void bajaUsuario(String dni) {
        usuarioDB.remove(dni);
    }
}
