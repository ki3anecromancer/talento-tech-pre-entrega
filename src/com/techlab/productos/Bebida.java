package com.techlab.productos;

public class Bebida extends Producto {

  Double volumenLitros;

  public Bebida(String nombre, Double precio, int stock, String descripcion, double volumenLitros) {
    super(nombre, precio, stock, descripcion);
    this.volumenLitros = volumenLitros;

    calcularVolumenancho(Integer.toString((int) volumenLitros).length() + 3);
  }

  public Double getVolumenLitros() {
    return volumenLitros;
  }

  public void setVolumenLitros(Double volumenLitros) {
    this.volumenLitros = volumenLitros;
  }

  @Override
  public String toString() {
    return String.format(
        "Tipo: Bebida%nNombre: %s%nVolumen: %.2f L.%nPrecio: $%.2f%nStock: %d%nDescripción: %s",
        getNombre(), volumenLitros, getPrecio(), getStock(), getDescripcion());
  }
}
