package ar.edu.uade.model.dto;

import ar.edu.uade.model.Peticion;

import java.util.Date;
import java.util.List;

public class PeticionDTO {
    private Long id;
    private PacienteDTO paciente;
    private String obraSocial;
    private Date fechaCarga;
    private List<EstudioDTO> estudios;
    private Date fechaEntrega;
    private SucursalDTO sucursal;

    public PeticionDTO(Long id, PacienteDTO paciente, String obraSocial, Date fechaCarga, List<EstudioDTO> estudios, Date fechaEntrega, SucursalDTO sucursal) {
        this.id = id;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.estudios = estudios;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public List<EstudioDTO> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudioDTO> estudios) {
        this.estudios = estudios;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

    public static PeticionDTO fromEntity(Peticion peticion) {
        return new PeticionDTO(
                peticion.getId(),
                PacienteDTO.fromEntity(peticion.getPaciente()),
                peticion.getObraSocial(),
                peticion.getFechaCarga(),
                EstudioDTO.fromEntities(peticion.getEstudios()),
                peticion.getFechaEntrega(),
                SucursalDTO.fromEntity(peticion.getSucursal())
        );
    }
}
