import java.util.ArrayList;

public class Tienda {

    private String nombre;

    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venta> ventas;

    public Tienda(String nombre) {
        this.nombre = nombre;

        productos = new ArrayList<>();
        clientes = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    // ---------------- PRODUCTOS ----------------

    public boolean agregarProducto(Producto producto) {

        if (producto == null) {
            return false;
        }

        // Evitar códigos repetidos
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        productos.add(producto);
        return true;
    }

    public Producto buscarProducto(String codigo) {

        for (Producto producto : productos) {

            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null;
    }

    public Producto buscarProductoPorNombre(String nombre) {

        for (Producto producto : productos) {

            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }

        return null;
    }

    public void listarProductos() {

        System.out.println("\n===== PRODUCTOS =====");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            producto.mostrarInformacion();
        }
    }

    // ---------------- CLIENTES ----------------

    public boolean agregarCliente(Cliente cliente) {

        if (cliente == null) {
            return false;
        }

        if (buscarCliente(cliente.getId()) != null) {
            return false;
        }

        clientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(int id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    public void listarClientes() {

        System.out.println("\n===== CLIENTES =====");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : clientes) {
            cliente.mostrarInformacion();
        }
    }

    // ---------------- VENTAS ----------------

    public void registrarVenta(Venta venta) {

        if (venta != null) {
            ventas.add(venta);
        }
    }

    public Venta buscarVenta(int id) {

        for (Venta venta : ventas) {

            if (venta.getId() == id) {
                return venta;
            }
        }

        return null;
    }

    public void listarVentas() {

        System.out.println("\n===== VENTAS =====");

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta venta : ventas) {
            venta.mostrarInformacion();
        }
    }
}