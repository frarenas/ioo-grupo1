package controller;

import model.Paciente;
import model.Sexo;
import model.dto.PacienteDTO;

import java.util.HashMap;
import java.util.Map;

public class PacienteController {

    public static Map<String, Paciente> pacienteDB = new HashMap<>();

    public void altaPaciente(
            String dni,
            String nombre,
            String domicilio,
            String email,
            Sexo sexo,
            Integer edad
    ) {
        Paciente paciente = new Paciente(dni, nombre, domicilio, email, sexo, edad);
        pacienteDB.put(dni, paciente);
    }

    public void modificarPaciente(
            String dni,
            String nombre,
            String domicilio,
            String email,
            Sexo sexo,
            Integer edad
    ) {
        Paciente paciente = pacienteDB.get(dni);
        paciente.setDni(dni);
        paciente.setNombre(nombre);
        paciente.setDomicilio(domicilio);
        paciente.setEmail(email);
        paciente.setSexo(sexo);
        paciente.setEdad(edad);

        pacienteDB.put(dni, paciente);
    }

    public void bajaPaciente(
            String dni
    ) {
        pacienteDB.remove(dni);
    }

    public PacienteDTO buscarPaciente(
            String dni
    ) {
        Paciente paciente = pacienteDB.get(dni);
        return null;
    }
}
