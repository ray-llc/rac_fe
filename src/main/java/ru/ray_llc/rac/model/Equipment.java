package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.ray_llc.rac.View;

@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
    @NamedQuery(name = Equipment.DELETE, query = "DELETE FROM Equipment u WHERE u.id=:id"),
    @NamedQuery(name = Equipment.BY_IP, query = "SELECT DISTINCT u FROM Equipment u LEFT JOIN FETCH u.gpio WHERE u.ip_address=?1"),
    @NamedQuery(name = Equipment.ALL_SORTED, query = "SELECT u FROM Equipment u ORDER BY u.description"),
})
@Entity
@Table(name = "equipments", uniqueConstraints = {
    @UniqueConstraint(columnNames = "ip_address", name = "equipments_unique_ip_address_idx")})
public class Equipment extends AbstractNamedEntity {

  public static final String DELETE = "Equipment.delete";
  public static final String BY_IP = "Equipment.getByIp";
  public static final String ALL_SORTED = "Equipment.getAllSorted";
  @Column(name = "address", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String address;

  @Column(name = "ip_address", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String ip_address;

  @Column(name = "longitude", nullable = false)
  private Double longitude;

  @Column(name = "latitude", nullable = false)
  private Double latitude;

  @Column(name = "description", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String description;

  @Column(name = "state", nullable = false, columnDefinition = "bool default true")
  @JsonView(View.JsonUI.class)
  private boolean state;

  @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
  @NotNull
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Date registered = new Date();

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "equipment")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//  @OrderBy("name DESC")
  @JsonManagedReference
  private Set<Gpio> gpio;

  public Equipment() {
  }

  @Override
  public String toString() {
    return "Equipment{" +
        "id=" + id +
        ", name=" + name +
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
