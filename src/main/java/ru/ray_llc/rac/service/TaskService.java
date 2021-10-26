package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import static ru.ray_llc.rac.util.UserUtil.URL_APPL;
import static ru.ray_llc.rac.util.UserUtil.fromTo;
import static ru.ray_llc.rac.util.ValidationUtil.checkNotFoundWithId;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.repository.TaskRepository;
import ru.ray_llc.rac.to.TaskIntegrationTo;
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
    try {
      response = requestService.postRequest(params, URL_APPL+"application");
      respCode = response.getResponseCode();

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(respCode + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
    return taskTo;
  }

  @Transactional
  public void delete(int id) {
    Assert.notNull(id, "Id must not be null");
    int respCode = 0;
    try {
      HttpURLConnection conn = requestService.deletePUTRequest(URL_APPL+"application/close/"+id);
      respCode = conn.getResponseCode();

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(respCode + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
  }

  public Task get(int id) {
    return checkNotFoundWithId(repository.get(id), id);
  }

  //надо подменить источник на Rac-appl
  public List<Task> getAll() {
    List<TaskIntegrationTo> tasks;
    int respCode = 0;
    try {
      HttpURLConnection conn = requestService.getRequest(URL_APPL+"application/list/ACTIVE");
      respCode = conn.getResponseCode();

      String responseLine;
      StringBuilder response = new StringBuilder();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
      while ((responseLine = bufferedReader.readLine()) != null) {
        response.append(responseLine);
      }
      bufferedReader.close();

      System.out.println(response.substring(response.toString().indexOf('['), response.toString().lastIndexOf(']') + 1));

      ObjectMapper mapper = new ObjectMapper();
      tasks = mapper.readValue(response.substring(response.toString().indexOf('['), response.toString().lastIndexOf(']') + 1),
          new TypeReference<List<TaskIntegrationTo>>() {
          });
    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(
            "Код ошибки " + HttpStatus.valueOf(respCode).value() + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
    return tasks.stream().map(task -> fromTo(task)).collect(Collectors.toList());
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
