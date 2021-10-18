package model;

import java.util.List;

public class Estudio {
    private Integer codigo;
    private Practica practica;
    private ResultadoPeticion resultadoPeticion;

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

    public void modificarResultado(Long valor){
        if(resultadoPeticion != null)
            resultadoPeticion.setResultado(valor);
    }
}
