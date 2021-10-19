package model.dto;

public class PracticaDTO {
    private Long codigo;
    private String nombre;
    private GrupoPracticaDTO grupo;
    private Long valorCriticoMin;
    private Long valorCriticoMax;
    private Boolean valorReservado;
    private Integer cantHorasResultado;
    private Boolean activa;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GrupoPracticaDTO getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoPracticaDTO grupo) {
        this.grupo = grupo;
    }

    public Long getValorCriticoMin() {
        return valorCriticoMin;
    }

    public void setValorCriticoMin(Long valorCriticoMin) {
        this.valorCriticoMin = valorCriticoMin;
    }

    public Long getValorCriticoMax() {
        return valorCriticoMax;
    }

    public void setValorCriticoMax(Long valorCriticoMax) {
        this.valorCriticoMax = valorCriticoMax;
    }

    public Boolean getValorReservado() {
        return valorReservado;
    }

    public void setValorReservado(Boolean valorReservado) {
        this.valorReservado = valorReservado;
    }

    public Integer getCantHorasResultado() {
        return cantHorasResultado;
    }

    public void setCantHorasResultado(Integer cantHorasResultado) {
        this.cantHorasResultado = cantHorasResultado;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
