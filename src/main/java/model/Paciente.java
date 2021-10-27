package model;

import model.dto.PacienteDTO;

public class Paciente {
    private String dni;
    private String nombre;
    private String domicilio;
    private String email;
    private Sexo sexo;
    private Integer edad;

    public Paciente(String dni, String nombre, String domicilio, String email, Sexo sexo, Integer edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.email = email;
        this.sexo = sexo;
        this.edad = edad;
    }

    public Paciente(PacienteDTO pacienteDTO) {
        this.dni = pacienteDTO.getDni();
        this.nombre = pacienteDTO.getNombre();
        this.domicilio = pacienteDTO.getDomicilio();
        this.email = pacienteDTO.getEmail();
        this.sexo = pacienteDTO.getSexo();
        this.edad = pacienteDTO.getEdad();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void actualizarDatos(String dni, String nombre, String domicilio, String email, Sexo sexo, Integer edad){
        //TODO
    }
}
