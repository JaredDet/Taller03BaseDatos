package servicio;

import datos.ConexionCliente;
import datos.ConexionPedido;
import modelo.Cliente;
import modelo.Pedido;
import vista.MuestraPedido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioPedido {


    public static void iniciar() {

        try {
            var conexion = new ConexionPedido();
            var resultados = conexion.leerTabla();
            leerResultados(resultados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void leerResultados(ResultSet resultados) throws SQLException {

        System.out.println("""
                      
                """
        );

        while (resultados.next()) {
            var pedido = crearPedido(resultados);
            MuestraPedido.mostrarResultados(pedido);
        }
        resultados.close();
    }

    private static Pedido crearPedido(ResultSet resultados) throws SQLException {

        var id = resultados.getInt("PEDIDO.id");
        var cliente = ServicioCliente.crearCliente(resultados);
        var fechaPedido = resultados.getDate("PEDIDO.fechaPedido");
        var costo = resultados.getInt("PEDIDO.costo");
        var nombreProducto = resultados.getString("PEDIDO.nombreProducto");

        return new Pedido(id, cliente, fechaPedido, costo, nombreProducto);
    }
}
