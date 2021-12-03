import servicio.ServicioCliente;
import servicio.ServicioPedido;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        iniciar();
    }

    public static void elegir(int eleccion){

        switch (eleccion) {
            case 0 -> ServicioPedido.iniciar();
            case 1 -> ServicioCliente.iniciar();
            default -> System.err.println("No se ha elegido un servicio válido");
        }
    }

    public static void mostrarMenu(){

        System.out.println("Seleccione una de las siguientes opciones");
        System.out.println("[0] mostrar pedidos");
        System.out.println("[1] mostrar clientes");
    }

    public static void iniciar(){
        mostrarMenu();
        var eleccion = new Scanner(System.in).nextInt();
        elegir(eleccion);
    }
}
