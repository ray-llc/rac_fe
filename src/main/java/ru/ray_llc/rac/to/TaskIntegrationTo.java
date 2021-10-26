package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class TaskIntegrationTo extends BaseTo implements Serializable {

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotBlank
  @Size(max = 255)
  private String phone;

  @NotBlank
  @Size(max = 255)
  private String numberAuto;

  @NotBlank
  @Size(max = 255)
  private String status;

  @NotNull
  GeoLocation geoLocation;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date registered = new Date();

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @JsonIgnore
  private Date closed;

//    @JsonIgnore
  private Set<EquipmentIntegrationTo> gateCollection;

  public TaskIntegrationTo() {
  }

  public TaskIntegrationTo(Integer id, String address, String phone, String numberAuto, String status, GeoLocation geoLocation, Date registered, Date closed) {
    super(id);
    this.address = address;
    this.phone = phone;
    this.numberAuto = numberAuto;
    this.status = status;
    this.geoLocation = geoLocation;
    this.registered = registered;
    this.closed = closed;
  }
  public TaskIntegrationTo(Integer id, String address, String phone, String numberAuto, String status, GeoLocation geoLocation, Date registered) {
    super(id);
    this.address = address;
    this.phone = phone;
    this.numberAuto = numberAuto;
    this.status = status;
    this.geoLocation = geoLocation;
    this.registered = registered;
  }

  @Override
  public String toString() {
    return "{" +
        "id:" + id +
        ", address:" + address +
        ", phone:" + phone +
        ", numberAuto:" + numberAuto +
        ", status:" + status +
        ", geoLocation:" + geoLocation.toString() +
        ", registered:" + registered +
        '}';
  }
}
