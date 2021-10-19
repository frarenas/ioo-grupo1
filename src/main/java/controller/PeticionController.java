package controller;

import model.Estudio;
import model.Paciente;
import model.Peticion;
import model.Sucursal;
import model.dto.PeticionDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeticionController {

    public static Map<Long, Peticion> peticionDB = new HashMap<>();

    public void altaPeticion(
            Paciente paciente,
            String obraSocial,
            Date fechaCarga,
            List<Estudio> estudios,
            Date fechaEntrega,
            Sucursal sucursal
    ) {
        //TODO: implementar
    }

    public void modificarPeticion(
            Long id,
            Paciente paciente,
            String obraSocial,
            Date fechaCarga,
            List<Estudio> estudios,
            Date fechaEntrega,
            Sucursal sucursal
    ) {
        //TODO: implementar
    }

    public void bajaPeticion(
            Long id
    ) {
        //TODO: implementar
    }

    public List<PeticionDTO> buscarPeticionesDelPaciente(
            String dni
    ) {
        //TODO: implementar
        return null;
    }

    public List<PeticionDTO> buscarPeticionesPorSucursal(
            Integer numeroSucursal
    ) {
        //TODO: implementar
        return null;
    }

    public void derivarPeticiones(
            List<PeticionDTO> peticiones
    ) {
        //TODO: implementar
    }

    public List<PeticionDTO> obtenerPeticionesCriticas() {
        //TODO: implementar
        return null;
    }
}
