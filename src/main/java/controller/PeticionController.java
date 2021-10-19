package controller;

import model.Estudio;
import model.Paciente;
import model.Peticion;
import model.Sucursal;

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

    public List<Peticion> buscarPeticionesDelPaciente(
            String dni
    ) {
        //TODO: implementar
        return null;
    }

    public List<Peticion> buscarPeticionesPorSucursal(
            Integer numeroSucursal
    ) {
        //TODO: implementar
        return null;
    }

    public void derivarPeticiones(
            List<Peticion> peticiones
    ) {
        //TODO: implementar
    }

    public List<Peticion> obtenerPeticionesCriticas() {
        //TODO: implementar
        return null;
    }
}
