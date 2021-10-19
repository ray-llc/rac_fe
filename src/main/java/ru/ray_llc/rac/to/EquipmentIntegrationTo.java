package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.ray_llc.rac.model.GeoLocation;

@Getter
@Setter
public class EquipmentIntegrationTo extends  BaseTo implements Serializable {

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotBlank
  @Size(max = 255)
  private String ipAddress;

  @NotNull
  private boolean open;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date timeOpen;

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotNull
  GeoLocation geoLocation;

  public EquipmentIntegrationTo() {
  }

  public EquipmentIntegrationTo(Integer id, String name, String ipAddress, boolean open, Date timeOpen, String address, GeoLocation geoLocation) {
    super(id);
    this.name = name;
    this.ipAddress = ipAddress;
    this.open = open;
    this.timeOpen = timeOpen;
    this.address = address;
    this.geoLocation = geoLocation;
  }

  @Override
  public String toString() {
    return "{" +
        "id:" + id +
        ", name:" + name +
        ", ipAddress:" + ipAddress +
        ", open:" + (Boolean.TRUE == open ? "true" : "false") +
        ", timeOpen:" + timeOpen.toString() +
        ", address:" + address +
        ", geoLocation:" + geoLocation.toString() +
        '}';
  }

}
