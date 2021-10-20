package model.dto;

import model.Estudio;

import java.util.ArrayList;
import java.util.List;

public class EstudioDTO {
    private Integer codigo;
    private PracticaDTO practica;
    private ResultadoPeticionDTO resultadoPeticion;

    public EstudioDTO(Integer codigo, PracticaDTO practica, ResultadoPeticionDTO resultadoPeticion) {
        this.codigo = codigo;
        this.practica = practica;
        this.resultadoPeticion = resultadoPeticion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public PracticaDTO getPractica() {
        return practica;
    }

    public void setPractica(PracticaDTO practica) {
        this.practica = practica;
    }

    public ResultadoPeticionDTO getResultadoPeticion() {
        return resultadoPeticion;
    }

    public void setResultadoPeticion(ResultadoPeticionDTO resultadoPeticion) {
        this.resultadoPeticion = resultadoPeticion;
    }

    public static EstudioDTO fromEntity(Estudio estudio) {
        return new EstudioDTO(
                estudio.getCodigo(),
                PracticaDTO.fromEntity(estudio.getPractica()),
                ResultadoPeticionDTO.fromEntity(estudio.getResultadoPeticion())
        );
    }

    public static List<EstudioDTO> fromEntities(List<Estudio> estudios) {
        List<EstudioDTO> estudioDTOs = new ArrayList<>();
        for (Estudio estudio: estudios) {
            estudioDTOs.add(fromEntity(estudio));
        }

        return estudioDTOs;
    }
}
