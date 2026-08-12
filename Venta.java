import java.util.ArrayList;

public class Venta {

    private int id;
    private Cliente cliente;
    private ArrayList<Producto> productos;
    private ArrayList<Integer> cantidades;
    private double total;

    public Venta(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
        this.total = 0;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public double getTotal() {
        return total;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {

        if (producto == null) {
            return false;
        }

        if (cantidad <= 0) {
            return false;
        }

        if (!producto.disminuirStock(cantidad)) {
            return false;
        }

        productos.add(producto);
        cantidades.add(cantidad);

        double subtotal = producto.getPrecio() * cantidad;
        total += subtotal;

        return true;
    }

    public void mostrarInformacion() {

        System.out.println("Venta ID: " + id);
        System.out.println("Cliente: " + cliente.getNombre());

        for (int i = 0; i < productos.size(); i++) {

            Producto producto = productos.get(i);
            int cantidad = cantidades.get(i);

            System.out.println(
                "Producto: " + producto.getNombre() +
                " | Cantidad: " + cantidad +
                " | Subtotal: $" + (producto.getPrecio() * cantidad)
            );
        }

        System.out.println("TOTAL: $" + total);
        System.out.println("----------------------------");
    }
}