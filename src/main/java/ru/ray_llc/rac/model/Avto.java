package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avto extends  AbstractNamedEntity{

  private String plate;

  private Double mileage;

  private Double engine_hours;

  public Avto() {}
}
