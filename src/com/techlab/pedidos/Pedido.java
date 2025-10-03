package com.techlab.pedidos;

import com.techlab.productos.ProductosList;

public class Pedido {

  private ProductosList productos;

  public Pedido() {
    this.productos = new ProductosList();
  }

  public ProductosList getProductos() {
    return productos;
  }
}
