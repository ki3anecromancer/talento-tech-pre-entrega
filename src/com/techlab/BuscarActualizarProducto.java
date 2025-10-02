package com.techlab;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductosList;
import java.util.List;
import java.util.Scanner;

public class BuscarActualizarProducto {

  private BuscarActualizarProducto() {}

  public static void ejecutar(Scanner scanner, List<Producto> productos) {
    int opcion;

    Utilidades.mostrarCartel(30, "BUSCAR/ACTUALIZAR PRODUCTO");

    // Opciones de tipo de búsqueda
    menuTipoDeBusqueda();
    opcion = Utilidades.elijaUnaOpcion(scanner, 0, 3);
    ejecutarOpcion(scanner, opcion, productos);
  }

  private static void menuTipoDeBusqueda() {
    System.out.println("""
        1. Buscar por nombre
        2. Buscar por ID
        3. Buscar por tipo (Bebida/Comida)
        0. Regresar
        """);
  }

  private static void ejecutarOpcion(Scanner scanner, int opcion, List<Producto> productos) {
    // Variables para búsqueda
    ProductosList listaProductos = new ProductosList();
    String nombre, tipo;
    int id;

    switch (opcion) {
      case 1: // Buscar por nombre
        System.out.print("\nNombre del producto: ");
        nombre = Utilidades.textoSinEspaciosExtra(scanner);

        // Armar lista con productos que contengan el nombre
        listaProductos.agregarListaProductos(Utilidades.buscarProductosPorNombreParcial(productos, nombre));

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
        listaProductos.agregarProducto(Utilidades.buscarProductoPorId(productos, id));

        if (listaProductos.getLista() != null) {
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
        listaProductos.agregarListaProductos(Utilidades.buscarProductosPorTipo(productos, tipo));

        System.out.println();
        // Mostrar lista de coincidencias en caso de no estar vacía
        if (listaProductos.getLista().isEmpty()) {
          System.out.println(
              "\n(!) No se ha encontrado ningún producto que coincida con el tipo: " + tipo);
        } else {
          System.out.printf("\n\tProductos que coinciden con el tipo '%s'%n", tipo);
          listaProductos.mostrarListaProductos(true, false, true, true, true, true, true);
        }
        break;
      case 0:
        break;
    }
  }
}
