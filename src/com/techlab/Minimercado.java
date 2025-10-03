package com.techlab;

import com.techlab.pedidos.Pedido;
import com.techlab.productos.Bebida;
import com.techlab.productos.Comida;
import com.techlab.productos.ProductosList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Minimercado {

  private Scanner scanner = new Scanner(System.in);
  private ProductosList productos;
  private List<Pedido> pedidos;

  public Minimercado() {
    productos = new ProductosList();
    pedidos = new ArrayList<>();
  }

  public void abrir() {

    // TODO: ELIMINAR EJEMPLOS DE PRUEBA
//    Bebida bebida = new Bebida(
//        "Coca-Cola Zero", 4.00, 5, "", 1.25);
//
//    Comida comida = new Comida(
//        "Nachos con Queso Frito", 6.99, 15, "", 0.9);
//
//    Comida comida2 = new Comida(
//        "Comida con un nombre bastante largo", 123456789.12, 1234567890,
//        "Comida larga", 1234567890.12);
//
//    productos.agregarProducto(bebida);
//    productos.agregarProducto(comida);
//    productos.agregarProducto(comida2);
    // FIN EJEMPLOS DE PRUEBA

    int opcion;

    while (true) {
      // Título de la tienda
      Utilidades.mostrarCartel(30, "MINIMERCADO");

      // Mostrar el menú con las opciones principales
      Utilidades.mostrarMenu();

      // Elegir una opción válida
      opcion = Utilidades.elijaUnaOpcion(scanner, 0, 6);

      // Ejecutar métodos según la opción
      Utilidades.ejecutarOpcion(opcion, scanner, productos, pedidos);

      // Si la opción es 0, finalizar el programa
      if (opcion == 0) {
        break;
      }
    }
  }
}
