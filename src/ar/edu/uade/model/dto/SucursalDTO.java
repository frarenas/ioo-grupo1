package ar.edu.uade.model.dto;

import ar.edu.uade.model.Sucursal;

import java.util.List;
import java.util.stream.Collectors;

public class SucursalDTO {
    private Long numero;
    private String direccion;
    private UsuarioDTO responsableTecnico;

    public SucursalDTO(Long numero, String direccion, UsuarioDTO responsableTecnico) {
        this.numero = numero;
        this.direccion = direccion;
        this.responsableTecnico = responsableTecnico;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public UsuarioDTO getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(UsuarioDTO responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public static SucursalDTO fromEntity(Sucursal sucursal) {
        return new SucursalDTO(
                sucursal.getNumero(),
                sucursal.getDireccion(),
                UsuarioDTO.fromEntity(sucursal.getResponsableTecnico())
        );
    }

    public static List<SucursalDTO> fromEntities(List<Sucursal> sucursales) {
        return sucursales.stream().map(SucursalDTO::fromEntity).collect(Collectors.toList());
    }
}
