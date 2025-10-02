package com.techlab.productos;

public class Comida extends Producto {

  Double pesoGramos;

  public Comida(String nombre, Double precio, int stock, String descripcion, double pesoGramos) {
    super(nombre, precio, stock, descripcion);
    this.pesoGramos = pesoGramos;

    calcularVolumenancho(Integer.toString((int) pesoGramos).length() + 7);
  }

  public Double getPesoGramos() {
    return pesoGramos;
  }

  public void setPesoGramos(Double pesoGramos) {
    this.pesoGramos = pesoGramos;
  }

  @Override
  public String toString() {
    return String.format(
        "Tipo: Comida%nNombre: %s%nVolumen: %.2f gr.%nPrecio: $%.2f%nStock: %d%nDescripción: %s",
        getNombre(), pesoGramos, getPrecio(), getStock(), getDescripcion());
  }
}
