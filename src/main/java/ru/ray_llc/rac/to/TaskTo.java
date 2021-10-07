package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskTo   extends BaseTo implements Serializable {

  @Serial
  private static final long serialVersionUID = 4421343332L;

  private Double longitude;

  private Double latitude;

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotBlank
  @Size(max = 255)
  private String phone;

  @NotBlank
  @Size(max = 255)
  private String number_auto;

  private boolean enabled = true;

  @NotNull
  private Date registered = new Date();

  @NotBlank
  @Size(min = 2, max = 100)
  protected String name = new Date().toString();

  public TaskTo() {
  }

  public TaskTo(Double longitude, Double latitude, String address, String phone,
      String number_auto) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.address = address;
    this.phone = phone;
    this.number_auto = number_auto;
  }

  @Override
  public String toString() {
    return "TaskTo{" +
        "id=" + id +
        ", address=" + address +
        ", phone=" + phone +
        ", number_auto=" + number_auto +
        ", longitude=" + longitude.toString() +
        ", latitude=" + latitude.toString() +
        ", enabled=" + enabled +
        ", name=" + name +
        '}';
  }

  public String toStringForPost() {
  return "{"+
      "\"address\":\"" + address +"\""+
      ", \"phone\":\"" + phone +"\""+
      ", \"numberAuto\":\"" + number_auto +"\""+
      ", \"geoLocation\": {\"longitude\":" + longitude.toString() +
      ", \"latitude\":" + latitude.toString() +
      "}}";
  }
}
