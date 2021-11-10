package ar.edu.uade.model;

import ar.edu.uade.model.dto.GrupoPracticaDTO;

public class GrupoPractica {
    private Long id;
    private String nombre;

    public GrupoPractica(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public GrupoPractica(GrupoPracticaDTO grupoPracticaDTO) {
        this.id = grupoPracticaDTO.getId();
        this.nombre = grupoPracticaDTO.getNombre();
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
}
