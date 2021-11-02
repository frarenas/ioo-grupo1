package ar.edu.uade.model.dto;

import ar.edu.uade.model.Peticion;
import ar.edu.uade.model.ResultadoPeticion;

public class ResultadoPeticionDTO {
    private Long resultado;

    public ResultadoPeticionDTO(Long resultado) {
        this.resultado = resultado;
    }

    public Long getResultado() {
        return resultado;
    }

    public void setResultado(Long resultado) {
        this.resultado = resultado;
    }

    public static ResultadoPeticionDTO fromEntity(ResultadoPeticion resultadoPeticion) {
        return new ResultadoPeticionDTO(
                resultadoPeticion.getResultado()
        );
    }
}
