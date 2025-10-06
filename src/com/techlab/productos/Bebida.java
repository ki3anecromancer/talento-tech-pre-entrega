package com.techlab.productos;

public class Bebida extends Producto {

  private double volumenLitros;
  private int anchoVolumen;

  public Bebida(String nombre, Double precio, int stock, String descripcion, double volumenLitros) {
    super(nombre, precio, stock, descripcion, "Bebida");
    this.volumenLitros = volumenLitros;

    anchoVolumen = Integer.toString((int) volumenLitros).length() + 6;

    calcularVolumenancho(anchoVolumen);
  }

  public Double getVolumenLitros() {
    return volumenLitros;
  }

  public void setVolumenLitros(double volumenLitros) {
    this.volumenLitros = volumenLitros;
    anchoVolumen = Integer.toString((int) volumenLitros).length() + 6;
    calcularVolumenancho(anchoVolumen);
  }

  public int getAnchoVolumen() {
    return anchoVolumen;
  }

  @Override
  public String toString() {
    return String.format(
        "Tipo: Bebida%nNombre: %s%nVolumen: %.2f L.%nPrecio: $%.2f%nStock: %d%nDescripción: %s",
        getNombre(), volumenLitros, getPrecio(), getStock(), getDescripcion());
  }
}
