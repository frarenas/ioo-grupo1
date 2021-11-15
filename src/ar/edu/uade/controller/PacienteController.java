package ar.edu.uade.controller;

import ar.edu.uade.model.Paciente;
import ar.edu.uade.model.ResultadoOperacion;
import ar.edu.uade.model.Sexo;
import ar.edu.uade.model.dto.EstudioDTO;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.model.dto.PeticionDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteController {

    public static Map<String, Paciente> pacienteDB = new HashMap<>();
    private static PacienteController instance;

    private PacienteController() {
    }

    public static PacienteController getInstance() {
        if (instance == null) {
            instance = new PacienteController();
        }
        return instance;
    }

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

    public void altaPaciente(
            PacienteDTO paciente
    ) {
        altaPaciente(
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getDomicilio(),
                paciente.getEmail(),
                paciente.getSexo(),
                paciente.getEdad()
        );
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

    public void modificarPaciente(
            PacienteDTO paciente
    ) {
        modificarPaciente(
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getDomicilio(),
                paciente.getEmail(),
                paciente.getSexo(),
                paciente.getEdad()
        );
    }

    public ResultadoOperacion bajaPaciente(
            String dni
    ) {
        PeticionController peticionController = PeticionController.getInstance();
        boolean peticionesFinalizadas = false;

        for (PeticionDTO peticionDTO: peticionController.buscarPeticionesDelPaciente(dni)) {

            for (EstudioDTO estudioDTO: peticionDTO.getEstudios()) {
                if (estudioDTO.getResultadoPeticion() != null){
                    peticionesFinalizadas = true;
                    break;
                }
            }
            if (peticionesFinalizadas)
                break;
        }
        if (!peticionesFinalizadas){
            pacienteDB.remove(dni);
            return new ResultadoOperacion(true, "Se elimino el paciente correctamente");
        }else{
            return new ResultadoOperacion(false, "No se puede eliminar el paciente porque tiene peticiones finalizadas");
        }
    }

    public PacienteDTO buscarPaciente(
            String dni
    ) {
        Paciente paciente = pacienteDB.get(dni);
        return PacienteDTO.fromEntity(paciente);
    }

    public List<PacienteDTO> listarPacientes() {
        return PacienteDTO.fromEntities(new ArrayList<>(pacienteDB.values()));
    }
}
