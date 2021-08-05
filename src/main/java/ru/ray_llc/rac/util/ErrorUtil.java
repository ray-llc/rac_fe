package ru.ray_llc.rac.util;

/*
 * @autor Alexandr.Yakubov
 **/

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorUtil {

  private ErrorUtil() {
  }

  public static ResponseEntity<String> getStringResponseEntity(BindingResult result) {
    if (result.hasErrors()) {
      String errorFieldsMsg = result.getFieldErrors().stream()
          .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
          .collect(Collectors.joining("<br>"));
      return ResponseEntity.unprocessableEntity().body(errorFieldsMsg);
    }
    return null;
  }
}
