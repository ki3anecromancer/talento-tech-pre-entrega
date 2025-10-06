package com.techlab.productos;

public class Comida extends Producto {

  private double pesoGramos;
  private int anchoVolumen;

  public Comida(String nombre, Double precio, int stock, String descripcion, double pesoGramos) {
    super(nombre, precio, stock, descripcion, "Comida");
    this.pesoGramos = pesoGramos;

    anchoVolumen = Integer.toString((int) pesoGramos).length() + 7;

    calcularVolumenancho(anchoVolumen);
  }

  public double getPesoGramos() {
    return pesoGramos;
  }

  public void setPesoGramos(double pesoGramos) {
    this.pesoGramos = pesoGramos;
    anchoVolumen = Integer.toString((int) pesoGramos).length() + 7;
    calcularVolumenancho(anchoVolumen);
  }

  public int getAnchoVolumen() {
    return anchoVolumen;
  }

  @Override
  public String toString() {
    return String.format(
        "Tipo: Comida%nNombre: %s%nVolumen: %.2f gr.%nPrecio: $%.2f%nStock: %d%nDescripción: %s",
        getNombre(), pesoGramos, getPrecio(), getStock(), getDescripcion());
  }
}
