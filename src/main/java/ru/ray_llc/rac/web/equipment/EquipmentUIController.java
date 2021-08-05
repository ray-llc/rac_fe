package ru.ray_llc.rac.web.equipment;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ray_llc.rac.View;
import ru.ray_llc.rac.model.Equipment;
import ru.ray_llc.rac.util.ValidationUtil;

@RestController
@RequestMapping(value = "/profile/barriers", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentUIController extends AbstractEquipmentController{

  @Override
  @GetMapping
  @JsonView(View.JsonUI.class)
  public List<Equipment> getAll() {
    return super.getAll();
  }

  @Override
  @GetMapping( "/{id}")
  @JsonView(View.JsonUI.class)
  public Equipment get(@PathVariable int id) {
    return super.get(id);
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable int id) {
    super.delete(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> createOrUpdate(Equipment equipment, BindingResult result) {
    if (result.hasErrors()) {
      return ValidationUtil.getErrorResponse(result);
    }
    if (equipment.isNew()) {
      super.create(equipment);
    } else {
      super.update(equipment, equipment.getId());
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping("/filter")
  @JsonView(View.JsonUI.class)
  public List<Equipment> getFilter(
      @RequestParam("ip_address")  @Nullable String ipAddress,
      @RequestParam("address")  @Nullable String address){

  return super.getFilter(ipAddress, address);
  }

}
