package ar.edu.uade.controller;

import ar.edu.uade.model.GrupoPractica;
import ar.edu.uade.model.Practica;
import ar.edu.uade.model.dto.GrupoPracticaDTO;

import java.util.HashMap;
import java.util.Map;

public class PracticaController {

    public static Map<Long, Practica> practicaDB = new HashMap<>();
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

    public void bajaPractica(Long codigo) {
        practicaDB.remove(codigo);
    }

    public void buscarPractica(Long codigo) {
       practicaDB.get(codigo);
    }
}