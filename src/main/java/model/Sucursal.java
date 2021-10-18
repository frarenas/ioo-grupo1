package model;

public class Sucursal {
    private Integer numero;
    private String direccion;
    private Usuario responsableTecnico;

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

    public Usuario getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(Usuario responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public void actualizarDatos(
            Integer numero,
            String direccion,
            Usuario responsableTecnico
    ){
        //TODO
    }
}
