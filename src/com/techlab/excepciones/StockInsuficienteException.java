package com.techlab.excepciones;

public class StockInsuficienteException extends RuntimeException{

  public StockInsuficienteException() {
    super();
  }

  public StockInsuficienteException(String mensaje) {
    super(mensaje);
  }
}
