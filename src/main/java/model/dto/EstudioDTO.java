package model.dto;

public class EstudioDTO {
    private Integer codigo;
    private PracticaDTO practica;
    private ResultadoPeticionDTO resultadoPeticion;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public PracticaDTO getPractica() {
        return practica;
    }

    public void setPractica(PracticaDTO practica) {
        this.practica = practica;
    }

    public ResultadoPeticionDTO getResultadoPeticion() {
        return resultadoPeticion;
    }

    public void setResultadoPeticion(ResultadoPeticionDTO resultadoPeticion) {
        this.resultadoPeticion = resultadoPeticion;
    }
}
