package com.techlab;

import com.techlab.productos.ProductosList;

public class ListaProductos {

  private ListaProductos() {}

  public static void ejecutar(ProductosList productos) {
    Utilidades.mostrarCartel(30, "LISTA DE PRODUCTOS");

    // Mostrar datos en forma de tabla
    productos.mostrarListaProductos(true, false, true, true, true, true, true);
  }
}
