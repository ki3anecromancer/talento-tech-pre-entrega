package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.pedidos.Pedidos;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class EliminarProducto {

  private EliminarProducto() {
  }

  public static void ejecutar(Scanner scanner, ProductosList productos, Pedidos pedidos) {
    int opcion;

    Utilidades.mostrarCartel(30, "ELIMINAR PRODUCTO");

    mostrarMenuInicial();
    opcion = Utilidades.elijaUnaOpcion(scanner, 0, 1);

    switch (opcion) {
      case 1: // Eliminar producto por ID
        Utilidades.crearLineas(30, true);
        eliminarProductoPorId(scanner, productos, pedidos);
        break;
      case 0: // Regresar
    }
  }

  private static void mostrarMenuInicial() {
    System.out.println("""
        1. Eliminar producto por ID
        0. Regresar
        """);
  }

  private static void eliminarProductoPorId(
      Scanner scanner, ProductosList productos, Pedidos pedidos) {

    int id = Utilidades.integerValido(scanner, true, "ID del producto: ");

    Utilidades.crearLineas(30, true);
    if (productos.existeId(id)) {
      System.out.println("Está por eliminar el producto:");
      productos.mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.println("\nConfirmar eliminación");
      if (Utilidades.opcionSiNo(scanner)) {
        Utilidades.crearLineas(30, true);

        // Si el producto existe en la lista de pedidos, eliminarlo
        if (pedidos.getProductos().existeId(id)) {
          pedidos.getProductos().eliminarProductoPorId(id);
          System.out.println("El producto ha sido eliminado de la lista de pedidos.");
        }

        // Eliminar el producto de la lista
        productos.eliminarProductoPorId(id);

        System.out.println("El producto ha sido eliminado con éxito.");
      } else {
        Utilidades.crearLineas(30, true);
        System.out.println("El producto no ha sido eliminado.");
      }
    } else {
      System.out.println("No existe un producto con la ID: " + id);
    }
  }
}
