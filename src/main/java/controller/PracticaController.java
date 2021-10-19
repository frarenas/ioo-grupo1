package controller;

import model.GrupoPractica;
import model.Practica;

public class PracticaController {
    public Practica altaPractica(
            long codigo,
            String nombre,
            GrupoPractica grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa
    ) {
        //TODO: implementar
        return null;
    }

    public void modificacionPractica(
            long codigo,
            String nombre,
            GrupoPractica grupo,
            Long valorCriticoMin,
            Long valorCriticoMax,
            Boolean valorReservado,
            Integer cantHorasResultado,
            Boolean activa
    ) {
        //TODO: implementar
    }

    public void bajaPractica(long codigo) {
        //TODO: implementar
    }

    public void buscarPractica(long codigo) {
        //TODO: implementar
    }
}
