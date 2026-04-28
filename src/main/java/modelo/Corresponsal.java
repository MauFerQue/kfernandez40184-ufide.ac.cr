package modelo;

public class Corresponsal extends Usuario {

    public Corresponsal(int id, String nombre, String username, String password) {
        super(id, nombre, username, password, "CORRESPONSAL");
    }

    public void reportarEvento(String evento) {
        System.out.println("Evento reportado: " + evento);
    }
}