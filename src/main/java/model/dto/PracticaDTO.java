package model.dto;

import model.Paciente;
import model.Practica;

public class PracticaDTO {
    private Long codigo;
    private String nombre;
    private GrupoPracticaDTO grupo;
    private Long valorCriticoMin;
    private Long valorCriticoMax;
    private Boolean valorReservado;
    private Integer cantHorasResultado;
    private Boolean activa;

    public PracticaDTO(Long codigo, String nombre, GrupoPracticaDTO grupo, Long valorCriticoMin, Long valorCriticoMax, Boolean valorReservado, Integer cantHorasResultado, Boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.grupo = grupo;
        this.valorCriticoMin = valorCriticoMin;
        this.valorCriticoMax = valorCriticoMax;
        this.valorReservado = valorReservado;
        this.cantHorasResultado = cantHorasResultado;
        this.activa = activa;
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

    public static PracticaDTO fromEntity(Practica practica) {
        return new PracticaDTO(
                practica.getCodigo(),
                practica.getNombre(),
                GrupoPracticaDTO.fromEntity(practica.getGrupo()),
                practica.getValorCriticoMin(),
                practica.getValorCriticoMax(),
                practica.getValorReservado(),
                practica.getCantHorasResultado(),
                practica.getActiva()
        );
    }
}
