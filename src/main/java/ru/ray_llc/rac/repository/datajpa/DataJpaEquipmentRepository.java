package ru.ray_llc.rac.repository.datajpa;

/*
 * @author Alexandr.Yakubov
 **/

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ray_llc.rac.model.Equipment;
import ru.ray_llc.rac.repository.EquipmentRepository;

@Repository
@Transactional(readOnly = true)
public class DataJpaEquipmentRepository implements EquipmentRepository {

  private static final Sort SORT_NAME_IP = Sort.by(Sort.Direction.ASC, "ip_address");

  @Autowired
  private final CrudEquipmentRepository crudRepository;

  public DataJpaEquipmentRepository(CrudEquipmentRepository crudRepository) {
    this.crudRepository = crudRepository;
  }

  @Override
  public Equipment save(Equipment equipment) {
    return crudRepository.save(equipment);
  }

  @Override
  public boolean delete(int id) {
    return crudRepository.delete(id) != 0;
  }

  @Override
  public Equipment get(int id) {
    return crudRepository.findById(id).orElse(null);
  }

  @Override
  public Equipment getByIpAddress(String ip) {
    return crudRepository.getByIpAddress(ip);
  }

  @Override
  public List<Equipment> getAll() {
    return crudRepository.findAll();
  }

  @Override
  public List<Equipment> getFilter(String ipAddress, String address) {
    return crudRepository.getFilter(ipAddress, address);
  }
}
