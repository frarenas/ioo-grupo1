package ar.edu.uade.model.dto;

import ar.edu.uade.model.Paciente;
import ar.edu.uade.model.Sexo;

import java.util.List;
import java.util.stream.Collectors;

public class PacienteDTO {
    private String dni;
    private String nombre;
    private String domicilio;
    private String email;
    private Sexo sexo;
    private Integer edad;

    public PacienteDTO(String dni, String nombre, String domicilio, String email, Sexo sexo, Integer edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.email = email;
        this.sexo = sexo;
        this.edad = edad;
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

    public static PacienteDTO fromEntity(Paciente paciente) {
        return new PacienteDTO(
                paciente.getDni(),
                paciente.getNombre(),
                paciente.getDomicilio(),
                paciente.getEmail(),
                paciente.getSexo(),
                paciente.getEdad()
        );
    }

    public static List<PacienteDTO> fromEntities(List<Paciente> pacientes) {
        return pacientes.stream().map(PacienteDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return  nombre + " - DNI: " + dni;
    }
}
