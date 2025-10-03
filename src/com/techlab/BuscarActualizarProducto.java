package com.techlab;

import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class BuscarActualizarProducto {

  private BuscarActualizarProducto() {}

  public static void ejecutar(Scanner scanner, ProductosList productos) {
    int opcion;

    Utilidades.mostrarCartel(30, "BUSCAR/ACTUALIZAR PRODUCTO");

    // Opciones de tipo de búsqueda
    menuTipoDeBusqueda();
    opcion = Utilidades.elijaUnaOpcion(scanner, 0, 4);
    ejecutarOpcion(scanner, opcion, productos);
  }

  private static void menuTipoDeBusqueda() {
    System.out.println("""
        1. Buscar por nombre
        2. Buscar por ID
        3. Buscar por tipo (Bebida/Comida)
        4. Actualizar por ID
        0. Regresar
        """);
  }

  private static void ejecutarOpcion(Scanner scanner, int opcion, ProductosList productos) {
    // Variables para búsqueda
    ProductosList listaProductos = new ProductosList();
    String nombre, tipo;
    int id;

    switch (opcion) {
      case 1: // Buscar por nombre
        System.out.print("\nNombre del producto: ");
        nombre = Utilidades.textoSinEspaciosExtra(scanner);

        // Armar lista con productos que contengan el nombre
        listaProductos.agregarListaProductos(Utilidades.buscarProductosPorNombreParcial(productos.getLista(), nombre));

        // Mostrar lista de coincidencias en caso de no estar vacía
        if (listaProductos.getLista().isEmpty()) {
          System.out.println(
              "\n(!) No se ha encontrado ningún producto que coincida con el nombre: " + nombre);
        } else {
          System.out.printf("\n\tProductos que coinciden con el nombre '%s'%n", nombre);
          listaProductos.mostrarListaProductos(true, false, true, true, true, true, true);
        }
        break;
      case 2: // Buscar por ID
        System.out.println();
        id = Utilidades.integerValido(scanner, true, "ID del producto: ");

        // Agregar producto solo si existe uno con la id requerida
        if (Utilidades.buscarProductoPorId(productos.getLista(), id) != null) {
          listaProductos.agregarProducto(Utilidades.buscarProductoPorId(productos.getLista(), id));

          System.out.printf("\n\tProducto que coincide con la ID '%d'%n", id);

          listaProductos.mostrarListaProductos(true, false, true, true, true, true, true);
        } else {
          System.out.println(
              "\n(!) No se ha encontrado ningún producto que coincida con la ID: " + id);
        }
        break;
      case 3: // Buscar por tipo (Bebida/Comida)
        System.out.println();
        tipo = Utilidades.elegirTipo(scanner);
        listaProductos.agregarListaProductos(Utilidades.buscarProductosPorTipo(productos.getLista(), tipo));

        // Mostrar lista de coincidencias en caso de no estar vacía
        if (listaProductos.getLista().isEmpty()) {
          System.out.println(
              "\n(!) No se ha encontrado ningún producto que coincida con el tipo: " + tipo);
        } else {
          System.out.printf("\n\tProductos que coinciden con el tipo '%s'%n", tipo);
          listaProductos.mostrarListaProductos(true, false, true, true, true, true, true);
        }
        break;
      case 4: // Actualizar por ID
        System.out.println();
        id = Utilidades.integerValido(scanner, true, "ID del producto: ");

        // Si existe un producto con la id, proceder a modificar
        if (Utilidades.buscarProductoPorId(productos.getLista(), id) != null) {
          // Agregar a ProductoList para tener sus métodos y poder mostrar
          listaProductos.agregarProducto(Utilidades.buscarProductoPorId(productos.getLista(), id));

          System.out.println("\nEstá por editar el producto:");
          productos.mostrarProductoPorId(id);

          System.out.println("\n¿Desea continuar?");
          if (Utilidades.opcionSiNo(scanner)) {
            System.out.println("\nIngrese los nuevos valores");
            System.out.print("Nombre: ");
            nombre = Utilidades.textoSinEspaciosExtra(scanner);
            double precio = Utilidades.doubleValido(scanner, true, "Precio: ");
            int stock = Utilidades.integerValido(scanner, true, "Stock: ");
            System.out.print("Descripción: ");
            String descripcion = Utilidades.textoSinEspaciosExtra(scanner);
            double contenido = Utilidades.doubleValido(scanner, true, "Volumen: ");

            System.out.println("\nConfirmar:");
            if (Utilidades.opcionSiNo(scanner)) {
              productos.editarProductoPorId(id, nombre, precio, stock, descripcion, contenido);
              System.out.println("\nEl producto ha sido actualizado con éxito.");
            } else {
              System.out.println("\nEl producto no ha sido modificado.");
            }
          }

        } else {
          System.out.println(
              "\n(!) No se ha encontrado ningún producto que coincida con la ID: " + id);
        }
      case 0:
        break;
    }
  }
}
