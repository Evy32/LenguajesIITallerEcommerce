import java.time.LocalDate;
import java.util.Scanner;

public class Ecommerce {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Tienda tienda = new Tienda("Tech Store");

        int opcion = -1;

        while (opcion != 0) {

            mostrarMenu();

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        registrarCliente(tienda);
                        break;

                    case 2:
                        registrarProducto(tienda);
                        break;

                    case 3:
                        tienda.listarClientes();
                        break;

                    case 4:
                        tienda.listarProductos();
                        break;

                    case 5:
                        realizarVenta(tienda);
                        break;

                    case 6:
                        tienda.listarVentas();
                        break;

                    case 7:
                        generarFactura(tienda);
                        break;

                    case 8:
                        buscarProductoPorNombre(tienda);
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion inexistente.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un numero valido.");
            }
        }

        scanner.close();
    }

    public static void mostrarMenu() {

        System.out.println();
        System.out.println("====================================");
        System.out.println("             E-COMMERCE");
        System.out.println("====================================");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar producto");
        System.out.println("3. Listar clientes");
        System.out.println("4. Listar productos");
        System.out.println("5. Realizar venta");
        System.out.println("6. Mostrar ventas");
        System.out.println("7. Generar factura");
        System.out.println("8. Buscar producto por nombre");
        System.out.println("0. Salir");
        System.out.println("====================================");
        System.out.print("Seleccione una opcion: ");
    }

    // ------------------------------------------------
    // REGISTRAR CLIENTE
    // ------------------------------------------------

    public static void registrarCliente(Tienda tienda) {

        System.out.println();
        System.out.println("Ingrese los datos:");
        System.out.println("id;nombre;email;telefono");

        String entrada = scanner.nextLine();

        String[] datos = entrada.split(";");

        if (datos.length != 4) {
            System.out.println("Datos incompletos.");
            return;
        }

        try {

            int id = Integer.parseInt(datos[0].trim());

            String nombre = datos[1].trim();
            String email = datos[2].trim();
            String telefono = datos[3].trim();

            if (tienda.buscarCliente(id) != null) {
                System.out.println("El identificador del cliente ya existe.");
                return;
            }

            Cliente cliente = new Cliente(
                    id,
                    nombre,
                    email,
                    telefono
            );

            if (tienda.agregarCliente(cliente)) {
                System.out.println("Cliente registrado correctamente.");
            }

        } catch (NumberFormatException e) {

            System.out.println("El ID debe ser un numero.");
        }
    }

    // ------------------------------------------------
    // REGISTRAR PRODUCTO
    // ------------------------------------------------

    public static void registrarProducto(Tienda tienda) {

        System.out.println();
        System.out.println("Ingrese los datos:");
        System.out.println("codigo;nombre;precio;stock");

        String entrada = scanner.nextLine();

        String[] datos = entrada.split(";");

        if (datos.length != 4) {
            System.out.println("Datos incompletos.");
            return;
        }

        try {

            String codigo = datos[0].trim();
            String nombre = datos[1].trim();

            double precio = Double.parseDouble(datos[2].trim());
            int stock = Integer.parseInt(datos[3].trim());

            if (precio < 0) {
                System.out.println("El precio no puede ser negativo.");
                return;
            }

            if (stock < 0) {
                System.out.println("El stock no puede ser negativo.");
                return;
            }

            if (tienda.buscarProducto(codigo) != null) {
                System.out.println("El codigo del producto ya existe.");
                return;
            }

            Producto producto = new Producto(
                    codigo,
                    nombre,
                    precio,
                    stock
            );

            if (tienda.agregarProducto(producto)) {
                System.out.println("Producto registrado correctamente.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Precio o stock invalidos.");
        }
    }

    // ------------------------------------------------
    // REALIZAR VENTA
    // ------------------------------------------------

    public static void realizarVenta(Tienda tienda) {

        System.out.println();
        System.out.println("Ingrese:");
        System.out.println("idVenta;idCliente;codigoProducto;cantidad");

        String entrada = scanner.nextLine();

        String[] datos = entrada.split(";");

        if (datos.length != 4) {
            System.out.println("Datos incompletos.");
            return;
        }

        try {

            int idVenta = Integer.parseInt(datos[0].trim());
            int idCliente = Integer.parseInt(datos[1].trim());
            String codigoProducto = datos[2].trim();
            int cantidad = Integer.parseInt(datos[3].trim());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
                return;
            }

            if (tienda.buscarVenta(idVenta) != null) {
                System.out.println("El ID de venta ya existe.");
                return;
            }

            Cliente cliente = tienda.buscarCliente(idCliente);

            if (cliente == null) {
                System.out.println("Cliente no encontrado.");
                return;
            }

            Producto producto = tienda.buscarProducto(codigoProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            if (cantidad > producto.getStock()) {
                System.out.println(
                    "No hay suficiente stock para realizar la venta."
                );
                return;
            }

            Venta venta = new Venta(idVenta, cliente);

            if (venta.agregarProducto(producto, cantidad)) {

                tienda.registrarVenta(venta);

                System.out.println("Venta realizada correctamente.");
                System.out.println("Total: $" + venta.getTotal());

            } else {

                System.out.println("No fue posible realizar la venta.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Los datos numericos son invalidos.");
        }
    }

    // ------------------------------------------------
    // GENERAR FACTURA
    // ------------------------------------------------

    public static void generarFactura(Tienda tienda) {

        System.out.print("Ingrese el ID de la venta: ");

        try {

            int idVenta = Integer.parseInt(scanner.nextLine());

            Venta venta = tienda.buscarVenta(idVenta);

            if (venta == null) {
                System.out.println("Venta no encontrada.");
                return;
            }

            int numeroFactura = 1000 + idVenta;

            String fecha = LocalDate.now().toString();

            Factura factura = new Factura(
                    numeroFactura,
                    venta,
                    fecha
            );

            factura.imprimirFactura();

        } catch (NumberFormatException e) {

            System.out.println("ID invalido.");
        }
    }

    // ------------------------------------------------
    // FUNCIONALIDAD ADICIONAL
    // ------------------------------------------------

    public static void buscarProductoPorNombre(Tienda tienda) {

        System.out.print("Ingrese el nombre del producto: ");

        String nombre = scanner.nextLine().trim();

        Producto producto = tienda.buscarProductoPorNombre(nombre);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
        } else {
            producto.mostrarInformacion();
        }
    }
}