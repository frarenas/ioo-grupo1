package model.dto;

import model.Peticion;
import model.ResultadoPeticion;

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
