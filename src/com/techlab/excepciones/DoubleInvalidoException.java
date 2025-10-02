package com.techlab.excepciones;

public class DoubleInvalidoException extends RuntimeException {

  public DoubleInvalidoException() {
    super();
  }

  public DoubleInvalidoException(String mensaje) {
    super(mensaje);
  }
}
