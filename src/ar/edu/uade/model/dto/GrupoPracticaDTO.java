package ar.edu.uade.model.dto;

import ar.edu.uade.model.GrupoPractica;

import java.util.List;
import java.util.stream.Collectors;

public class GrupoPracticaDTO {
    private Long id;
    private String nombre;

    public GrupoPracticaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static GrupoPracticaDTO fromEntity(GrupoPractica grupoPractica) {
        return new GrupoPracticaDTO(grupoPractica.getId(), grupoPractica.getNombre());
    }

    public static List<GrupoPracticaDTO> fromEntities(List<GrupoPractica> gruposPractica) {
        return gruposPractica.stream().map(GrupoPracticaDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return nombre;
    }
}
