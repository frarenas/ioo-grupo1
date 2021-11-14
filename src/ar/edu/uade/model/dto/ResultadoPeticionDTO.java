package ar.edu.uade.model.dto;

import ar.edu.uade.model.ResultadoPeticion;

public class ResultadoPeticionDTO {
    private Double resultado;

    public ResultadoPeticionDTO(Double resultado) {
        this.resultado = resultado;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public static ResultadoPeticionDTO fromEntity(ResultadoPeticion resultadoPeticion) {
        return new ResultadoPeticionDTO(
                resultadoPeticion.getResultado()
        );
    }
}
