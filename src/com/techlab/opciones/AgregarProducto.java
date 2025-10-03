package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.Producto;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class AgregarProducto {

  private AgregarProducto() {
  }

  public static void ejecutar(Scanner scanner, ProductosList productos) {

    Utilidades.mostrarCartel(30, "AGREGAR PRODUCTO");

    int opcion = opcionInicial(scanner);

    switch (opcion) {
      case 1: // Crear nuevo producto
        agregarProducto(scanner, productos);
        break;
      case 2: // [Testing] Cargar la lista con 10 productos
        cargarLista(productos);
        break;
      case 0: // Regresar
    }
  }

  private static void agregarProducto(Scanner scanner, ProductosList productos) {
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
      productos.agregarProducto(producto);
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
    } else if (tipo.equalsIgnoreCase("Comida")) {
      mensaje = String.format("Peso en gramos: %.2f gr.", pesoGramos);
    }

    return mensaje;
  }

  private static int opcionInicial(Scanner scanner) {
    System.out.println("""
        1. Crear nuevo producto
        2. [Testing] Cargar la lista con 10 productos
        0. Regresar
        """);

    return Utilidades.elijaUnaOpcion(scanner, 0, 2);
  }

  private static void cargarLista(ProductosList productos) {
    Bebida producto1 = new Bebida("Agua Mineral con Gas", 1800.00, 2,
        "Ideal para hidratación", 1.5);
    Comida producto2 = new Comida("Empanadas de Carne (unidad)", 3000.00, 30,
        "Clásico criollo horneado", 90.0);
    Bebida producto3 = new Bebida("Cerveza IPA Artesanal", 4500.00, 10,
        "Amarga y refrescante", 0.5);
    Comida producto4 = new Comida("Milanesa de Ternera con Papas Fritas", 10500.00, 8,
        "Plato principal popular", 450.0);
    Bebida producto5 = new Bebida("Jugo de Naranja Natural", 3200.00, 12,
        "Recién exprimido", 0.5);
    Comida producto6 = new Comida("Sándwich de Miga Triple", 4800.00, 25,
        "Relleno de jamón y queso", 300.0);
    Bebida producto7 = new Bebida("Vino Malbec Joven", 8900.00, 6,
        "Tinto ligero y afrutado", 0.75);
    Comida producto8 = new Comida("Pizza Muzzarella (Grande)", 14000.00, 5,
        "Con abundante queso", 750.0);
    Bebida producto9 = new Bebida("Gaseosa Limón (Marca Local)", 2600.00, 20,
        "Sabor cítrico burbujeante", 1.5);
    Comida producto10 = new Comida("Alfajor de Maicena", 1500.00, 40,
        "Dulce de leche y coco", 100.0);

    productos.agregarProducto(producto1);
    productos.agregarProducto(producto2);
    productos.agregarProducto(producto3);
    productos.agregarProducto(producto4);
    productos.agregarProducto(producto5);
    productos.agregarProducto(producto6);
    productos.agregarProducto(producto7);
    productos.agregarProducto(producto8);
    productos.agregarProducto(producto9);
    productos.agregarProducto(producto10);

    System.out.println("\nSe han agregado 10 productos para probar funcionalidades.");
  }
}
