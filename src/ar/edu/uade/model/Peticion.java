package ar.edu.uade.model;

import ar.edu.uade.model.dto.PeticionDTO;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Peticion {
    private Long id;
    private Paciente paciente;
    private String obraSocial;
    private Date fechaCarga;
    private List<Estudio> estudios;
    private Date fechaEntrega;
    private Sucursal sucursal;

    public Peticion(Long id, Paciente paciente, String obraSocial, Date fechaCarga, List<Estudio> estudios, Date fechaEntrega, Sucursal sucursal) {
        this.id = id;
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.fechaCarga = fechaCarga;
        this.estudios = estudios;
        this.fechaEntrega = fechaEntrega;
        this.sucursal = sucursal;
    }

    public Peticion(PeticionDTO peticionDTO) {
        this.id = peticionDTO.getId();
        this.paciente = new Paciente(peticionDTO.getPaciente());
        this.obraSocial = peticionDTO.getObraSocial();
        this.fechaCarga = peticionDTO.getFechaCarga();
        this.estudios = peticionDTO.getEstudios().stream().map(Estudio::new).collect(Collectors.toList());
        this.fechaEntrega = peticionDTO.getFechaEntrega();
        this.sucursal = new Sucursal(peticionDTO.getSucursal());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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

    public List<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<Estudio> estudios) {
        this.estudios = estudios;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public void agregarEstudio(Estudio estudio){
        estudios.add(estudio);
    }

    public void eliminarEstudio(Integer codigo){
        Estudio estudioAEliminar = new Estudio();
        for (Estudio estudio: estudios) {
            if(Objects.equals(estudio.getCodigo(), codigo)){
                estudioAEliminar = estudio;
                break;
            }
        }
        estudios.remove(estudioAEliminar);
    }

    public Boolean esFinalizado(){
        for (Estudio estudio: estudios) {
            if(estudio.getResultadoPeticion().esFinalizado())
                return true;
        }
        return false;
    }
}
