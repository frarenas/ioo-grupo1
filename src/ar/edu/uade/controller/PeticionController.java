package ar.edu.uade.controller;

import ar.edu.uade.model.Estudio;
import ar.edu.uade.model.Paciente;
import ar.edu.uade.model.Peticion;
import ar.edu.uade.model.Sucursal;
import ar.edu.uade.model.dto.*;

import java.util.*;
import java.util.stream.Collectors;

public class PeticionController {

    public static Map<Long, Peticion> peticionDB = new HashMap<>();
    private static PeticionController instance;

    private PeticionController() {
    }

    public static PeticionController getInstance() {
        if (instance == null) {
            instance = new PeticionController();
        }
        return instance;
    }

    public PeticionDTO altaPeticion(
            Long id,
            PacienteDTO paciente,
            String obraSocial,
            Date fechaCarga,
            Date fechaEntrega,
            SucursalDTO sucursal
    ) {
        Peticion peticion = new Peticion(id, new Paciente(paciente), obraSocial, fechaCarga, null, fechaEntrega, new Sucursal(sucursal));
        peticionDB.put(id, peticion);
        return PeticionDTO.fromEntity(peticion);
    }

    public PeticionDTO altaPeticion(
            PeticionDTO peticionDTO
    ) {
        return altaPeticion(
                peticionDTO.getId(),
                peticionDTO.getPaciente(),
                peticionDTO.getObraSocial(),
                peticionDTO.getFechaCarga(),
                peticionDTO.getFechaEntrega(),
                peticionDTO.getSucursal()
        );
    }

    public void modificarPeticion(
            Long id,
            PacienteDTO paciente,
            String obraSocial,
            Date fechaCarga,
            Date fechaEntrega,
            SucursalDTO sucursal,
            List<EstudioDTO> estudioDtos
    ) {
        List<Estudio> estudios = estudioDtos.stream().map(Estudio::new).collect(Collectors.toList());

        Peticion peticion = peticionDB.get(id);
        peticion.setPaciente(new Paciente(paciente));
        peticion.setObraSocial(obraSocial);
        peticion.setFechaCarga(fechaCarga);
        peticion.setEstudios(estudios);
        peticion.setFechaEntrega(fechaEntrega);
        peticion.setSucursal(new Sucursal(sucursal));
        peticionDB.put(id, peticion);
    }

    public void modificarPeticion(
            PeticionDTO peticionDTO
    ) {
        modificarPeticion(
                peticionDTO.getId(),
                peticionDTO.getPaciente(),
                peticionDTO.getObraSocial(),
                peticionDTO.getFechaCarga(),
                peticionDTO.getFechaEntrega(),
                peticionDTO.getSucursal(),
                peticionDTO.getEstudios()
        );
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
            if (peticion.getPaciente().getDni().equals(dni)) {
                peticionesDni.add(PeticionDTO.fromEntity(peticion));
            }
        }

        return peticionesDni;
    }

    public List<PeticionDTO> buscarPeticionesPorSucursal(
            Long numeroSucursal
    ) {
        Collection<Peticion> peticiones = peticionDB.values();
        List<PeticionDTO> peticionesSucursal = new ArrayList<>();
        for (Peticion peticion : peticiones) {
            if (peticion.getSucursal().getNumero().intValue() == numeroSucursal) {
                peticionesSucursal.add(PeticionDTO.fromEntity(peticion));
            }
        }
        return peticionesSucursal;
    }

    public void derivarPeticiones(
            List<PeticionDTO> peticiones,
            SucursalDTO sucursal
    ) {
        for (PeticionDTO peticionDTO : peticiones) {
            Peticion peticion = peticionDB.get(peticionDTO.getId());
            peticion.setSucursal(new Sucursal(sucursal));
            peticionDB.put(peticionDTO.getId(), peticion);
        }

    }

    public List<PeticionDTO> obtenerPeticionesCriticas() {
        List<PeticionDTO> peticionesCriticas = new ArrayList<>();

        for (Peticion peticion : peticionDB.values()) {

            List<Estudio> estudios = peticion.getEstudios();
            if (estudios != null) {
                for (Estudio estudio : estudios) {

                    if (estudio.esCritico()) {
                        peticionesCriticas.add(PeticionDTO.fromEntity(peticion));
                        break;
                    }
                }
            }
        }
        return peticionesCriticas;
    }

    public List<PeticionDTO> getPeticiones() {
        return PeticionDTO.fromEntities(new ArrayList<>(peticionDB.values()));
    }
}
