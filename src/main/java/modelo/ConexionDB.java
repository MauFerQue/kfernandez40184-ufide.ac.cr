package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    public static Connection get() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fidespn?useSSL=false&serverTimezone=UTC",
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}