package controller;

import model.Estudio;
import model.Paciente;
import model.Peticion;
import model.Sucursal;
import model.dto.EstudioDTO;
import model.dto.PacienteDTO;
import model.dto.PeticionDTO;
import model.dto.SucursalDTO;

import java.util.*;
import java.util.stream.Collectors;

public class PeticionController {

    public static Map<Long, Peticion> peticionDB = new HashMap<>();

    public void altaPeticion(
            Long id,
            PacienteDTO paciente,
            String obraSocial,
            Date fechaCarga,
            List<EstudioDTO> estudioDtos,
            Date fechaEntrega,
            SucursalDTO sucursal
    ) {
        List<Estudio> estudios = estudioDtos.stream().map(Estudio::new).collect(Collectors.toList());
        Peticion peticion = new Peticion(id, new Paciente(paciente), obraSocial, fechaCarga, estudios, fechaEntrega, new Sucursal(sucursal));
        peticionDB.put(id, peticion);
    }

    public void modificarPeticion(
            Long id,
            PacienteDTO paciente,
            String obraSocial,
            Date fechaCarga,
            List<EstudioDTO> estudioDtos,
            Date fechaEntrega,
            SucursalDTO sucursal
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
            Long numeroSucursal
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
