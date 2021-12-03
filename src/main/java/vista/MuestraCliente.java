package vista;

import modelo.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MuestraCliente {



    public static void mostrarResultados(Cliente cliente) throws SQLException {

        System.out.println(cliente);
    }

}
