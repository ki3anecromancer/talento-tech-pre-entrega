package com.techlab.opciones;

import com.techlab.Utilidades;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.ProductosList;
import java.util.Scanner;

public class CrearPedido {

  private CrearPedido() {}

  public static void ejecutar(Scanner scanner, ProductosList productos, Pedido pedidos) {
    Utilidades.mostrarCartel(30, "CREAR PEDIDO");
  }
}
