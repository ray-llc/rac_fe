package ru.ray_llc.rac.model;

/*
 * @author Alexandr.Yakubov
 **/


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "gpio", uniqueConstraints = {@UniqueConstraint(columnNames = {"equipments_id",
    "name"}, name = "gpio_unique_equipments_name_idx")})
public class Gpio extends AbstractNamedEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "equipments_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  @NotNull
  private Equipment equipment;

  @Column(name = "direction", nullable = false)
  @NotBlank
  @Size(max = 50)
  private String direction;

  @Column(name = "trigger", nullable = false)
  @NotBlank
  @Size(max = 50)
  private String trigger;

  @Column(name = "action", nullable = false)
  @NotBlank
  @Size(max = 50)
  private String action;
  @Column(name = "parameter", nullable = false)
  @NotBlank
  @Size(max = 255)
  private String parameter;
  @Column(name = "value", nullable = false)
  @NotNull
  private int value;
  @Column(name = "debounce", nullable = false)
  @NotNull
  private int debounce;
  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  public Gpio() {
  }

  @Override
  public String toString() {
    return "Equipment{" +
        "id=" + id +
        ", name=" + name +
        ", direction=" + direction +
        ", value=" + value +
        ", trigger=" + trigger +
        ", action=" + action +
        ", parameter=" + parameter +
        ", debounce=" + debounce +
        ", enabled=" + enabled +
        '}';
  }
}
