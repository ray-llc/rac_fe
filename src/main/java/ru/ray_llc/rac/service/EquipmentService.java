package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.model.Equipment;
import ru.ray_llc.rac.repository.EquipmentRepository;

@Service
@Transactional(readOnly = true)
public class EquipmentService {

  private final EquipmentRepository repository;

  public EquipmentService(EquipmentRepository repository) {
    this.repository = repository;
  }

  @Transactional
  @CacheEvict(value = "equipments", allEntries = true)
  public Equipment create(Equipment equipment){
    Assert.notNull(equipment, "Equipment must not be null");
    return repository.save(equipment);
  }

  @Transactional
  @CacheEvict(value = "equipments", allEntries = true)
  public void delete(int id) {
    checkNotFoundWithId(repository.delete(id), id);
  }

  public Equipment get(int id) {
    return checkNotFoundWithId(repository.get(id), id);
  }

  @Cacheable("equipments")
  public List<Equipment> getAll() {
    return repository.getAll();
  }

  @CacheEvict(value = "equipments", allEntries = true)
  @Transactional
  public void update(Equipment equipment) {
    Assert.notNull(equipment, "equipment must not be null");
    repository.save(equipment);
  }


  @CacheEvict(value = "equipments", allEntries = true)
  @Transactional
  public void enable(int id, boolean enabled) {
    Equipment equipment = get(id);
    equipment.setEnabled(enabled);
    repository.save(equipment);
  }

  public List<Equipment> getFilter(String ipAddress, String address) {
    ipAddress = ipAddress == null ? "%%" : "%"+ipAddress+"%";
    address = address == null ? "%%" : "%"+address+"%";
    return repository.getFilter(ipAddress, address);
  }
}
