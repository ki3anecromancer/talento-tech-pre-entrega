package com.techlab.pedidos;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductosList;

public class Pedidos {

  private ProductosList productos;

  public Pedidos() {
    this.productos = new ProductosList();
  }

  public ProductosList getProductos() {
    return productos;
  }

  public void crearPedido(Producto nuevo) {
    if (productos.existeId(nuevo.getId())) {
      int stockActual = productos.getProductoPorId(nuevo.getId()).getStock();
      int stockPedido = nuevo.getStock();
      productos.getProductoPorId(nuevo.getId()).setStock(stockActual + stockPedido);
    } else {
      productos.agregarProducto(nuevo);
    }
  }

  public void mostrarPrecioListaCompleta() {
    double total = 0;

    for (Producto producto : productos.getLista()) {
      total += producto.getPrecioTotal();
    }

    System.out.printf("$%.2f%n", total);
  }
}
