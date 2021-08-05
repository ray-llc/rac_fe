package ru.ray_llc.rac.util.exception;

public class IllegalRequestDataException extends RuntimeException {

  public IllegalRequestDataException(String msg) {
    super(msg);
  }
}