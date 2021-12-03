package vista;

import modelo.Cliente;
import modelo.Pedido;

import java.sql.SQLException;

public class MuestraPedido {

    public static void mostrarResultados(Pedido pedido) throws SQLException {

        System.out.println(pedido);
    }
}
