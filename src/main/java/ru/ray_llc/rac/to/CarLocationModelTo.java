package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarLocationModelTo implements Serializable {

//"{"timeTrack":"07-02-2022 04:10:01","location":{"latitude":45.116387,"longitude":38.991683}}"
  /**
   * Время определения местоположения
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date timeTrack;
  /**
   * Геопозиция объекта.
   */
  private Location location;

  public CarLocationModelTo(Date timeTrack, Location location) {
    this.timeTrack = timeTrack;
    this.location = location;
  }

  public CarLocationModelTo() {
  }

  @Override
  public String toString() {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
    return "{" +
        "timeTrack:" + timeTrack +
        ", location:" + location.toString() +
        '}';
  }

}
