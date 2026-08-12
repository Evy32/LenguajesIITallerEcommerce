public class Factura {

    private int numero;
    private Venta venta;
    private String fecha;

    public Factura(int numero, Venta venta, String fecha) {
        this.numero = numero;
        this.venta = venta;
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public Venta getVenta() {
        return venta;
    }

    public String getFecha() {
        return fecha;
    }

    public void imprimirFactura() {

        System.out.println("================================");
        System.out.println("             FACTURA");
        System.out.println("================================");

        System.out.println("Numero: " + numero);
        System.out.println("Fecha: " + fecha);

        System.out.println();
        System.out.println("Cliente: " + venta.getCliente().getNombre());
        System.out.println("Email: " + venta.getCliente().getEmail());

        System.out.println();

        for (int i = 0; i < venta.getProductos().size(); i++) {

            Producto producto = venta.getProductos().get(i);
            int cantidad = venta.getCantidades().get(i);

            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Subtotal: $" +
                    (producto.getPrecio() * cantidad));
            System.out.println();
        }

        System.out.println("TOTAL: $" + venta.getTotal());

        System.out.println("================================");
    }
}