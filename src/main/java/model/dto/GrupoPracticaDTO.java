package model.dto;

import model.GrupoPractica;

public class GrupoPracticaDTO {
    private String nombre;

    public GrupoPracticaDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static GrupoPracticaDTO fromEntity(GrupoPractica grupoPractica) {
        return new GrupoPracticaDTO(grupoPractica.getNombre());
    }

}
