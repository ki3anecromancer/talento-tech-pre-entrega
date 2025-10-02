package com.techlab;

import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import java.util.List;
import java.util.Scanner;

public class AgregarProducto {

  private AgregarProducto() {}

  public static void ejecutar(Scanner scanner, List<Producto> productos) {

    Utilidades.mostrarCartel(30, "AGREGAR PRODUCTO");

    // Dar la posibilidad de cancelar y regresar
    if (opcionInicial(scanner) == 0) {
      return;
    }

    String tipo;
    String nombre;
    Double precio;
    int stock;
    String descripcion = "";
    Producto producto = null;
    Double volumenLitros = 0.0;
    Double pesoGramos = 0.0;

    // asignara variables verificadas
    System.out.println();
    tipo = Utilidades.elegirTipo(scanner);

    switch (tipo) {
      case "Bebida":
        volumenLitros = Utilidades.doubleValido(
            scanner, true, "Volumen en litros: ");
        break;
      case "Comida":
        pesoGramos = Utilidades.doubleValido(
            scanner, true, "Peso en gramos: ");
        break;
    }

    System.out.print("Nombre: ");
    nombre = Utilidades.textoSinEspaciosExtra(scanner);

    precio = Utilidades.doubleValido(scanner, true, "Precio: ");

    stock = Utilidades.integerValido(scanner, true, "Stock: ");

    System.out.print("Descripción (opcional dejar en blanco): ");
    descripcion = Utilidades.textoSinEspaciosExtra(scanner);

    // Confirmar si se quiere agregar el producto
    // No crearlo aún para evitar generar su ID
    // Crear el producto solo si está confirmado
    Utilidades.dejarEspacios(5);
    System.out.println("Usted está por agregar el producto:");
    System.out.println("Tipo: " + tipo);
    System.out.println(mostrarVolumen(tipo, volumenLitros, pesoGramos));
    System.out.println("Nombre: " + nombre);
    System.out.printf("Precio: $%.2f%n", precio);
    System.out.println("Stock: " + stock);
    System.out.println("Descripción: " + descripcion + "\n");

    if (confirmarAgregarProducto(scanner)) {
      // Crear producto y agregarlo
      if (tipo.equalsIgnoreCase("Bebida")) {
        producto = new Bebida(nombre, precio, stock, descripcion, volumenLitros);
      } else if (tipo.equalsIgnoreCase("Comida")) {
        producto = new Comida(nombre, precio, stock, descripcion, pesoGramos);
      }

      // Agregar el producto a la lista
      productos.add(producto);
      System.out.println("\nEl producto ha sido agregaddo con éxito.");
    } else {
      System.out.println("\nEl producto no ha sido agregado.");
    }
  }

  private static boolean confirmarAgregarProducto(Scanner scanner) {
    int opcion;

    System.out.print("""
        Confirmar
        1. Si
        2. No
        """);

    opcion = Utilidades.elijaUnaOpcion(scanner, 1, 2);

    return opcion == 1;
  }

  private static String mostrarVolumen(String tipo, Double volumenLitros, Double pesoGramos) {
    String mensaje = "";

    if (tipo.equalsIgnoreCase("Bebida")) {
      mensaje = String.format("Volumen en litros: %.2f L.", volumenLitros);
    } else if (tipo.equalsIgnoreCase("Comida")){
      mensaje = String.format("Peso en gramos: %.2f gr.", pesoGramos);
    }

    return mensaje;
  }

  private static int opcionInicial(Scanner scanner) {
    System.out.println("""
        1. Crear nuevo producto
        0. Regresar
        """);

    return Utilidades.elijaUnaOpcion(scanner, 0, 1);
  }
}
