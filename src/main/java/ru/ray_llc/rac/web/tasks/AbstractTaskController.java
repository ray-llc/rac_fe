package ru.ray_llc.rac.web.tasks;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.UserUtil.fromTo;
import static ru.ray_llc.rac.util.ValidationUtil.assureIdConsistent;
import static ru.ray_llc.rac.util.ValidationUtil.checkNew;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.service.TaskService;
import ru.ray_llc.rac.to.TaskTo;
import ru.ray_llc.rac.web.SecurityUtil;

public abstract class AbstractTaskController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private TaskService service;

  public Task get(int id) {
    int userId = SecurityUtil.authUserId();
    log.info("get equipment {} for user {}", id, userId);
    return service.get(id);
  }

  public void delete(int id) {
    int userId = SecurityUtil.authUserId();
    log.info("delete Task {} for user {}", id, userId);
    service.delete(id);
  }

  public List<Task> getAll() {
    int userId = SecurityUtil.authUserId();
    log.info("getAll for user {}", userId);
    return service.getAll();
  }

  public Task create(TaskTo task) {
    int userId = SecurityUtil.authUserId();
    log.info("create {} for user {}", task, userId);
    checkNew(task);
    return service.create(fromTo(task));
  }

  public void update(TaskTo task, int id) {
    int userId = SecurityUtil.authUserId();
    log.info("update {} for user {}", task, userId);
    assureIdConsistent(task, id);
    service.update(fromTo(task));
  }
}
