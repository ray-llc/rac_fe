package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.ray_llc.rac.model.GeoLocation;
import ru.ray_llc.rac.util.TaskDeserialezer;

@Getter
@Setter
//@JsonDeserialize(using = TaskDeserialezer.class)
public class TaskIntegrationTo extends BaseTo implements Serializable{
//  @Serial
//  private static final long serialVersionUID = 442124242332L;

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

  public TaskIntegrationTo() {
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
