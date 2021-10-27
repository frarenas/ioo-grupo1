package model;

import model.dto.PracticaDTO;

public class Practica {
    private Long codigo;
    private String nombre;
    private GrupoPractica grupo;
    private Long valorCriticoMin;
    private Long valorCriticoMax;
    private Boolean valorReservado;
    private Integer cantHorasResultado;
    private Boolean activa;

    public Practica(Long codigo, String nombre, GrupoPractica grupo, Long valorCriticoMin, Long valorCriticoMax, Boolean valorReservado, Integer cantHorasResultado, Boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valorCriticoMin = valorCriticoMin;
        this.valorCriticoMax = valorCriticoMax;
        this.valorReservado = valorReservado;
        this.cantHorasResultado = cantHorasResultado;
        this.activa = activa;
    }

    public Practica(PracticaDTO practicaDTO) {
        this.codigo = practicaDTO.getCodigo();
        this.nombre = practicaDTO.getNombre();
        this.grupo = new GrupoPractica(practicaDTO.getGrupo());
        this.valorCriticoMin = practicaDTO.getValorCriticoMin();
        this.valorCriticoMax = practicaDTO.getValorCriticoMax();
        this.valorReservado = practicaDTO.getValorReservado();
        this.cantHorasResultado = practicaDTO.getCantHorasResultado();
        this.activa = practicaDTO.getActiva();
    }

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

}
