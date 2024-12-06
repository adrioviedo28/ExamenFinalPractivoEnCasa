import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class MenuPrincipal {
    private Inventario inventario;
    private  String archivo;

        //contructor
        public MenuPrincipal(Inventario inventario, String archivo) {
            this.inventario = inventario;  // trae el inventario
            this.archivo = archivo;  // trae el archivo
        }
//Menu

    public void mostrarMenu() throws IOException { //es la excepcion de java
        Scanner scanner = new Scanner(System.in);
        //interaccion con el usuario

        System.out.println(" - - - - - - - Menu Principal - - - - - - ");
        System.out.println("|Selecciona la accion que deseas realizar|");
        System.out.println("|1. Agregar producto                     |");
        System.out.println("|2. Actualizar producto                  |");
        System.out.println("|3. Eliminar producto                    |");
        System.out.println("|4. Buscar por categoría                 |");
        System.out.println("|5. Generar reporte                      |");
        System.out.println("|6. Cantidad de productos por categoría  |");
        System.out.println("|7. Producto mas costoso                 |");
        System.out.println("|8. Salir                                |");
        System.out.println("------------------------------------------");
        int opcion = scanner.nextInt();

        scanner.nextLine();  // Limpiar el buffer

        //opciones del menu
        switch (opcion) {
            case 1:
                // Agregar producto
                agregarProducto(scanner);
                break;
            case 2:
                // Actualizar producto
                actualizarProducto(scanner);
                break;
            case 3:
                // Eliminar producto
                eliminarProducto(scanner);
                break;
            case 4:
                // Buscar por categoría
                buscarPorCategoria(scanner);
                break;
            case 5:
                // Generar reporte
                inventario.reporte("reporte_Productos.txt");
                break;
            case 6:
                // Cantidad de productos por categoría
                cantidadPorCategoria();
                break;
            case 7:
                // Producto mas caro
                productoMasCaro();
                break;
            case 8:
                // Salir
                System.out.println("gracias por la visita");
                return;  // finalizo el programa
            default:
                System.out.println("Opcion no valida");
        }

        // Volver a mostrar el menú despues de seleccionar una opcion
        mostrarMenu();
    }

    // Métodos auxiliares para cada opción del menú
    private void agregarProducto(Scanner scanner) throws IOException {
        System.out.print("Digita el ID: ");
        int id = Integer.parseInt(scanner.nextLine()); // comvertirlo de string a int
        System.out.print("Digita un nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Digita una categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Digita un precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Digita una cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        inventario.agregarProducto(new Producto(id, nombre, categoria, precio, cantidad), archivo);
        System.out.println("Se agrego el producto correctamente");
    }

    private void actualizarProducto(Scanner scanner) throws IOException {
        System.out.print("Indica el id del producto que deseas actualizar, deja vacio los campos que no deseas actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        //buscar el id
        Producto productoExistente = inventario.traerProductoId(id);
        if (productoExistente == null) {
            System.out.println("No pudimos encontrar el id que ingresaste");
            return;
        }
        //solicitar los nuevos datos
        System.out.print("Ingresa el nombre del producto que se va a cambiar:  ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            nombre = productoExistente.getNombreProducto();
        }
        System.out.print("Ingresa la nueva categoría ");
        String categoria = scanner.nextLine();
        if (categoria.isEmpty()) {
            categoria = productoExistente.getCategoria();
        }
        System.out.print("Ingresa un nuevo precio: ");
        String precioStr = scanner.nextLine();
        double precio = productoExistente.getPrecio();
        if (!precioStr.isEmpty()) {
            precio = Double.parseDouble(precioStr);
        }
        System.out.print("Ingresa una nueva cantidad: ");
        String cantidadStr = scanner.nextLine();
        int cantidad = productoExistente.getCantidadDisponible();
        if (!cantidadStr.isEmpty()) {
            cantidad = Integer.parseInt(cantidadStr);
        }
        // Crear el nuevo producto con los datos actualizados
        Producto nuevoProducto = new Producto(id, nombre, categoria, precio, cantidad);
        // Reemplazar el producto viejo con el actualizado
        boolean actualizado = inventario.actualizarProducto(id, nombre, categoria, precio, cantidad);
        if (actualizado) {
            System.out.println("Producto actualizado exitosamente.");
            inventario.guardarInventarioEnArchivo(archivo);  // Guardar los cambios en el archivo
        } else {
            System.out.println("Error al actualizar los datos");
        }
    }

    private void eliminarProducto(Scanner scanner) throws IOException {
        System.out.print("Introduce ID del producto que deseas eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        scanner.nextLine(); // Limpiar el buffer

        if (inventario.eliminarProducto(id)) {
            System.out.println("Producto eliminado exitosamente");
            inventario.guardarInventarioEnArchivo(archivo);  // Guardar los cambios en el archivo
        } else {
            System.out.println("No se encontro el producto");
        }
    }

    private void buscarPorCategoria(Scanner scanner) {
        System.out.print("Introduce categoría, recuerda que las categorias son: ");
        System.out.println("detergente");
        System.out.println("licor");
        System.out.println("fruta");
        System.out.println("verdura");
        System.out.println("papeleria");
        String categoria = scanner.nextLine();
        List<Producto> productosCategoria = inventario.buscarPorCategoria(categoria);

        //Valida si hay oo no productos
        if (productosCategoria.isEmpty()) {
            System.out.println("No se encontraron productos para esta categoria");
        } else {
            for (Producto p : productosCategoria) { //recorre todos los productos
                System.out.println( "Los productos que pertenecen a la categoria que ingresaste: ");
                System.out.println( "ID: " + p.getId());
                System.out.println( "Nombre: " + p.getNombreProducto());
                System.out.println( "Categoria: " + p.getCategoria());
                System.out.println( "Precio: $" + p.getPrecio());
                System.out.println( "Cantidad: " + p.getCantidadDisponible());
            }
        }
    }

    private void generarReporte() {
        inventario.reporte("reporte_Producto.txt");
        System.out.println("Se ha generado exitosamente el reporte de inventario, se ha guardado en la carpeta raiz de tu proyecto");
    }

    private void cantidadPorCategoria() {
        inventario.cantidadPorCategoria();
    }

    private void productoMasCaro() {
        Producto productoCaro = inventario.traerProductoMasCostoso();
        if (productoCaro != null) {
            System.out.println("El producto mas caro del inventario es: ");
            System.out.println("ID: " + productoCaro.getId());
            System.out.println("Nombre: " + productoCaro.getNombreProducto());
            System.out.println("Categoria: " + productoCaro.getCategoria());
            System.out.println("Precio: $" + productoCaro.getPrecio());
            System.out.println("Cantidad: " + productoCaro.getCantidadDisponible());
        } else {
            System.out.println("No se encontraron productos");
        }
    }
}
