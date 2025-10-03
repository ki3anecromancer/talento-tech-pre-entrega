package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class EliminarProducto {

  private EliminarProducto() {
  }

  public static void ejecutar(Scanner scanner, ProductosList productos) {
    int opcion;

    Utilidades.mostrarCartel(30, "ELIMINAR PRODUCTO");

    mostrarMenuInicial();
    opcion = Utilidades.elijaUnaOpcion(scanner, 0, 1);

    switch (opcion) {
      case 1: // Eliminar producto por ID
        eliminarProductoPorId(scanner, productos);
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

  private static void eliminarProductoPorId(Scanner scanner, ProductosList productos) {
    System.out.println();
    int id = Utilidades.integerValido(scanner, true, "ID del producto: ");

    if (productos.existeId(id)) {
      System.out.println("\nEstá por eliminar el producto:");
      productos.mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.println("\nConfirmar eliminación");
      if (Utilidades.opcionSiNo(scanner)) {
        productos.eliminarProductoPorId(id);
        System.out.println("\nEl producto ha sido eliminado con éxito.");
      } else {
        System.out.println("\nEl producto no ha sido eliminado.");
      }
    } else {
      System.out.println("\nNo existe un producto con la ID: " + id);
    }
  }
}
