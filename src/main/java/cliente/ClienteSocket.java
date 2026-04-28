package cliente;

import java.io.*;
import java.net.*;
import java.util.function.Consumer;

public class ClienteSocket {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    public ClienteSocket() throws IOException {
        socket = new Socket("localhost", 5000);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }

    public String leer() throws IOException {
        return entrada.readLine();
    }

    public void escuchar(Consumer<String> callback) {
        new Thread(() -> {
            try {
                String msg;
                while ((msg = entrada.readLine()) != null) {
                    callback.accept(msg);
                }
            } catch (Exception e) {}
        }).start();
    }

    public PrintWriter getSalida() {
        return salida;
    }
}