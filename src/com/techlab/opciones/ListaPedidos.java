package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.pedidos.Pedido;

public class ListaPedidos {

  private ListaPedidos() {
  }

  public static void ejecutar(Pedido pedido) {
    Utilidades.mostrarCartel(30, "LISTA DE PEDIDOS");
  }
}
