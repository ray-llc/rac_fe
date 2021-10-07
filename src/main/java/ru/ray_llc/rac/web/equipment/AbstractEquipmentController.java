package ru.ray_llc.rac.web.equipment;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.ValidationUtil.assureIdConsistent;
import static ru.ray_llc.rac.util.ValidationUtil.checkNew;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ray_llc.rac.model.Equipment;
import ru.ray_llc.rac.service.EquipmentService;
import ru.ray_llc.rac.service.TaskService;
import ru.ray_llc.rac.web.SecurityUtil;

public abstract class AbstractEquipmentController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private EquipmentService service;


  public Equipment get(int id) {
    int userId = SecurityUtil.authUserId();
    log.info("get equipment {} for user {}", id, userId);
    return service.get(id);
  }

  public void delete(int id) {
    int userId = SecurityUtil.authUserId();
    log.info("delete equipment {} for user {}", id, userId);
    service.delete(id);
  }

  public List<Equipment> getAll() {
    int userId = SecurityUtil.authUserId();
    log.info("getAll for user {}", userId);
    return service.getAll();
//    return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
  }

  public Equipment create(Equipment equipment) {
    int userId = SecurityUtil.authUserId();
    log.info("create {} for user {}", equipment, userId);
    checkNew(equipment);
    return service.create(equipment);
  }

  public void update(Equipment equipment, int id) {
    int userId = SecurityUtil.authUserId();
    log.info("update {} for user {}", equipment, userId);
    assureIdConsistent(equipment, id);
    service.update(equipment);
  }

  public List<Equipment> getFilter(
      @RequestParam @Nullable String ipAddress,
      @RequestParam @Nullable String address) {
    int userId = SecurityUtil.authUserId();
    log.info("getBetween ipAddress {},  Address {} for user {}", ipAddress, address, userId);

    List<Equipment> equipmentsDateFiltered = service.getFilter(ipAddress, address);
    return equipmentsDateFiltered;
  }

  public void enable(int id, boolean enabled) {
    log.info(enabled ? "enable {}" : "disable {}", id);
    service.enable(id, enabled);
  }

  public void openOrClose(int id, boolean setAction) {
    log.info(setAction ? "open {}" : "close {}", id);
    service.sendOpenClosePostRequest(id, setAction);
  }

}
