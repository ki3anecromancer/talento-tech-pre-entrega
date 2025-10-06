package com.techlab.excepciones;

public class NombreInvalidoException extends RuntimeException {

  public NombreInvalidoException() {
    super();
  }

  public NombreInvalidoException(String mensaje) {
    super(mensaje);
  }
}
