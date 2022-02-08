package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avto extends AbstractNamedEntity {

  private String plate;

  private Double mileage;

  private Double engine_hours;

  //  @JsonIgnore
  private GeoLocation geoLocation;

  public Avto() {
  }

  public Avto(Integer id, String name, String plate, Double mileage, Double engine_hours, GeoLocation geoLocation) {
    super(id, name);
    this.plate = plate;
    this.mileage = mileage;
    this.engine_hours = engine_hours;
    this.geoLocation = geoLocation;
  }

}
