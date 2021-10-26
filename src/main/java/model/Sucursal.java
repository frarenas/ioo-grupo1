package model;

public class Sucursal {
    private Long numero;
    private String direccion;
    private Usuario responsableTecnico;

    public Sucursal(Long numero, String direccion, Usuario responsableTecnico) {
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

    public Usuario getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(Usuario responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }

    public void actualizarDatos(
            Long numero,
            String direccion,
            Usuario responsableTecnico
    ){
        //TODO
    }
}
