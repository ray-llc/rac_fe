package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation implements Serializable {
  private Double longitude;
  private Double latitude;

  public GeoLocation(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public GeoLocation() {
  }

  @Override
  public String toString() {
    return "{" +
        "longitude:" + longitude.toString() +
        ", latitude:" + latitude.toString() +
        '}';
  }
}
