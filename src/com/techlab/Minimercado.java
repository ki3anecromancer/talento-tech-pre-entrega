package com.techlab;

import com.techlab.pedidos.Pedidos;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class Minimercado {

  private Scanner scanner = new Scanner(System.in);
  private ProductosList productos;
  private Pedidos pedidos;

  public Minimercado() {
    productos = new ProductosList();
    pedidos = new Pedidos();
  }

  public void abrir() {
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
