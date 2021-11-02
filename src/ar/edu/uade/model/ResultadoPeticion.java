package ar.edu.uade.model;

import ar.edu.uade.model.dto.ResultadoPeticionDTO;

public class ResultadoPeticion {
    private Long resultado;

    public ResultadoPeticion(ResultadoPeticionDTO resultadoPeticionDTO) {
        this.resultado = resultadoPeticionDTO.getResultado();
    }

    public Long getResultado() {
        return resultado;
    }

    public void setResultado(Long resultado) {
        this.resultado = resultado;
    }

    public Boolean esFinalizado() {
        return resultado != null;
    }
}
