package controller;

import model.GrupoPractica;
import model.Paciente;
import model.Practica;

import java.util.HashMap;
import java.util.Map;

public class PracticaController {

    public static Map<Long, Practica> practicaDB = new HashMap<>();

    public Practica altaPractica(
            Long codigo,
            String nombre,
            GrupoPractica grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa) {

        Practica practica = new Practica(codigo,nombre,grupo,valorCriticoMin,valorCriticoMax,valorReservado,cantHorasResultado,activa);
        practicaDB.put(codigo, practica);
        return practica;
    }

    public void modificacionPractica(
            Long codigo,
            String nombre,
            GrupoPractica grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa
    ) {

        Practica practica = practicaDB.get(codigo);
        practica.setNombre(nombre);
        practica.setGrupo(grupo);
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