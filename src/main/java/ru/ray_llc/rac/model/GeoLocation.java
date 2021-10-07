package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation {
  private Double longitude;
  private Double latitude;

  public GeoLocation(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public GeoLocation() {
  }
}
