package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvtoTo extends BaseTo implements Serializable{

  @NotBlank
  @Size(min = 2, max = 100)
  protected String name = "RU22w";

  @NotBlank
  @Size(min = 2, max = 100)
  private String plate;

  @NotBlank
  private Double mileage;

  @NotBlank
  private Double engine_hours;

  public AvtoTo() {}

}
