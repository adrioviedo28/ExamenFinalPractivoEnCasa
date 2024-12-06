public class Producto {
//Los atributos para la Clase Producto
    private int idProducto;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int cantidadDisponible;

    //el contructor para poder inicializar los atributos
    // (public Producto(...)) se usa para crear un nuevo objeto Producto y asignar valores a sus atributos cuando lo instanciamos.
    public Producto(int idProducto, String nombreProducto, String categoria, double precio, int cantidadDisponible) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
}

    // Métodos getter (leer valores de los atributos) y setter (modificar valores de los atributos)

    // conseguir el ID del producto
    public int getId() {
        return idProducto;
    }

    // configurar el ID del producto
    public void setId(int idProducto) {
        this.idProducto = idProducto;
    }

    //  Conseguir el nombre del producto
    public String getNombreProducto() {
        return nombreProducto;
    }

    // configurar el nombre del producto
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    // Conseguir la categoría del producto
    public String getCategoria() {
        return categoria;
    }

    // configurar la categoría del producto
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // conseguir el precio del producto
    public double getPrecio() {
        return precio;
    }

    // configurar el precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // conseguir la cantidad disponible del producto
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    // configurar la cantidad disponible del producto
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    // Método para mostrar los detalles del producto
    public void mostrarDetalles() {
        System.out.println("ID: " + idProducto);
        System.out.println("Nombre: " + nombreProducto);
        System.out.println("Categoría: " + categoria);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad disponible: " + cantidadDisponible);
    }
}