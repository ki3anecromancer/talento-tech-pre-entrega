package com.techlab.excepciones;

public class IntegerInvalidoException extends RuntimeException{

  public IntegerInvalidoException() {
    super();
  }

  public IntegerInvalidoException(String mensaje) {
    super(mensaje);
  }
}
