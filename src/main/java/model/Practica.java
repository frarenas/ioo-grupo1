package model;

import java.util.List;

public class Practica {
    private Long codigo;
    private String nombre;
    private GrupoPractica grupo;
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

    public GrupoPractica getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoPractica grupo) {
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

    public void cambiarGrupo(GrupoPractica grupoPractica){
        //TODO
    }

    public void actualizarDatos(
            Long codigo,
            String nombre,
            GrupoPractica grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa
    ){
        //TODO
    }
}
