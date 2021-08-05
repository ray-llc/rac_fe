package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.repository.TaskRepository;

@Service
@Transactional(readOnly = true)
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Task create(Task task) {
    Assert.notNull(task, "Task must not be null");
    return repository.save(task);
  }

  @Transactional
  public void delete(int id) {
    checkNotFoundWithId(repository.delete(id), id);
  }

  public Task get(int id) {
    return checkNotFoundWithId(repository.get(id), id);
  }

  public List<Task> getAll() {
    return repository.getAll();
  }

  @Transactional
  public void update(Task task) {
    Assert.notNull(task, "Task must not be null");
    repository.save(task);
  }

  @Transactional
  public void enable(int id, boolean enabled) {
    Task task = get(id);
    task.setEnabled(enabled);
    repository.save(task);
  }

}
