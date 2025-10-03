package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class CrearPedido {

  private CrearPedido() {
  }

  public static void ejecutar(Scanner scanner, ProductosList productos, Pedido pedidos) {
    int opcion;

    do {
      Utilidades.mostrarCartel(30, "CREAR PEDIDO");
      mostrarMenuPrincipal();
      opcion = Utilidades.elijaUnaOpcion(scanner, 0, 1);

      switch (opcion) {
        case 1:
          crearPedido(scanner, productos, pedidos);
          Utilidades.enterParaContinuar(scanner);
          Utilidades.dejarEspacios(20);
          break;
        case 0:
      }
    } while (opcion != 0);
  }

  private static void mostrarMenuPrincipal() {
    System.out.println("""
        1. Crear pedido
        0. Regresar
        """);
  }

  private static void crearPedido(Scanner scanner, ProductosList productos, Pedido pedidos) {
    int id;

    System.out.println();
    id = Utilidades.integerValido(scanner, true, "Ingrese la 'ID' del producto: ");

    if (productos.existeId(id)) {
      System.out.println("\nProducto seleccionado:");
      productos.mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.println();
      // Si no hay stock, cancelar
      if (productos.getProductoPorId(id).getStock() == 0) {
        System.out.println("El producto se encuentra sin stock actualmente.");
      } else {
        int cantidad = Utilidades.integerValido(scanner, true, "Cantidad deseada: ");

        // Si el stock excede el disponible o la solicitud es 0, cancelar
        if (cantidad > productos.getProductoPorId(id).getStock()) {
          System.out.println("\n(!) Cantidad excesiva, stock actual: " +
              productos.getProductoPorId(id).getStock());
        } else if (cantidad == 0) {
          System.out.println("\n(!) No se pueden agregar 0 productos. Ingrese una cantidad real.");
        } else {
          Utilidades.crearLineas(30, true);
          System.out.println("\nPedido solicitado:");
          System.out.printf("%18s %d x %s",
              "Pedido:", cantidad, productos.getProductoPorId(id).getNombre());
          System.out.printf("%n%18s $%.2f",
              "Precio por unidad:", productos.getProductoPorId(id).getPrecio());
          System.out.printf("%n%18s $%.2f%n",
              "Total:", productos.getProductoPorId(id).getPrecio() * cantidad);

          System.out.println("\nConfirmar pedido");
          if (Utilidades.opcionSiNo(scanner)) {

            // Crear el nuevo pedido y agregarlo
            Producto nuevo = null;

            if (productos.getProductoPorId(id).getClass() == Bebida.class) {
              nuevo = new Bebida(
                  productos.getProductoPorId(id).getNombre(),
                  productos.getProductoPorId(id).getPrecio(),
                  cantidad,
                  productos.getProductoPorId(id).getDescripcion(),
                  ((Bebida) productos.getProductoPorId(id)).getVolumenLitros());
            } else if (productos.getProductoPorId(id).getClass() == Comida.class) {
              nuevo = new Comida(
                  productos.getProductoPorId(id).getNombre(),
                  productos.getProductoPorId(id).getPrecio(),
                  cantidad,
                  productos.getProductoPorId(id).getDescripcion(),
                  ((Comida) productos.getProductoPorId(id)).getPesoGramos());
            }

            pedidos.getProductos().agregarProducto(nuevo);

            // Modificar su id y reestablecer el id autoincremental
            nuevo.setIdReducirContador(productos.getProductoPorId(id).getId());

            // Modificar el stock
            int stock = productos.getProductoPorId(id).getStock();
            productos.getProductoPorId(id).setStock(stock - cantidad);

            // actualizar el ancho de las listas
            productos.actualizarTodosLosAnchos();
            pedidos.getProductos().actualizarTodosLosAnchos();

            System.out.println("\nEl pedido ha sido realizado con éxito.");
          } else {
            System.out.println("\nPedido cancelado.");
          }
        }
      }
    } else {
      System.out.println("\nNo existe un producto con la ID: " + id);
    }
  }
}
