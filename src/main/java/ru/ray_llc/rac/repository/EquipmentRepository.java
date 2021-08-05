package ru.ray_llc.rac.repository;

/*
 * @author Alexandr.Yakubov
 **/

import java.util.List;
import ru.ray_llc.rac.model.Equipment;

public interface EquipmentRepository {

  // null if not found, when updated
  Equipment save(Equipment equipment);

  // false if not found
  boolean delete(int id);

  // null if not found
  Equipment get(int id);

  // null if not found
  Equipment getByIpAddress(String ip);

  List<Equipment> getAll();

  List<Equipment> getFilter(String ipAddress, String address);
}
