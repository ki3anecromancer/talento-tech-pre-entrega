package com.techlab.productos;

public class Producto {

  // Tamaño de la columna en consola
  private int volumenMaxAncho = 12;

  // Ancho de cada atributo
  private int anchoId;
  private int anchoNombre;
  private int anchoPrecio;
  private int anchoStock;

  private static int contadorId = 1; // Único y auto-incremental, simula Base de Datos
  private int id;
  private String nombre;
  private double precio;
  private String descripcion; // opcional
  private int stock;
  private String tipo;

  public Producto(String nombre, double precio, int stock, String descripcion, String tipo) {
    id = contadorId;
    contadorId++; // autoincremental cada vez que se genera un nuevo producto
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
    this.descripcion = descripcion;
    this.tipo = tipo;

    // Definir los anchos
    anchoId = anchoInt(id);
    anchoNombre = nombre.length();
    anchoPrecio = anchoDouble(precio, 3);
    anchoStock = anchoInt(stock);
  }

  private int calcularAncho(int maxAncho, int nuevoAncho) {
    return nuevoAncho > maxAncho - 5 ? nuevoAncho + 5 : maxAncho;
  }

  public void calcularVolumenancho(int nuevoAncho) {
    volumenMaxAncho = calcularAncho(volumenMaxAncho, nuevoAncho);
  }

  private int anchoInt(int intConvertir) {
    return Integer.toString(intConvertir).length();
  }

  private int anchoDouble(double doubleConvertir, int espacioExtra) {
    return Integer.toString((int) doubleConvertir).length() + espacioExtra;
  }

  public void setIdReducirContador(int id) {
    this.id = id;
    contadorId--;
  }

  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
    anchoNombre = nombre.length();
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
    anchoPrecio = anchoDouble(precio, 3);
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
    anchoStock = anchoInt(stock);
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

  public double getPrecioTotal() {
    return stock * precio;
  }

  public String getTipo() {
    return tipo;
  }

  // Getters de ancho
  public int getAnchoId() {
    return anchoId;
  }

  public int getAnchoNombre() {
    return anchoNombre;
  }

  public int getAnchoPrecio() {
    return anchoPrecio;
  }

  public int getAnchoStock() {
    return anchoStock;
  }
}
