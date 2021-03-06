package ar.edu.uade.model;

import ar.edu.uade.model.dto.ResultadoPeticionDTO;

public class ResultadoPeticion {
    private Double resultado;

    public ResultadoPeticion() {}

    public ResultadoPeticion(Double resultado) {
        this.resultado = resultado;
    }

    public ResultadoPeticion(ResultadoPeticionDTO resultadoPeticionDTO) {
        this.resultado = resultadoPeticionDTO != null ? resultadoPeticionDTO.getResultado() : null;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Boolean esFinalizado() {
        return resultado != null;
    }
}
