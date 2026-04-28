package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Partido implements Serializable {

    private int id;
    private String equipoLocal;
    private String equipoVisitante;

    private ArrayList<String> eventos;
    private ArrayList<String> chat;

    public Partido(int id, String local, String visitante) {
        this.id = id;
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        eventos = new ArrayList<>();
        chat = new ArrayList<>();
    }

    public void agregarEvento(String evento) {
        eventos.add(evento);
    }

    public void agregarMensaje(String mensaje) {
        chat.add(mensaje);
    }

    public String getInfo() {
        return equipoLocal + " vs " + equipoVisitante;
    }
}