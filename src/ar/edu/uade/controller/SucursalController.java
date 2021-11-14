package ar.edu.uade.controller;

import ar.edu.uade.model.Sucursal;
import ar.edu.uade.model.Usuario;
import ar.edu.uade.model.dto.EstudioDTO;
import ar.edu.uade.model.dto.PeticionDTO;
import ar.edu.uade.model.dto.SucursalDTO;
import ar.edu.uade.model.dto.UsuarioDTO;

import java.util.Collection;
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

    public String bajaSucursal(long numero, SucursalDTO sucursalDestino) {
        PeticionController peticionController = PeticionController.getInstance();
        boolean peticionesFinalizadas = false;

        List<PeticionDTO> peticionDTOS = peticionController.buscarPeticionesPorSucursal(numero);

        for (PeticionDTO peticionDTO: peticionDTOS) {

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
            peticionController.derivarPeticiones(peticionDTOS, sucursalDestino);

            sucursalDB.remove(numero);
            return "Se elimino la sucursal correctamente";
        }else{
            return "No se puede eliminar la sucursal porque tiene peticiones finalizadas";
        }
    }

    public SucursalDTO buscarSucursal(long numero) {
        Sucursal sucursal = sucursalDB.get(numero);
        return SucursalDTO.fromEntity(sucursal);
    }

    public Collection<SucursalDTO> obtenerSucursales() {
        return sucursalDB.values().stream().map(SucursalDTO::fromEntity).collect(Collectors.toList());
    }
}
