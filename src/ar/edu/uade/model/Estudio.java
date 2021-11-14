package ar.edu.uade.model;

import ar.edu.uade.model.dto.EstudioDTO;

public class Estudio {
    private Integer codigo;
    private Practica practica;
    private ResultadoPeticion resultadoPeticion;

    public Estudio() {}

    public Estudio(EstudioDTO estudioDTO) {
        this.codigo = estudioDTO.getCodigo();
        this.practica = new Practica(estudioDTO.getPractica());
        this.resultadoPeticion = new ResultadoPeticion(estudioDTO.getResultadoPeticion());
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public ResultadoPeticion getResultadoPeticion() {
        return resultadoPeticion;
    }

    public void setResultadoPeticion(ResultadoPeticion resultadoPeticion) {
        this.resultadoPeticion = resultadoPeticion;
    }

    public Boolean esCritico(){
        if(resultadoPeticion != null && practica != null) {
            return resultadoPeticion.getResultado() > practica.getValorCriticoMax()
                    || resultadoPeticion.getResultado() < practica.getValorCriticoMin();
        }
        return false;
    }

    public void modificarResultado(Double valor){
        if(resultadoPeticion != null)
            resultadoPeticion.setResultado(valor);
    }
}
