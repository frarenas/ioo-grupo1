package ar.edu.uade.controller;

import ar.edu.uade.model.GrupoPractica;
import ar.edu.uade.model.Practica;
import ar.edu.uade.model.dto.GrupoPracticaDTO;
import ar.edu.uade.model.dto.PacienteDTO;
import ar.edu.uade.model.dto.PracticaDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticaController {

    public static Map<Long, Practica> practicaDB = new HashMap<>();
    public static Map<Long, GrupoPractica> grupoPracticaDB = new HashMap<>();
    private static PracticaController instance;

    private PracticaController() {
    }

    public static PracticaController getInstance() {
        if (instance == null) {
            instance = new PracticaController();
        }
        return instance;
    }

    public Practica altaPractica(
            Long codigo,
            String nombre,
            GrupoPracticaDTO grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa) {

        Practica practica = new Practica(codigo,nombre, new GrupoPractica(grupo),valorCriticoMin,valorCriticoMax,valorReservado,cantHorasResultado,activa);
        practicaDB.put(codigo, practica);
        return practica;
    }

    public Practica altaPractica(PracticaDTO practicaDTO) {
        return altaPractica(
                practicaDTO.getCodigo(),
                practicaDTO.getNombre(),
                practicaDTO.getGrupo(),
                practicaDTO.getValorCriticoMin(),
                practicaDTO.getValorCriticoMax(),
                practicaDTO.getValorReservado(),
                practicaDTO.getCantHorasResultado(),
                practicaDTO.getActiva()
        );
    }

    public void modificacionPractica(
            Long codigo,
            String nombre,
            GrupoPracticaDTO grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa
    ) {

        Practica practica = practicaDB.get(codigo);
        practica.setNombre(nombre);
        practica.setGrupo(new GrupoPractica(grupo));
        practica.setValorCriticoMin(valorCriticoMin);
        practica.setValorCriticoMax(valorCriticoMax);
        practica.setValorReservado(valorReservado);
        practica.setCantHorasResultado(cantHorasResultado);
        practica.setActiva(activa);
        practicaDB.put(codigo, practica);
    }

    public void modificacionPractica(PracticaDTO practicaDTO) {
        modificacionPractica(
                practicaDTO.getCodigo(),
                practicaDTO.getNombre(),
                practicaDTO.getGrupo(),
                practicaDTO.getValorCriticoMin(),
                practicaDTO.getValorCriticoMax(),
                practicaDTO.getValorReservado(),
                practicaDTO.getCantHorasResultado(),
                practicaDTO.getActiva()
        );
    }

    public void bajaPractica(Long codigo) {
        practicaDB.remove(codigo);
    }

    public void buscarPractica(Long codigo) {
       practicaDB.get(codigo);
    }

    public List<GrupoPracticaDTO> getGruposPractica() {
        return GrupoPracticaDTO.fromEntities(new ArrayList<>(grupoPracticaDB.values()));
    }

    public List<PracticaDTO> getPracticas() {
        return PracticaDTO.fromEntities(new ArrayList<>(practicaDB.values()));
    }
}