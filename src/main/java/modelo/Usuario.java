package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    protected int id;
    protected String nombre;
    protected String username;
    protected String password;
    protected String rol;

    public Usuario(int id, String nombre, String username, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public boolean login(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }

    public void logout() {
        System.out.println("Sesión cerrada");
    }

    public String getRol() {
        return rol;
    }

    public String getUsername() {
        return username;
    }
}