import java.awt.Menu;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Inventario inventario = new Inventario();
        inventario.traerProductosDelArchivo("C:\\Users\\aovied4\\Documents\\ExamenFinalPractivoEnCasa\\Data\\Inventario.txt");

      // Crear el objeto MenuPrincipal, pasando el inventario y la ruta del archivo
        MenuPrincipal menu = new MenuPrincipal(inventario, "C:\\Users\\aovied4\\Documents\\ExamenFinalPractivoEnCasa\\Data\\Inventario.txt");

        //visualizar Menu
        menu.mostrarMenu();
    }
}
