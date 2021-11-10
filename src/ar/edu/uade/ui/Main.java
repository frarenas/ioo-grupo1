package ar.edu.uade.ui;

import ar.edu.uade.controller.PacienteController;
import ar.edu.uade.controller.PracticaController;
import ar.edu.uade.controller.SucursalController;
import ar.edu.uade.controller.UsuarioController;
import ar.edu.uade.model.*;

import java.util.Date;

public class Main {
    public static  void  main (String[] args){
        init();
        new ar.edu.uade.ui.Menu();
    }

    private static void init() {
        Usuario usuario1 = new Usuario("Admin", "admin@example.com", "123456", "Usuario Uno", "Domicilio 1234", "11111111", new Date(121231331), Rol.ADMINISTRADOR);
        Usuario usuario2 = new Usuario("laborista", "fulanito@example.com", "123456", "Usuario Dos", "Domicilio 3456", "22222222", new Date(121231331), Rol.LABORISTA);
        Usuario usuario3 = new Usuario("recepcion", "fulanita@example.com", "123456", "Usuario Tres", "Domicilio 5678", "333333333", new Date(121231331), Rol.RECEPCION);

        UsuarioController.usuarioDB.put(usuario1.getDni(), usuario1);
        UsuarioController.usuarioDB.put(usuario2.getDni(), usuario2);
        UsuarioController.usuarioDB.put(usuario3.getDni(), usuario3);

        Sucursal sucursal1=new Sucursal(1L,"Direccion 1",usuario1);
        Sucursal sucursal2=new Sucursal(2L,"Direccion 2",usuario2);
        Sucursal sucursal3=new Sucursal(3L,"Direccion 3",usuario3);

        SucursalController.sucursalDB.put(sucursal1.getNumero(), sucursal1);
        SucursalController.sucursalDB.put(sucursal2.getNumero(), sucursal2);
        SucursalController.sucursalDB.put(sucursal3.getNumero(), sucursal3);

        Paciente paciente1 = new Paciente("44444444", "Paciente Uno", "Domicilio 1111", "paciente1@example.com", Sexo.MASCULINO, 11);
        Paciente paciente2 = new Paciente("55555555", "Paciente Dos", "Domicilio 2222", "paciente2@example.com", Sexo.FEMENINO, 22);
        Paciente paciente3 = new Paciente("66666666", "Paciente Tres", "Domicilio 33333", "paciente3@example.com", Sexo.OTRO, 33);

        PacienteController.pacienteDB.put(paciente1.getDni(), paciente1);
        PacienteController.pacienteDB.put(paciente2.getDni(), paciente2);
        PacienteController.pacienteDB.put(paciente3.getDni(), paciente3);

        GrupoPractica grupo1 = new GrupoPractica(1L, "Grupo 1");
        GrupoPractica grupo2 = new GrupoPractica(2L, "Grupo 2");
        GrupoPractica grupo3 = new GrupoPractica(3L, "Grupo 3");

        PracticaController.grupoPracticaDB.put(grupo1.getId(), grupo1);
        PracticaController.grupoPracticaDB.put(grupo2.getId(), grupo2);
        PracticaController.grupoPracticaDB.put(grupo3.getId(), grupo3);

        Practica practica1 = new Practica(1L,"Practica 1", grupo1,10L,100L,false,12,true);
        Practica practica2 = new Practica(2L,"Practica 2", grupo2,20L,200L,true,24,true);
        Practica practica3 = new Practica(3L,"Practica 3", grupo3,30L,300L,false,48,true);

        PracticaController.practicaDB.put(practica1.getCodigo(), practica1);
        PracticaController.practicaDB.put(practica2.getCodigo(), practica2);
        PracticaController.practicaDB.put(practica3.getCodigo(), practica3);
    }

}
