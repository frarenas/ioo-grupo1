package model.dto;

import model.Sucursal;

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
}
