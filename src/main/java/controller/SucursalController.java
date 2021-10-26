package controller;

import model.Sucursal;
import model.Usuario;
import model.dto.PeticionDTO;
import model.dto.SucursalDTO;

import java.util.HashMap;
import java.util.Map;

public class SucursalController {

    public static Map<Long, Sucursal> sucursalDB = new HashMap<>();

    public void altaSucursal(
            long numero,
            String direccion,
            Usuario responsableTecnico
    ) {
        Sucursal sucursal = new Sucursal(numero, direccion, responsableTecnico);
        sucursalDB.put(numero, sucursal);
    }

    public void modificarSucursal(
            long numero,
            String direccion,
            Usuario responsableTecnico
    ) {
        Sucursal sucursal = sucursalDB.get(numero);
        sucursal.setNumero(numero);
        sucursal.setDireccion(direccion);
        sucursal.setResponsableTecnico(responsableTecnico);

        sucursalDB.put(numero, sucursal);
    }

    public void bajaSucursal(long numero) {
        sucursalDB.remove(numero);
    }

    public SucursalDTO buscarSucursal(long numero) {
        Sucursal sucursal = sucursalDB.get(numero);
        return SucursalDTO.fromEntity(sucursal);
    }
}
