package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.pedidos.Pedidos;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class ListaPedidos {

  private ListaPedidos() {
  }

  public static void ejecutar(Scanner scanner, ProductosList productos, Pedidos pedidos) {
    Utilidades.mostrarCartel(30, "LISTA DE PEDIDOS");

    if (pedidos.getProductos().estaVacia()) {
      System.out.println("La lista de pedidos se encuentre actualmente vacía.");
    } else {
      pedidos.getProductos().mostrarListaProductos(true, true, true, true, true, true, true);

      System.out.print("\nValor total de los pedidos: ");
      pedidos.mostrarPrecioListaCompleta();

      switch (opcionesPedido(scanner)) {
        case 1: // Modificar pedido
          modificarPedido(scanner, productos, pedidos);
          break;
        case 2: // Borrar pedido
          borrarPedido(scanner, productos, pedidos);
          break;
        case 0: // Salir
      }
    }
  }

  private static int opcionesPedido(Scanner scanner) {
    int opcion;

    Utilidades.crearLineas(30, true);
    System.out.println("""
        1. Modificar pedido
        2. Borrar pedido
        0. Salir
        """);

    opcion = Utilidades.elijaUnaOpcion(scanner, 0, 2);
    return opcion;
  }

  private static void modificarPedido(Scanner scanner, ProductosList productos, Pedidos pedidos) {
    int id;
    int cantidad;
    int stockMax;

    Utilidades.crearLineas(30, true);
    id = Utilidades.integerValido(scanner, true, "Ingrese la ID del pedido: ");

    if (pedidos.getProductos().existeId(id)) {
      stockMax = productos.getProductoPorId(id).getStock() +
          pedidos.getProductos().getProductoPorId(id).getStock();

      System.out.println("\nPedido seleccionado:");
      pedidos.getProductos().mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.printf("%nCantidad de stock aún disponible: %d%n",
          productos.getProductoPorId(id).getStock());
      System.out.println();
      cantidad = Utilidades.integerValido(scanner, true,
          String.format("Ingrese la nueva cantidad deseada [máximo %d]: ", stockMax));

      Utilidades.crearLineas(30, true);
      // Si la cantidad no es válida: cancelar
      if (cantidad == 0 || cantidad > stockMax) {
        System.out.println("(!) No es válida la cantidad ingresada: " + cantidad);
      } else {
        // Actualizar el stock de pedidos
        pedidos.getProductos().getProductoPorId(id).setStock(cantidad);
        // Actualizar el stock de productos
        productos.getProductoPorId(id).setStock(stockMax - cantidad);

        System.out.println("El pedido ha sido actualizado con éxito.");
      }
    } else {
      Utilidades.crearLineas(30, true);
      System.out.println("(!) No existe un pedido con la ID: " + id);
    }
  }

  private static void borrarPedido(Scanner scanner, ProductosList productos, Pedidos pedidos) {
    int id;
    int stockMax;

    Utilidades.crearLineas(30, true);
    id = Utilidades.integerValido(scanner, true, "Ingrese la ID del pedido: ");

    Utilidades.crearLineas(30, true);
    // Si no existe: cancelar
    if (!pedidos.getProductos().existeId(id)) {
      System.out.println("(!) No existe un pedido con la ID: " + id);
    } else {
      System.out.println("Pedido seleccionado:");
      pedidos.getProductos().mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.println("\nConfirmar eliminación");
      if (Utilidades.opcionSiNo(scanner)) {
        // Reestablecer el stock
        stockMax = pedidos.getProductos().getProductoPorId(id).getStock() +
            productos.getProductoPorId(id).getStock();

        productos.getProductoPorId(id).setStock(stockMax);

        // Eliminar el pedido
        pedidos.getProductos().eliminarProductoPorId(id);

        Utilidades.crearLineas(30, true);
        System.out.println("El pedido ha sido eliminado con éxito.");
      } else {
        System.out.println("El pedido no ha sido eliminado.");
      }
    }
  }
}
