package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private int id;
    private Cliente cliente;
    private Date fechaPedido;
    private int costo;
    private String nombreProducto;

    public Pedido(int id, Cliente cliente, Date fechaPedido, int costo, String nombreProducto) {
        this.id = id;
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.costo = costo;
        this.nombreProducto = nombreProducto;
    }

    public Pedido(int id, Date fechaPedido, int costo, String nombreProducto) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.costo = costo;
        this.nombreProducto = nombreProducto;
    }

    public Pedido() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "; Cliente: " + cliente.toString()
                + "; Fecha pedido: " + fechaPedido
                + "; Costo: " + costo
                + "; Nombre producto: " + nombreProducto;
    }
}
