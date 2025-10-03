package com.techlab;

import com.techlab.opciones.AgregarProducto;
import com.techlab.opciones.BuscarActualizarProducto;
import com.techlab.opciones.CrearPedido;
import com.techlab.opciones.EliminarProducto;
import com.techlab.opciones.ListaPedidos;
import com.techlab.opciones.ListaProductos;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import com.techlab.excepciones.DoubleInvalidoException;
import com.techlab.excepciones.IntegerInvalidoException;
import com.techlab.productos.ProductosList;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Utilidades {

  private Utilidades() {
  }

  public static void mostrarMenu() {
    System.out.println("""
        1. Agregar producto
        2. Lista productos
        3. Buscar/Actualizar producto
        4. Eliminar producto
        5. Crear pedido
        6. Lista pedidos
        0. Salir
        """);
  }

  public static void ejecutarOpcion(int opcion, Scanner scanner,
      ProductosList productos, Pedido pedidos) {

    switch (opcion) {
      case 1: // Agregar producto
        dejarEspacios(20);
        AgregarProducto.ejecutar(scanner, productos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 2: // Lista productos
        dejarEspacios(20);
        ListaProductos.ejecutar(productos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 3: // Buscar/Actualizar producto
        dejarEspacios(20);
        BuscarActualizarProducto.ejecutar(scanner, productos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 4: // Eliminar producto
        dejarEspacios(20);
        EliminarProducto.ejecutar(scanner, productos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 5: // Crear pedido
        dejarEspacios(20);
        CrearPedido.ejecutar(scanner, productos, pedidos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 6: // Lista pedidos
        dejarEspacios(20);
        ListaPedidos.ejecutar(pedidos);
        enterParaContinuar(scanner);
        dejarEspacios(20);
        break;
      case 0: // Salir
        System.out.println("Fin del programa...");
        break;
    }
  }

  public static void mostrarCartel(int borde, String mensaje) {
    // Calculo para centrar el mensaje
    int espacios = borde / 2 - mensaje.length() / 2;

    crearLineas(borde, true);
    if (espacios > 0) {
      for (int i = 0; i < espacios; i++) {
        System.out.print(" ");
      }
    }
    System.out.println(mensaje);
    crearLineas(borde, true);
  }

  public static void enterParaContinuar(Scanner scanner) {
    System.out.print("\nPresione 'ENTER' para continuar...");
    scanner.nextLine();
  }

  public static void dejarEspacios(int espacios) {
    if (espacios > 0) {
      for (int i = 0; i < espacios; i++) {
        System.out.println();
      }
    }
  }

  public static void crearLineas(int cantidad, boolean espacioFinal) {
    for (int i = 0; i < cantidad; i++) {
      if (i == cantidad - 1 && espacioFinal) {
        System.out.println("=");
      } else {
        System.out.print("=");
      }
    }
  }

  public static boolean validarOpcion(int opcion, int minOpcion, int maxOpcion) {
    if (!(opcion >= minOpcion && opcion <= maxOpcion)) {
      System.out.println("Opción ingresada inválida.");
    }

    return opcion >= minOpcion && opcion <= maxOpcion;
  }

  public static int elijaUnaOpcion(Scanner scanner, int minOpcion, int maxOpcion) {
    int opcion;

    do {
      opcion = integerValido(scanner, true, "Elija una opción: ");
    } while (!validarOpcion(opcion, minOpcion, maxOpcion));

    return opcion;
  }

  public static Double doubleValido(Scanner scanner, boolean esPositivo, String mensaje) {
    double numero;

    while (true) {
      try {
        System.out.print(mensaje);
        numero = scanner.nextDouble();

        if (esPositivo && numero < 0) {
          throw new DoubleInvalidoException();
        }

        scanner.nextLine();
        break;
      } catch (InputMismatchException | DoubleInvalidoException ex) {
        System.out.println("Número ingresado inválido.");
        scanner.nextLine();
      }
    }

    return numero;
  }

  public static Integer integerValido(Scanner scanner, boolean esPositivo, String mensasje) {
    int numero;

    while (true) {
      try {
        System.out.print(mensasje);
        numero = scanner.nextInt();

        if (esPositivo && numero < 0) {
          throw new IntegerInvalidoException();
        }

        scanner.nextLine();
        break;
      } catch (InputMismatchException | IntegerInvalidoException ex) {
        System.out.println("Número ingresado inválido.");
        scanner.nextLine();
      }
    }

    return numero;
  }

  public static String textoSinEspaciosExtra(Scanner scanner) {
    String nombre = scanner.nextLine();

    // Quitar espacios en blanco al comienzo y al final
    // Reemplazar todos los espacios en blanco en el medio por uno solo
    // "  a   b c " = "a b c"
    return nombre.trim().replaceAll("\\s+", " ");
  }

  public static boolean nombreCompletoExiste(List<Producto> productos, String nombreProducto) {
    for (Producto producto : productos) {
      if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
        return true;
      }
    }
    return false;
  }

  public static boolean nombreParcialExiste(Producto producto, String nombreProducto) {
    return producto.getNombre().toLowerCase().contains(nombreProducto.toLowerCase());
  }

  public static List<Producto> buscarProductosPorNombreParcial(
      List<Producto> productos, String nombre) {

    List<Producto> productosCoincidentes = new ArrayList<>();

    for (Producto producto : productos) {
      if (nombreParcialExiste(producto, nombre)) {
        productosCoincidentes.add(producto);
      }
    }

    return productosCoincidentes;
  }

  public static Producto buscarProductoPorId(
      List<Producto> productos, int id) {

    for (Producto producto : productos) {
      if (producto.getId() == id) {
        return producto;
      }
    }

    return null;
  }

  public static List<Producto> buscarProductosPorTipo(
      List<Producto> productos, String tipo) {

    List<Producto> productosCoincidentes = new ArrayList<>();

    // Solo agregar los productos que coincidan con la clase Comida/Bebida según el tipo
    for (Producto producto : productos) {
      if (tipo.equalsIgnoreCase("Bebida")
          && producto.getClass() == Bebida.class) {
        productosCoincidentes.add(producto);
      } else if (tipo.equalsIgnoreCase("Comida")
          && producto.getClass() == Comida.class) {
        productosCoincidentes.add(producto);
      }
    }

    return productosCoincidentes;
  }

  public static String armarFormato(int espacio, String formato) {
    return "%-" + espacio + formato;
  }

  public static String elegirTipo(Scanner scanner) {
    int opcion;

    System.out.print("""
        Tipo de producto
        1. Bebida
        2. Comida
        """);

    System.out.println();
    opcion = elijaUnaOpcion(scanner, 1, 2);

    return opcion == 1 ? "Bebida" : "Comida";
  }

  public static boolean opcionSiNo(Scanner scanner) {
    int opcion;

    System.out.println("""
        1. Si
        2. No
        """);

    opcion = elijaUnaOpcion(scanner, 1, 2);

    return opcion == 1;
  }
}
