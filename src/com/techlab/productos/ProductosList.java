package com.techlab.productos;

import static com.techlab.Utilidades.armarFormato;

import com.techlab.Utilidades;
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

  public void eliminarProductoPorId(int id) {
    for (int i = 0; i < productos.size(); i++) {
      if (productos.get(i).getId() == id) {
        productos.remove(i);
        actualizarTodosLosAnchos();
        break;
      }
    }
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
    idMaxAncho = calcularAncho(idMaxAncho, producto.getAnchoId());
    nombreMaxAncho = calcularAncho(nombreMaxAncho, producto.getAnchoNombre());
    precioMaxAncho = calcularAncho(precioMaxAncho, producto.getAnchoPrecio());
    stockMaxAncho = calcularAncho(stockMaxAncho, producto.getAnchoStock());

    // El ancho del volumen ya está calculado cuando se crea el objeto
    volumenMaxAncho = producto.getVolumenMaxAncho();
  }

  public void actualizarTodosLosAnchos() {
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
      prodId = calcularAncho(idAncho, producto.getAnchoId());
      prodNombre = calcularAncho(nombreAncho, producto.getAnchoNombre());
      prodPrecio = calcularAncho(precioAncho, producto.getAnchoPrecio());
      prodStock = calcularAncho(stockAncho, producto.getAnchoStock());
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

  private int getIdMaxAncho() {
    return idMaxAncho;
  }

  private int getNombreMaxAncho() {
    return nombreMaxAncho;
  }

  private int getPrecioMaxAncho() {
    return precioMaxAncho;
  }

  private int getStockMaxAncho() {
    return stockMaxAncho;
  }

  private int getVolumenMaxAncho() {
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

  public void mostrarProductoPorId(int idProducto, boolean id, boolean tipo, boolean nombre,
      boolean volumen, boolean precio, boolean stock, boolean descripcion) {

      ProductosList listaProductos = new ProductosList();
      listaProductos.agregarProducto(Utilidades.buscarProductoPorId(productos, idProducto));
      listaProductos.mostrarListaProductos(true, true, true, true, true, true, true);
  }

  public void mostrarTabla(Producto producto, boolean id, boolean tipo, boolean nombre,
      boolean volumen, boolean precio, boolean stock, boolean descripcion) {

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
    if (volumen) {
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
      if (productos.isEmpty()) {
        sb.append(String.format("%-9s", "Tipo"));
      } else {
        sb.append(String.format("%-11s", "Tipo"));
      }
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

  private String getFormatoColumnaNombre(Producto producto) {
    return String.format("%-" + nombreMaxAncho + "s", producto.getNombre());
  }

  private String getFormatoColumnaVolumenGramos(double volumen) {
    return String.format("%-" + volumenMaxAncho + "s", String.format("%.2f gr.", volumen));
  }

  private String getFormatoColumnaVolumenLitros(double volumen) {
    return String.format("%-" + volumenMaxAncho + "s", String.format("%.2f L.", volumen));
  }

  private String getFormatoColumnaId(Producto producto) {
    return String.format("%-" + idMaxAncho + "d", producto.getId());
  }

  private String getFormatoColumnaPrecio(Producto producto) {
    return String.format("$%-" + precioMaxAncho + ".2f", producto.getPrecio());
  }

  private String getFormatoColumnaStock(Producto producto) {
    return String.format("%-" + stockMaxAncho + "s", producto.getStock());
  }

  public boolean existeId(int id) {
    for (int i = 0; i < productos.size(); i++) {
      if (productos.get(i).getId() == id) {
        return true;
      }
    }
    return false;
  }

  public Producto getProductoPorId(int id) {
    for (int i = 0; i < productos.size(); i++) {
      if (productos.get(i).getId() == id) {
        return productos.get(i);
      }
    }
    return null;
  }

  public boolean estaVacia() {
    return productos.isEmpty();
  }
}
