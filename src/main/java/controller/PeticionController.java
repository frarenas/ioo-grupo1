package controller;

import model.Estudio;
import model.Paciente;
import model.Sucursal;
import model.dto.PeticionDTO;

import java.util.Date;
import java.util.List;

public class PeticionController {

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
