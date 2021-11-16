package ar.edu.uade.controller;

import ar.edu.uade.model.ResultadoOperacion;
import ar.edu.uade.model.Sucursal;
import ar.edu.uade.model.Usuario;
import ar.edu.uade.model.dto.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SucursalController {

    public static Map<Long, Sucursal> sucursalDB = new HashMap<>();
    private static SucursalController instance;

    private SucursalController() {
    }

    public static SucursalController getInstance() {
        if (instance == null) {
            instance = new SucursalController();
        }
        return instance;
    }

    public void altaSucursal(
            long numero,
            String direccion,
            UsuarioDTO responsableTecnico
    ) {
        Sucursal sucursal = new Sucursal(numero, direccion, new Usuario(responsableTecnico));
        sucursalDB.put(numero, sucursal);
    }

    public void altaSucursal(
            SucursalDTO sucursalDTO
    ) {
        altaSucursal(sucursalDTO.getNumero(), sucursalDTO.getDireccion(), sucursalDTO.getResponsableTecnico());
    }

    public void modificarSucursal(
            long numero,
            String direccion,
            UsuarioDTO responsableTecnico
    ) {
        Sucursal sucursal = sucursalDB.get(numero);
        sucursal.setNumero(numero);
        sucursal.setDireccion(direccion);
        sucursal.setResponsableTecnico(new Usuario(responsableTecnico));

        sucursalDB.put(numero, sucursal);
    }

    public void modificarSucursal(
            SucursalDTO sucursalDTO
    ) {
        modificarSucursal(sucursalDTO.getNumero(), sucursalDTO.getDireccion(), sucursalDTO.getResponsableTecnico());
    }

    public ResultadoOperacion bajaSucursal(long numero){
        PeticionController peticionController = PeticionController.getInstance();
        List<PeticionDTO> peticiones = peticionController.buscarPeticionesPorSucursal(numero);

        if(peticiones.size() == 0){
            sucursalDB.remove(numero);
            return new ResultadoOperacion(true, "Se elimino la sucursal correctamente");
        }else {
            List<PeticionDTO> peticionesFinalizadas = peticiones.stream()
                    .filter(pet -> pet.getEstudios() != null && pet.getEstudios().stream().anyMatch(est -> est.getResultadoPeticion().getResultado() != null))
                    .collect(Collectors.toList());
            if (peticionesFinalizadas.size() > 0) {
                return new ResultadoOperacion(false, "No se puede eliminar la sucursal porque tiene peticiones finalizadas");
            }else{
                return new ResultadoOperacion(false, "DERIVAR");
            }
        }
    }

    public void bajaSucursal(long numero, SucursalDTO sucursalDestino) {
        PeticionController peticionController = PeticionController.getInstance();
        List<PeticionDTO> peticionDTOS = peticionController.buscarPeticionesPorSucursal(numero);

        peticionController.derivarPeticiones(peticionDTOS, sucursalDestino);

        sucursalDB.remove(numero);
    }

    //TODO: Esto no está en el diagrama de clases. ¿Hay que incluirlo?
    public List<SucursalDTO> listarSucursales() {
        return SucursalDTO.fromEntities(new ArrayList<>(SucursalController.sucursalDB.values()));
    }

    public SucursalDTO buscarSucursal(long numero) {
        Sucursal sucursal = sucursalDB.get(numero);
        return SucursalDTO.fromEntity(sucursal);
    }

    public List<SucursalDTO> getSucursales() {
        return SucursalDTO.fromEntities(new ArrayList<>(sucursalDB.values()));
    }
}
