package modelo;

import java.util.ArrayList;

public class Fanatico extends Usuario {

    private ArrayList<String> equiposFavoritos;

    public Fanatico(int id, String nombre, String username, String password) {
        super(id, nombre, username, password, "FAN");
        equiposFavoritos = new ArrayList<>();
    }

    public void agregarFavorito(String equipo) {
        equiposFavoritos.add(equipo);
    }

    public ArrayList<String> getEquiposFavoritos() {
        return equiposFavoritos;
    }
}