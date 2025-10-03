package com.techlab.productos;

public class Producto {

  // Tamaño de la columna en consola
  private int volumenMaxAncho = 12;

  private static int contadorId = 1; // Único y auto-incremental
  private int id;
  private String nombre;
  private double precio;
  private String descripcion; // opcional
  private int stock;

  public Producto(String nombre, double precio, int stock, String descripcion) {
    id = contadorId;
    contadorId++; // autoincremental cada vez que se genera un nuevo producto
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
    this.descripcion = descripcion;
  }

  private int calcularAncho(int maxAncho, int nuevoAncho) {
    return nuevoAncho > maxAncho - 5 ? nuevoAncho + 5 : maxAncho;
  }

  public void calcularVolumenancho(int nuevoAncho) {
    volumenMaxAncho = calcularAncho(volumenMaxAncho, nuevoAncho);
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getVolumenMaxAncho() {
    return volumenMaxAncho;
  }
}
