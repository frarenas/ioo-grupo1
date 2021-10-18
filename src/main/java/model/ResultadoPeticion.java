package model;

public class ResultadoPeticion {
    private Long resultado;

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
