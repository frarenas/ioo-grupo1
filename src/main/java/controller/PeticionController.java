package controller;

import model.Estudio;
import model.Paciente;
import model.Peticion;
import model.Sucursal;
import model.dto.PeticionDTO;

import java.util.*;

public class PeticionController {

    public static Map<Long, Peticion> peticionDB = new HashMap<>();

    public void altaPeticion(
            Long id,
            Paciente paciente,
            String obraSocial,
            Date fechaCarga,
            List<Estudio> estudios,
            Date fechaEntrega,
            Sucursal sucursal
    ) {
        Peticion peticion = new Peticion(id, paciente, obraSocial, fechaCarga, estudios, fechaEntrega, sucursal);
        peticionDB.put(id, peticion);
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
        Peticion peticion = peticionDB.get(id);
        peticion.setPaciente(paciente);
        peticion.setObraSocial(obraSocial);
        peticion.setFechaCarga(fechaCarga);
        peticion.setEstudios(estudios);
        peticion.setFechaEntrega(fechaEntrega);
        peticion.setSucursal(sucursal);
        peticionDB.put(id, peticion);
    }

    public void bajaPeticion(
            Long id
    ) {
        peticionDB.remove(id);
    }

    public List<PeticionDTO> buscarPeticionesDelPaciente(
            String dni
    ) {
        Collection<Peticion> peticiones = peticionDB.values();
        List<PeticionDTO> peticionesDni = new ArrayList<>();
        for (Peticion peticion : peticiones) {
            if (peticion.getPaciente().getDni().equals(dni)){
                peticionesDni.add(PeticionDTO.fromEntity(peticion));
            }
        }

        return peticionesDni;
    }

    public List<PeticionDTO> buscarPeticionesPorSucursal(
            Integer numeroSucursal
    ) {
        Collection<Peticion> peticiones = peticionDB.values();
        List<PeticionDTO> peticionesSucursal = new ArrayList<>();
        for (Peticion peticion : peticiones) {
            if (peticion.getSucursal().getNumero().intValue() == numeroSucursal){
                peticionesSucursal.add(PeticionDTO.fromEntity(peticion));
            }
        }
        return peticionesSucursal;
    }

    public void derivarPeticiones(
            List<PeticionDTO> peticiones,
            Sucursal sucursal
    ) {
        for (PeticionDTO peticionDTO : peticiones) {
            Peticion peticion = peticionDB.get(peticionDTO.getId());
            peticion.setSucursal(sucursal);
            peticionDB.put(peticionDTO.getId(), peticion);
        }

    }

    public List<PeticionDTO> obtenerPeticionesCriticas() {
        List<PeticionDTO> peticionesCriticas = new ArrayList<>();

        for (Peticion peticion : peticionDB.values()) {

            for (Estudio estudio : peticion.getEstudios()) {

                if (estudio.esCritico()){
                    peticionesCriticas.add(PeticionDTO.fromEntity(peticion));
                    break;
                }
            }
        }
        return peticionesCriticas;
    }
}
