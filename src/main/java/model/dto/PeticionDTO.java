package model.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PeticionDTO {
    private Long id;
    private PacienteDTO paciente;
    private String obraSocial;
    private Date fechaCarga;
    private List<EstudioDTO> estudios;
    private Date fechaEntrega;
    private SucursalDTO sucursal;

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
}
