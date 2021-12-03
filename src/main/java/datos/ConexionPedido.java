package datos;

import java.sql.*;

public class ConexionPedido {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USER = "root";
    private static final String PASS = "3H5fV8MFRN";
    private String sql;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public ConexionPedido() throws SQLException {
        conectar();
    }

    private static void conectar() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public ResultSet leerTabla() throws SQLException {

        sql = "SELECT PEDIDO.id, PEDIDO.fechaPedido, PEDIDO.costo, PEDIDO.nombreProducto " +
                ", CLIENTE.id, CLIENTE.nombre, CLIENTE.apellido, CLIENTE.telefono, CLIENTE.correo " +
                "FROM PEDIDO " +
                "INNER JOIN CLIENTE ON(PEDIDO.idCliente = CLIENTE.id)";
        return statement.executeQuery(sql);
    }

    public void insertarDato(int id, int idCliente, Date fechaPedido, int costo, String nombreProducto) throws SQLException {

        sql = "INSERT INTO PEDIDO(id, idCliente, fechaPedido, costo, nombreProducto)" +
                " VALUES (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, idCliente);
        preparedStatement.setDate(3, fechaPedido);
        preparedStatement.setInt(4, costo);
        preparedStatement.setString(5, nombreProducto);

        preparedStatement.executeUpdate();
    }

    public ResultSet buscarRegistro(String telefonoCliente) throws SQLException {

        var idCliente = buscarCliente(telefonoCliente).getInt("id");

        sql = "SELECT id FROM PEDIDO" +
                " WHERE idCliente = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCliente);
        return preparedStatement.executeQuery();
    }

    private ResultSet buscarCliente(String telefonoCliente) throws SQLException {

        var datosCliente = new ConexionCliente();

        sql = "SELECT id FROM CLIENTE" +
                " WHERE telefono = ?";

        return datosCliente.buscarRegistro(telefonoCliente);
    }

    public void actualizar(String telefonoCliente, String atributo, String nuevoValor) throws SQLException {

        var resultados = buscarRegistro(telefonoCliente);

        if (resultados.next()) {
            realizarConsulta(resultados, atributo, nuevoValor);
        }
    }

    private void realizarConsulta(ResultSet resultados, String atributo, String nuevoValor) throws SQLException {

        var id = resultados.getInt("id");
        sql = "UPDATE PEDIDO SET " + atributo + " = ? " +
                "WHERE id = " + id;

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nuevoValor);
        preparedStatement.executeUpdate();
    }

    public void borrarRegistro(String telefono) throws SQLException {

        var id = buscarRegistro(telefono).getInt("id");
        sql = "DELETE PEDIDO, CLIENTE FROM PEDIDO INNER JOIN CLIENTE " +
                "WHERE PEDIDO.id = CLIENTE.id AND PEDIDO.id = " + id;
        statement.executeUpdate(sql);
    }

    public void cerrarConexion() throws SQLException {

        if (statement != null) {
            statement.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
