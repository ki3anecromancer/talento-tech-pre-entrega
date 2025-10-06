package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class BuscarActualizarProducto {

  private BuscarActualizarProducto() {
  }

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
    switch (opcion) {
      case 1: // Buscar por nombre
        Utilidades.crearLineas(30, true);
        buscarProductoPorNombre(scanner, productos);
        break;
      case 2: // Buscar por ID
        Utilidades.crearLineas(30, true);
        buscarProductoPorId(scanner, productos);
        break;
      case 3: // Buscar por tipo (Bebida/Comida)
        Utilidades.crearLineas(30, true);
        buscarProductosPorTipo(scanner, productos);
        break;
      case 4: // Actualizar por ID
        Utilidades.crearLineas(30, true);
        actualizarProductoPorId(scanner, productos);
      case 0: // Regresar
        break;
    }
  }

  private static void buscarProductoPorNombre(Scanner scanner, ProductosList productos) {
    ProductosList listaProductos = new ProductosList();
    String nombre;

    nombre = Utilidades.textoSinEspaciosExtra(scanner, false, "Nombre del producto: ");

    // Armar lista con productos que contengan el nombre
    listaProductos.agregarListaProductos(
        Utilidades.buscarProductosPorNombreParcial(productos.getLista(), nombre));

    Utilidades.crearLineas(30, true);
    // Mostrar lista de coincidencias en caso de no estar vacía
    if (listaProductos.getLista().isEmpty()) {
      System.out.println(
          "(!) No se ha encontrado ningún producto que coincida con el nombre: " + nombre);
    } else {
      System.out.printf("\tProductos que coinciden con el nombre '%s'%n", nombre);
      listaProductos.mostrarListaProductos(true, true, true, true, true, true, true);
    }
  }

  private static void buscarProductoPorId(Scanner scanner, ProductosList productos) {
    int id;

    id = Utilidades.integerValido(scanner, true, "ID del producto: ");

    Utilidades.crearLineas(30, true);
    // Agregar producto solo si existe uno con la id requerida
    if (productos.existeId(id)) {
      System.out.printf("\tProducto que coincide con la ID '%d'%n", id);
      productos.mostrarProductoPorId(
          id, true, true, true, true, true, true, true);
    } else {
      System.out.println(
          "(!) No se ha encontrado ningún producto que coincida con la ID: " + id);
    }
  }

  private static void buscarProductosPorTipo(Scanner scanner, ProductosList productos) {
    ProductosList listaProductos = new ProductosList();
    String tipo;

    tipo = Utilidades.elegirTipo(scanner);
    listaProductos.agregarListaProductos(
        Utilidades.buscarProductosPorTipo(productos.getLista(), tipo));

    Utilidades.crearLineas(30, true);
    // Mostrar lista de coincidencias en caso de no estar vacía
    if (listaProductos.getLista().isEmpty()) {
      System.out.println(
          "(!) No se ha encontrado ningún producto que coincida con el tipo: " + tipo);
    } else {
      System.out.printf("\tProductos que coinciden con el tipo '%s'%n", tipo);
      listaProductos.mostrarListaProductos(true, true, true, true, true, true, true);
    }
  }

  private static void actualizarProductoPorId(Scanner scanner, ProductosList productos) {
    ProductosList listaProductos = new ProductosList();
    int id;
    String nombre;
    double contenido = 0.0;
    String tipo;

    id = Utilidades.integerValido(scanner, true, "ID del producto: ");

    Utilidades.crearLineas(30, true);
    // Si existe un producto con la id, proceder a modificar
    if (productos.existeId(id)) {
      // Agregar a ProductoList para tener sus métodos y poder mostrar
      listaProductos.agregarProducto(Utilidades.buscarProductoPorId(productos.getLista(), id));

      // Obtener el tipo
      tipo = Utilidades.buscarProductoPorId(productos.getLista(), id).getTipo();

      System.out.println("Está por editar el producto:");
      productos.mostrarProductoPorId(id, true, true, true, true, true, true, true);

      System.out.println("\n¿Desea continuar?");
      if (Utilidades.opcionSiNo(scanner)) {
        Utilidades.crearLineas(30, true);
        System.out.println("Ingrese los nuevos valores");
        nombre = Utilidades.textoSinEspaciosExtra(scanner, false, "Nombre: ");
        if (tipo.equalsIgnoreCase("Comida")) {
          contenido = Utilidades.doubleValido(scanner, true, false, "Gramos: ");
        } else if (tipo.equalsIgnoreCase("Bebida")) {
          contenido = Utilidades.doubleValido(scanner, true, false, "Litros: ");
        }
        double precio = Utilidades.doubleValido(scanner, true, true, "Precio: ");
        int stock = Utilidades.integerValido(scanner, true, "Stock: ");
        String descripcion = Utilidades.textoSinEspaciosExtra(scanner, true, "Descripción: ");

        // Mostrar los nuevos valores con formato
        Utilidades.crearLineas(30, true);
        System.out.println("Nuevos datos modificados:");
        System.out.println("Nombre: " + nombre);
        if (tipo.equalsIgnoreCase("Comida")) {
          System.out.printf("Peso en gramos: %.2f gr.%n", contenido);
        } else if (tipo.equalsIgnoreCase("Bebida")) {
          System.out.printf("Volumen en litros: %.2f L%n", contenido);
        }
        System.out.printf("Precio: $%.2f%n", precio);
        System.out.println("Stock: " + stock);
        System.out.println("Descripción: " + descripcion);

        System.out.println("\nConfirmar:");
        if (Utilidades.opcionSiNo(scanner)) {
          productos.editarProductoPorId(id, nombre, precio, stock, descripcion, contenido);
          Utilidades.crearLineas(30, true);
          System.out.println("El producto ha sido actualizado con éxito.");
        } else {
          Utilidades.crearLineas(30, true);
          System.out.println("El producto no ha sido modificado.");
        }
      } else {
        Utilidades.crearLineas(30, true);
        System.out.println("Edición de producto cancelada.");
      }
    } else {
      System.out.println(
          "(!) No se ha encontrado ningún producto que coincida con la ID: " + id);
    }
  }
}
