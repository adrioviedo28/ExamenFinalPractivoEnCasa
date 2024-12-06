import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Inventario {

    // lista (List<Producto> productos) para almacenar los productos.
    private List<Producto> productos;

    //constructor
    public Inventario() {
        productos = new ArrayList<>();
    }
    //metodo que traiga todos los productos y recorra el listado
    public void traerProductosDelArchivo(String archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea; //variable linea, guarda cada linea del archivo (fila)
            //while lee cada linea si no esta vacia lee cada linea
            while ((linea = reader.readLine()) != null) {
                // Procesar cada línea separa cada columna por un coma
                String[] datos = linea.split(",");
                int idProducto =Integer.parseInt(datos[0]);
                String nombreProducto = datos[1];
                String categoria = datos[2];
                double precio = Double.parseDouble(datos[3]);
                int cantidadDisponible = Integer.parseInt(datos[4]);
                productos.add(new Producto(idProducto, nombreProducto, categoria, precio, cantidadDisponible));
            }
        } catch (IOException e) {
            System.out.println("Error al intentar recuperar informacion del archivo: " + e.getMessage());
        }
    }
//traer productos por el id
    public Producto traerProductoId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;  // devuelve el producto
            }
        }
        return null;  // si no es nulo
    }

    // Metodo - Agregar un producto al inventario
    public void agregarProducto(Producto producto, String archivo) throws IOException {
        productos.add(producto);
        guardarInventarioEnArchivo(archivo);  // Guardamos el inventario actualizado en el archivo
    }
    // Metodo - guardar productos en inventario

    public void guardarInventarioEnArchivo(String archivo) throws IOException {

        File nuevoArchivo = new File(archivo);  // Crea un arcivo en la ruta que indico
        System.out.println("se estan guardando los datos en la siguiente ruta: " + nuevoArchivo.getAbsolutePath());  // Imprime la ruta absoluta del archivo

        // si no hay un archivo que cree uno
        if (!nuevoArchivo.exists()) {
            nuevoArchivo.createNewFile();
            System.out.println("No hay archivo existente, se crea uno.");
        }
//el buffer escribe en cada linea del archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
           // el asigna los valoress del array y los separa con ,
            for (Producto producto : productos) {
                bw.write(producto.getId() + "," + producto.getNombreProducto() + "," + producto.getCategoria() + "," + producto.getPrecio() + "," + producto.getCantidadDisponible());
                bw.newLine();
            }
        }
    }

    // Metodo-Actualizar un producto
    public boolean actualizarProducto(int id, String nombre, String categoria, double precio, int cantidad) {
        Producto producto = traerProductoId(id);
        if(producto != null) {
            producto.setNombreProducto(nombre);// puedo cambiar el nombre
            producto.setCategoria(categoria); // la categoria
            producto.setPrecio(precio);// el precio
            producto.setCantidadDisponible(cantidad); // y la cantidad
            System.out.println("Producto actualizado correctamente");
            return true;
        } else //mensaje por si no es posible encontrar el producto que le pase
            System.out.println("No se encontro el producto");
        return false;
    }

    // Metodo-Eliminar un producto
    public boolean eliminarProducto(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                productos.remove(p);
                System.out.println("producto eliminado correctamente");
                return true; // Producto eliminado de manera exitosa
            }
        }
        return false; // No se encontró el producto con ese IDProducto
    }

    // Metodo-Calcular la cantidad total de productos en el inventario
    public int calcularCantidadTotal() {
        int total = 0;
        for (Producto p : productos) {
            total += p.getCantidadDisponible();
        }
        System.out.println("la cantidad total de productos disponibles es: "+total);
        return total;
    }

    public List<Producto> buscarPorCategoria(String categoria) { //creo una lista donde voy a guardar los productos filtrados
        List<Producto> productosPorCategoria = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equals(categoria)) { //si la categoria es igual a la que consulte se me guarda en la lista creada
                productosPorCategoria.add(producto);
            }
        }
        return productosPorCategoria;
    }

    // Metodo -conseguir el producto más costoso
    public Producto traerProductoMasCostoso() {
        if (productos.isEmpty()) {
            return null;  // recorre la lista y mira si hay contenido o no, si no hay devulve null
        }
        Producto productoMasCostoso = productos.get(0); // se asigna el primer producto a la variable y toma el puesto del mas caro
        for (Producto producto : productos) {
            if (producto.getPrecio() > productoMasCostoso.getPrecio()) { //traigo el precio de los productos y lo comparo con el que tomo el puesto del mas caro
                productoMasCostoso = producto; // si llega a haber un producto mas caro, se reasigna a la variable de arriba
            }
        }
        return productoMasCostoso;
    }

    // Metodo-Mostrar el inventario completo
    public void cantidadPorCategoria() {
        int detergente = 0;
        int fruta = 0;
        int verdura = 0;
        int licor = 0;
        int papeleria = 0;


        for (Producto producto : productos) {
            String productoCategoria = producto.getCategoria();

            if (productoCategoria.equals("Detergente")) {
                detergente += producto.getCantidadDisponible();
            }
            else if (productoCategoria.equals("Fruta")) {
                fruta += producto.getCantidadDisponible();
            }
            else if (productoCategoria.equals("Verdura")) {
                verdura += producto.getCantidadDisponible();
            }
            else if (productoCategoria.equals("Licor")) {
                licor += producto.getCantidadDisponible();
            }
            else if (productoCategoria.equals("Papeleria")) {
                papeleria += producto.getCantidadDisponible();
            }
        }


    }
    public void reporte(String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            //Estructura del reporte
            writer.write("Producto  | Categoría | Unidades Totales | Valor Total");
            writer.newLine();

            //se calcula el valor total del inventario
            double totalInventario = 0;

            for (Producto producto : productos) {
                //valor total de cada producto
                double totalProducto = producto.getPrecio() * producto.getCantidadDisponible();

                //se suma al valor total del inventario
                totalInventario += totalProducto;

                // Escribir los datos del producto en el archivo
                // los % ayudan a justificar el texto haci uno de los costados
                writer.write(String.format("%-10s | %-10s | %-15d | $%.2f",
                        producto.getNombreProducto(),
                        producto.getCategoria(),
                        producto.getCantidadDisponible(),
                        totalProducto));
                writer.newLine();
            }

            //este formato sirve para redondear valores
            writer.write("total inventario:  $" + String.format("%.2f", totalInventario));
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
}
