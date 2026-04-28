package modelo;

public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String username, String password) {
        super(id, nombre, username, password, "ADMIN");
    }

    public void crearPartido() {
        System.out.println("Administrador creó un partido");
    }
}
