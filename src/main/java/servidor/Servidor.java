package servidor;

import java.net.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Servidor {

    public static ArrayList<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Servidor iniciado...");

            while (true) {
                Socket cliente = server.accept();
                new Thread(new ClientHandler(cliente)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String msg) {
        for (PrintWriter c : clientes) {
            c.println(msg);
        }
    }
}