package datos;

import java.sql.*;

public class ConexionCliente {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USER = "root";
    private static final String PASS = "3H5fV8MFRN";
    private String sql;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public ConexionCliente() throws SQLException {
        conectar();
    }

    private static void conectar() throws SQLException {

        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public ResultSet leerTabla() throws SQLException {

        sql = "SELECT * FROM CLIENTE";
        return statement.executeQuery(sql);
    }

    public void insertarDato(String nombre, String apellido, String telefono, String correo) throws SQLException {

        sql = "INSERT INTO CLIENTE(nombre, apellido, telefono, correo)" +
                " VALUES (?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, nombre);
        preparedStatement.setString(2, apellido);
        preparedStatement.setString(3, telefono);
        preparedStatement.setString(4, correo);

        preparedStatement.executeUpdate();
    }

    public ResultSet buscarRegistro(String telefono) throws SQLException {

        sql = "SELECT id FROM CLIENTE" +
                " WHERE telefono = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, telefono);
        return preparedStatement.executeQuery();
    }

    public ResultSet buscarRegistroPorID(int id) throws SQLException {

        sql = "SELECT * FROM CLIENTE" +
                " WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void actualizar(String telefono, String atributo, String nuevoValor) throws SQLException {

        var resultados = buscarRegistro(telefono);

        if (resultados.next()) {
            realizarConsulta(resultados, atributo, nuevoValor);
        }
    }

    private void realizarConsulta(ResultSet resultados, String atributo, String nuevoValor) throws SQLException {

        var id = resultados.getInt("id");
        sql = "UPDATE CLIENTE SET " + atributo + " = ? " +
                "WHERE id = " + id;

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nuevoValor);
        preparedStatement.executeUpdate();
    }

    public void borrarRegistro(String telefono) throws SQLException {

        var id = buscarRegistro(telefono).getInt("id");
        sql = "DELETE FROM CLIENTE " +
                "WHERE id = " + id;
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
