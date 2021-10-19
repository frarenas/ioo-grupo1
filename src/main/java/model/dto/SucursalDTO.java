package model.dto;

public class SucursalDTO {
    private Integer numero;
    private String direccion;
    private UsuarioDTO responsableTecnico;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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
}
