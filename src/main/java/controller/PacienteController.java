package controller;

import model.Estudio;
import model.Paciente;
import model.Sexo;
import model.dto.EstudioDTO;
import model.dto.PacienteDTO;
import model.dto.PeticionDTO;

import java.util.HashMap;
import java.util.List;
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

    public String bajaPaciente(
            String dni
    ) {
        PeticionController peticionController = new PeticionController();
        Boolean peticionesFinalizadas = false;

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
            return "Se elimino el paciente correctamente";
        }else{
            return "No se puede eliminar el paciente porque tiene peticiones finalizadas";
        }



    }

    public PacienteDTO buscarPaciente(
            String dni
    ) {
        Paciente paciente = pacienteDB.get(dni);
        return PacienteDTO.fromEntity(paciente);
    }
}
