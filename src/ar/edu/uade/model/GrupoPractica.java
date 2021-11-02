package ar.edu.uade.model;

import ar.edu.uade.model.dto.GrupoPracticaDTO;

public class GrupoPractica {
    private String nombre;

    public GrupoPractica(String nombre) {
        this.nombre = nombre;
    }

    public GrupoPractica(GrupoPracticaDTO grupoPracticaDTO) {
        this.nombre = grupoPracticaDTO.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
