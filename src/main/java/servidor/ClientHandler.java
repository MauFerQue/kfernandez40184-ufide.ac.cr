package servidor;

import java.io.*;
import java.net.*;
import java.sql.*;

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            Servidor.clientes.add(salida);

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {

                System.out.println("Mensaje: " + mensaje);

                if (mensaje.startsWith("LOGIN")) {
                    String[] p = mensaje.split("\\|");

                    Connection conn = modelo.ConexionDB.get();
                    PreparedStatement ps = conn.prepareStatement(
                            "SELECT rol FROM usuario WHERE username=? AND password=?"
                    );

                    ps.setString(1, p[1]);
                    ps.setString(2, p[2]);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        salida.println("OK|" + rs.getString("rol"));
                    } else {
                        salida.println("ERROR");
                    }
                }

                else if (mensaje.startsWith("CREAR_PARTIDO")) {
                    String[] p = mensaje.split("\\|");

                    Connection conn = modelo.ConexionDB.get();
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO partido(local,visitante) VALUES(?,?)"
                    );

                    ps.setString(1, p[1]);
                    ps.setString(2, p[2]);
                    ps.executeUpdate();

                    salida.println("OK");
                }

                else if (mensaje.startsWith("LISTAR_PARTIDOS")) {

                    Connection conn = modelo.ConexionDB.get();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM partido");

                    StringBuilder lista = new StringBuilder();

                    while (rs.next()) {
                        lista.append(rs.getInt("id"))
                             .append(" - ")
                             .append(rs.getString("local"))
                             .append(" vs ")
                             .append(rs.getString("visitante"))
                             .append(";");
                    }

                    salida.println("PARTIDOS|" + lista.toString());
                }

                else if (mensaje.startsWith("EVENTO")) {

                    String[] p = mensaje.split("\\|");

                    Connection conn = modelo.ConexionDB.get();
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO evento(partido_id,tipo,descripcion,minuto) VALUES(?,?,?,?)"
                    );

                    ps.setInt(1, Integer.parseInt(p[1]));
                    ps.setString(2, p[2]);
                    ps.setString(3, p[3]);
                    ps.setInt(4, Integer.parseInt(p[4]));
                    ps.executeUpdate();

                    salida.println("OK");

                    Servidor.broadcast("EVENTO|" + p[1] + "|" + p[2] + "|" + p[3] + "|" + p[4]);
                }

                else if (mensaje.startsWith("CHAT")) {

                    String[] p = mensaje.split("\\|");

                    Servidor.broadcast("CHAT|" + p[1] + "|" + p[2] + "|" + p[3]);
                    salida.println("OK");
                }
            }

        } catch (Exception e) {
            System.out.println("Cliente desconectado");
        }
    }
}