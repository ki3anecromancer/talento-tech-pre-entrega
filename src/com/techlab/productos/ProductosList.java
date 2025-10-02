package com.techlab.productos;

import static com.techlab.Utilidades.armarFormato;

import java.util.ArrayList;
import java.util.List;

public class ProductosList {

  List<Producto> productos;

  // Variables de ancho de columna para toda la lista
  private int idMaxAncho = 6;
  private int nombreMaxAncho = 10;
  private int precioMaxAncho = 10;
  private int stockMaxAncho = 10;
  private int volumenMaxAncho = 10;

  public ProductosList() {
    productos = new ArrayList<>();
  }

  public List<Producto> getLista() {
    return productos;
  }

  public void agregarProducto(Producto producto) {
    calcularTodosLosAnchos(producto);
    productos.add(producto);
  }

  public void agregarListaProductos(List<Producto> productos) {
    for (Producto producto : productos) {
      agregarProducto(producto);
    }
  }

  private int calcularAncho(int maxAncho, int nuevoAncho) {
    return nuevoAncho > maxAncho - 5 ? nuevoAncho + 5 : maxAncho;
  }

  private void calcularTodosLosAnchos(Producto producto) {
    idMaxAncho = calcularAncho(idMaxAncho, Integer.toString(producto.getId()).length());
    nombreMaxAncho = calcularAncho(nombreMaxAncho, producto.getNombre().length());
    precioMaxAncho = calcularAncho(precioMaxAncho,
        Integer.toString((int) producto.getPrecio()).length() + 3);
    stockMaxAncho = calcularAncho(stockMaxAncho, Integer.toString(producto.getStock()).length());
    volumenMaxAncho = calcularAncho(volumenMaxAncho, producto.getVolumenAncho());
  }

  public int getIdMaxAncho() {
    return idMaxAncho;
  }

  public int getNombreMaxAncho() {
    return nombreMaxAncho;
  }

  public int getPrecioMaxAncho() {
    return precioMaxAncho;
  }

  public int getStockMaxAncho() {
    return stockMaxAncho;
  }

  public int getVolumenMaxAncho() {
    return volumenMaxAncho;
  }

  public void mostrarTabla(Producto producto, boolean id, boolean tipo, boolean nombre, boolean volumenLitros,
      boolean precio, boolean stock, boolean descripcion) {

    StringBuilder sb = new StringBuilder();

    if (id) {
      sb.append(getFormatoColumnaId(producto));
    }
    if (tipo) {
      sb.append(String.format("%-10s", "Bebida"));
    }
    if (nombre) {
      sb.append(getFormatoColumnaNombre(producto));
    }
    if (volumenLitros) {
      if (producto.getClass() == Bebida.class) {
        sb.append(getFormatoColumnaVolumen(((Bebida) producto).getVolumenLitros()));
      } else if (producto.getClass() == Comida.class) {
        sb.append(getFormatoColumnaVolumen(((Comida) producto).getPesoGramos()));
      }
    }
    if (precio) {
      sb.append(getFormatoColumnaPrecio(producto));
    }
    if (stock) {
      sb.append(getFormatoColumnaStock(producto));
    }
    if (descripcion) {
      sb.append(producto.getDescripcion());
    }

    String mensaje = sb.toString();
    System.out.println(mensaje);
  }

  public void mostrarListaProductos(boolean id, boolean tipo, boolean nombre,
      boolean volumen, boolean precio, boolean stock, boolean descripcion) {

    mostrarCabeceraTabla(id, tipo, nombre, volumen, precio, stock, descripcion);

    for (Producto producto : this.getLista()) {
      mostrarTabla(producto, id, tipo, nombre, volumen, precio, stock, descripcion);
    }
  }

  public void mostrarCabeceraTabla(boolean id, boolean tipo, boolean nombre,
      boolean pesoGramos, boolean precio, boolean stock, boolean descripcion) {

    StringBuilder sb = new StringBuilder();
    if (id) {
      sb.append(String.format(armarFormato(getIdMaxAncho(), "s"), "ID"));
    }
    if (tipo) {
      sb.append(String.format("%-10s", "Tipo"));
    }
    if (nombre) {
      sb.append(String.format(armarFormato(getNombreMaxAncho(), "s"), "Nombre"));
    }
    if (pesoGramos) {
      sb.append(String.format(armarFormato(getVolumenMaxAncho(), "s"), "Volumen"));
    }
    if (precio) {
      // Sumar 1 espacio por el char $
      sb.append(String.format(armarFormato(getPrecioMaxAncho() + 1, "s"), "Precio"));
    }
    if (stock) {
      sb.append(String.format(armarFormato(getStockMaxAncho(), "s"), "Stock"));
    }
    if (descripcion) {
      sb.append("Descripción");
    }

    String cabecera = sb.toString();

    System.out.println(cabecera);
  }

  public String getFormatoColumnaNombre(Producto producto) {
    return String.format("%-" + nombreMaxAncho + "s", producto.getNombre());
  }

  public String getFormatoColumnaVolumen(double volumen) {
    return String.format("%-" + volumenMaxAncho + "s", String.format("%.2f gr.", volumen));
  }

  public String getFormatoColumnaId(Producto producto) {
    return String.format("%-" + idMaxAncho + "d", producto.getId());
  }

  public String getFormatoColumnaPrecio(Producto producto) {
    return String.format("$%-" + precioMaxAncho + ".2f", producto.getPrecio());
  }

  public String getFormatoColumnaStock(Producto producto) {
    return String.format("%-" + stockMaxAncho + "s", producto.getStock());
  }
}
