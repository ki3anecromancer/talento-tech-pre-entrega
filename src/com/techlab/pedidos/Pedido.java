package com.techlab.pedidos;

import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

  Integer id;
  List<Producto> productos;

  public Pedido() {
    this.productos = new ArrayList<>();
  }

  // TODO: agregar metodos
}
