package ru.ray_llc.rac.repository.datajpa;

/*
 * @author Alexandr.Yakubov
 **/

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.repository.TaskRepository;

@Repository
@Transactional(readOnly = true)
public class DataJpaTaskRepository implements TaskRepository {

  private static final Sort SORT_REGISTERED = Sort.by(Direction.DESC, "registered");

  @Autowired
  private CrudTaskRepository crudRepository;

  @Override
  public Task save(Task task) {
    return crudRepository.save(task);
  }

  @Override
  public boolean delete(int id) {
    return crudRepository.delete(id) != 0;
  }

  @Override
  public Task get(int id) {
    return crudRepository.findById(id).orElse(null);
  }

  @Override
  public List<Task> getAll() {
    return crudRepository.findAll(SORT_REGISTERED);
  }
}
