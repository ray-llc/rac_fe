package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Location {
  /**
   * Широта.
   */
  private Double latitude;

  /**
   * Долгота.
   */
  private Double longitude;

  @Override
  public String toString() {
    return "{" +
        "longitude:" + longitude +
        ", latitude:" + latitude +
        '}';
  }
}
