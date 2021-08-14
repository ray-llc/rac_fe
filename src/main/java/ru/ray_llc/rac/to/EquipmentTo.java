package ru.ray_llc.rac.to;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.ray_llc.rac.model.Gpio;

@Getter
@Setter
public class EquipmentTo  extends BaseTo implements Serializable {
  @Serial
  private static final long serialVersionUID = 121312322L;

  @NotBlank
  @Size(min = 2, max = 100)
  protected String name = "RU22w";

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotBlank
  @Size(max = 255)
  private String ip_address;

  private Double longitude;

  private Double latitude;

  @NotBlank
  @Size(max = 255)
  private String description;

  private boolean state;

  private boolean enabled;

  private Set<Gpio> gpio;

  public EquipmentTo(String name, String address, String ip_address, Double longitude, Double latitude,
      String description, boolean state, boolean enabled,
      Set<Gpio> gpio) {
    this.name = name;
    this.address = address;
    this.ip_address = ip_address;
    this.longitude = longitude;
    this.latitude = latitude;
    this.description = description;
    this.state = state;
    this.enabled = enabled;
    this.gpio = gpio;
  }


  @Override
  public String toString() {
    return "EquipmentTo{" +
        ", address=" + address +
        ", ip_address=" + ip_address +
        ", longitude=" + longitude.toString() +
        ", latitude=" + latitude.toString() +
        ", state=" + state +
        ", enabled=" + enabled +
        ", description=" + description +
        ", gpio=" + gpio +
        '}';
  }
}
