package com.techlab.productos;

import static com.techlab.Utilidades.armarFormato;

import java.util.ArrayList;
import java.util.List;

public class ProductosList {

  private List<Producto> productos;

  // Variables de ancho de columna para toda la lista
  private int idMaxAncho = 7;
  private int nombreMaxAncho = 11;
  private int precioMaxAncho = 10;
  private int stockMaxAncho = 10;
  private int volumenMaxAncho = 12;

  public ProductosList() {
    productos = new ArrayList<>();
  }

  public List<Producto> getLista() {
    return productos;
  }

  public void agregarProducto(Producto producto) {
    calcularAnchoAgregado(producto);
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

  private void calcularAnchoAgregado(Producto producto) {
    // Variables separadas para claridad
    int idAncho = anchoInt(producto.getId());
    int nombreAncho = producto.getNombre().length();
    int precioAncho = anchoDouble(producto.getPrecio(), 3);
    int stockAncho = anchoInt(producto.getStock());

    idMaxAncho = calcularAncho(idMaxAncho, idAncho);
    nombreMaxAncho = calcularAncho(nombreMaxAncho, nombreAncho);
    precioMaxAncho = calcularAncho(precioMaxAncho, precioAncho);
    stockMaxAncho = calcularAncho(stockMaxAncho, stockAncho);

    // El ancho del volumen ya está calculado cuando se crea el objeto
    volumenMaxAncho = producto.getVolumenMaxAncho();
  }

  private void actualizarTodosLosAnchos() {
    int idAncho = 7;
    int nombreAncho = 11;
    int precioAncho = 10;
    int stockAncho = 10;
    int volumenAncho = 12;

    int prodId;
    int prodNombre;
    int prodPrecio;
    int prodStock;
    int prodVolumen = 0;

    for (Producto producto : productos) {
      // Cargar los anchos de cada producto en su propia variable
      prodId = calcularAncho(idAncho, anchoInt(producto.getId()));
      prodNombre = calcularAncho(nombreAncho, producto.getNombre().length());
      prodPrecio = calcularAncho(precioAncho, anchoDouble(producto.getPrecio(), 3));
      prodStock = calcularAncho(stockAncho, anchoInt(producto.getStock()));
      if (producto.getClass() == Bebida.class) {
        prodVolumen = calcularAncho(volumenAncho, ((Bebida) producto).getAnchoVolumen());
      } else if (producto.getClass() == Comida.class) {
        prodVolumen = calcularAncho(volumenAncho, ((Comida) producto).getAnchoVolumen());
      }

      // Comparar e ir asignando si son mayores
      if (prodId > idAncho) {
        idAncho = prodId;
      }
      if (prodNombre > nombreAncho) {
        nombreAncho = prodNombre;
      }
      if (prodPrecio > precioAncho) {
        precioAncho = prodPrecio;
      }
      if (prodStock > stockAncho) {
        stockAncho = prodStock;
      }
      if (prodVolumen > volumenAncho) {
        volumenAncho = prodVolumen;
      }
    }

    // Actualizar los anchos máximo con los nuevos valores
    idMaxAncho = idAncho;
    nombreMaxAncho = nombreAncho;
    precioMaxAncho = precioAncho;
    stockMaxAncho = stockAncho;
    volumenMaxAncho = volumenAncho;
  }

  private int anchoInt(int int_convertir) {
    return Integer.toString(int_convertir).length();
  }

  private int anchoDouble(double double_convertir, int espacioExtra) {
    return Integer.toString((int) double_convertir).length() + espacioExtra;
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

  public void editarProductoPorId(
      int id, String nombre, double precio, int stock, String descripcion, double volumen) {

    // Buscar el producto en la lista y editarlo
    for (int i = 0; i < productos.size(); i++) {
      // Si se encuentra, actualizar sus valores y romper el bucle
      if (productos.get(i).getId() == id) {
        productos.get(i).setNombre(nombre);
        productos.get(i).setPrecio(precio);
        productos.get(i).setStock(stock);
        productos.get(i).setDescripcion(descripcion);

        if (productos.get(i).getClass() == Bebida.class) {
          ((Bebida) productos.get(i)).setVolumenLitros(volumen);
        } else if (productos.get(i).getClass() == Comida.class) {
          ((Comida) productos.get(i)).setPesoGramos(volumen);
        }
        break;
      }
    }

    // Actualizar todos los anchos
    actualizarTodosLosAnchos();
  }

  public void mostrarProductoPorId(int id) {
    for (int i = 0; i < productos.size(); i++) {
      if (productos.get(i).getId() == id) {
        mostrarCabeceraTabla(true, false, true, true, true, true, true);
        mostrarTabla(productos.get(i), true, false, true, true, true, true, true);
        break;
      }
    }
  }

  public void mostrarTabla(Producto producto, boolean id, boolean tipo, boolean nombre,
      boolean volumenLitros,
      boolean precio, boolean stock, boolean descripcion) {

    StringBuilder sb = new StringBuilder();

    if (id) {
      sb.append(getFormatoColumnaId(producto));
    }
    if (tipo) {
      if (producto.getClass() == Bebida.class) {
        sb.append(String.format("%-11s", "Bebida"));
      } else if (producto.getClass() == Comida.class) {
        sb.append(String.format("%-11s", "Comida"));
      }
    }
    if (nombre) {
      sb.append(getFormatoColumnaNombre(producto));
    }
    if (volumenLitros) {
      if (producto.getClass() == Bebida.class) {
        sb.append(getFormatoColumnaVolumenLitros(((Bebida) producto).getVolumenLitros()));
      } else if (producto.getClass() == Comida.class) {
        sb.append(getFormatoColumnaVolumenGramos(((Comida) producto).getPesoGramos()));
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
      sb.append(String.format("%-11s", "Tipo"));
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

  public String getFormatoColumnaVolumenGramos(double volumen) {
    return String.format("%-" + volumenMaxAncho + "s", String.format("%.2f gr.", volumen));
  }

  public String getFormatoColumnaVolumenLitros(double volumen) {
    return String.format("%-" + volumenMaxAncho + "s", String.format("%.2f L.", volumen));
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
