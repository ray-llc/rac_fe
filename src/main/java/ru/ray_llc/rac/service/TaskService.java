package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.ValidationUtil.checkNotFoundWithId;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.repository.TaskRepository;
import ru.ray_llc.rac.to.TaskTo;
import ru.ray_llc.rac.util.exception.IllegalRequestDataException;

@Service
@Transactional(readOnly = true)
public class TaskService {

  private final TaskRepository repository;

  @Autowired
  private RequestService requestService;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Task create(Task task) {
    Assert.notNull(task, "Task must not be null");
    return repository.save(task);
  }

  public TaskTo sendPostRequest(TaskTo taskTo) {
    Assert.notNull(taskTo, "Task must not be null");
    List<String> params = new ArrayList<>();
    params.add(taskTo.toStringForPost());
    HttpURLConnection response;
    Integer respCode = 0;
    String respMessage = "";
    try{
      response = requestService.PostRequest(params, "http://localhost:8081/api/application");
      respCode = response.getResponseCode();
      respMessage = response.getResponseMessage();

    } catch ( IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    }finally {
      if(respCode >= 300) {
        throw new IllegalRequestDataException(respMessage);
      }
    }
    return taskTo;
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
