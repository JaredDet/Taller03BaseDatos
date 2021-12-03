package servicio;

import datos.ConexionCliente;
import modelo.Cliente;
import vista.MuestraCliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioCliente {


    public static void iniciar() {

        try {
            var conexion = new ConexionCliente();
            var resultados = conexion.leerTabla();
            leerResultados(resultados);
            conexion.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void leerResultados(ResultSet resultados) throws SQLException {

        System.out.println("""
                      
                """
        );

        while (resultados.next()) {
            var cliente = crearCliente(resultados);
            MuestraCliente.mostrarResultados(cliente);
        }
        resultados.close();
    }

    public static Cliente crearCliente(ResultSet resultado) throws SQLException {

        var id = resultado.getInt("CLIENTE.id");
        var nombre = resultado.getString("CLIENTE.nombre");
        var apellido = resultado.getString("CLIENTE.apellido");
        var telefono = resultado.getString("CLIENTE.telefono");
        var correo = resultado.getString("CLIENTE.correo");

        return new Cliente(id, nombre, apellido, telefono, correo);
    }
}
