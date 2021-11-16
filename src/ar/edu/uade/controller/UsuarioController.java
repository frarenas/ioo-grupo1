package ar.edu.uade.controller;

import ar.edu.uade.model.ResultadoOperacion;
import ar.edu.uade.model.Rol;
import ar.edu.uade.model.Usuario;
import ar.edu.uade.model.dto.UsuarioDTO;

import java.util.*;

public class UsuarioController {

    public static Map<String, Usuario> usuarioDB = new HashMap<>();
    public static Usuario usuarioLogueado;
    private static UsuarioController instance;

    private UsuarioController() {
    }

    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }

    public UsuarioDTO altaUsuario(
            String nombreUsuario,
            String email,
            String contrasena,
            String nombre,
            String domicilio,
            String dni,
            Date fechaNacimiento,
            Rol rol) {
        Usuario usuario = new Usuario(nombreUsuario, email, contrasena, nombre, domicilio, dni, fechaNacimiento, rol);
        usuarioDB.put(dni, usuario);
        return UsuarioDTO.fromEntity(usuario);
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

    //TODO: Esto no está en el diagrama de clases. ¿Hay que incluirlo?
    public List<UsuarioDTO> listarUsuarios() {
        return UsuarioDTO.fromEntities(new ArrayList<>(UsuarioController.usuarioDB.values()));
    }

    public UsuarioDTO buscarUsuario(String dni) {
        Usuario usuario = usuarioDB.get(dni);
        return UsuarioDTO.fromEntity(usuario);
    }

    public ResultadoOperacion login(String usuario, String contrasena) {
        usuarioLogueado = usuarioDB.values().stream()
                .filter(x -> Objects.equals(x.getNombreUsuario(), usuario) && Objects.equals(x.getContrasena(), contrasena))
                .findFirst().orElse(null);

        if (usuarioLogueado != null) {
            return new ResultadoOperacion(true, null);
        } else {
            return new ResultadoOperacion(false, "Usuario o contraseña incorrecta.");
        }
    }

    public void logout() {
        usuarioLogueado = null;
    }
}
