package ru.ray_llc.rac.repository;

/*
 * @author Alexandr.Yakubov
 **/

import java.util.List;
import ru.ray_llc.rac.model.Task;

public interface TaskRepository {
  // null if not found, when updated
  Task save(Task task);

  // false if not found
  boolean delete(int id);

  // null if not found
  Task get(int id);

  List<Task> getAll();

}
