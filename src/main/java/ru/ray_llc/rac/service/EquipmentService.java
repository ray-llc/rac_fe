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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ray_llc.rac.model.Equipment;
import ru.ray_llc.rac.repository.EquipmentRepository;
import ru.ray_llc.rac.to.EquipmentIntegrationTo;
import ru.ray_llc.rac.util.exception.IllegalRequestDataException;

@Service
@Transactional(readOnly = true)
public class EquipmentService {

  private final EquipmentRepository repository;

  @Autowired
  private RequestService requestService;

  public EquipmentService(EquipmentRepository repository) {
    this.repository = repository;
  }

  @Transactional
  @CacheEvict(value = "equipments", allEntries = true)
  public Equipment create(Equipment equipment) {
    Assert.notNull(equipment, "Equipment must not be null");

    List<String> params = new ArrayList<>();
    params.add(equipment.toStringForPost());
    HttpURLConnection response;
    Integer respCode = 0;
    try {
      response = requestService.postRequest(params, URL_APPL + "gate?diameter=200");
      respCode = response.getResponseCode();

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(respCode + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
    return equipment;
//    return repository.save(equipment);
  }

  @Transactional
  @CacheEvict(value = "equipments", allEntries = true)
  public void delete(int id) {
    //меняем на запрос к сервису
//    checkNotFoundWithId(repository.delete(id), id);
    Assert.notNull(id, "Id must not be null");
    int respCode = 0;
    try {
      HttpURLConnection conn = requestService.deleteRequest(URL_APPL+"gate/"+id);
      respCode = conn.getResponseCode();

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(respCode + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
  }

  public Equipment get(int id) {

    EquipmentIntegrationTo equipments;
    int respCode = 0;
    try {
      HttpURLConnection conn = requestService.getRequest(URL_APPL + "gate/" + id);
      respCode = conn.getResponseCode();

      String responseLine;
      StringBuilder response = new StringBuilder();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
      while ((responseLine = bufferedReader.readLine()) != null) {
        response.append(responseLine);
      }
      bufferedReader.close();

      System.out.println(response);

      ObjectMapper mapper = new ObjectMapper();
      equipments = mapper.readValue(response.toString(), EquipmentIntegrationTo.class);
    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(
            "Код ошибки " + HttpStatus.valueOf(respCode).value() + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
    return checkNotFoundWithId(fromTo(equipments), id);
  }

  //надо подменить источник на Rac-appl
  @Cacheable("equipments")
  public List<Equipment> getAll() {

    List<EquipmentIntegrationTo> equipments;
    int respCode = 0;
    try {
      HttpURLConnection conn = requestService.getRequest(URL_APPL + "gate/list");
      respCode = conn.getResponseCode();

      String responseLine;
      StringBuilder response = new StringBuilder();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
      while ((responseLine = bufferedReader.readLine()) != null) {
        response.append(responseLine);
      }
      bufferedReader.close();

      ObjectMapper mapper = new ObjectMapper();
      equipments = mapper.readValue(response.substring(response.toString().indexOf('['), response.toString().lastIndexOf(']') + 1),
          new TypeReference<List<EquipmentIntegrationTo>>() {
          });
    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(
            "Код ошибки " + HttpStatus.valueOf(respCode).value() + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
    return equipments.stream().map(equipment -> fromTo(equipment)).collect(Collectors.toList());
  }

  @CacheEvict(value = "equipments", allEntries = true)
  @Transactional
  public void update(Equipment equipment) {
    Assert.notNull(equipment, "equipment must not be null");

    List<String> params = new ArrayList<>();
    params.add(equipment.toStringForPost());
    System.out.println(equipment.toStringForPost());
    HttpURLConnection response;
    Integer respCode = 0;
    try {
      response = requestService.putRequest(params, URL_APPL + "gate/"+equipment.getId()+"?diameter=200");
      respCode = response.getResponseCode();

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(respCode + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
  }


  @CacheEvict(value = "equipments", allEntries = true)
  @Transactional
  public void enable(int id, boolean enabled) {
    Equipment equipment = get(id);
    equipment.setEnabled(enabled);
    repository.save(equipment);
  }

  public List<Equipment> getFilter(String ipAddress, String address) {
    String finalIpAddress = ipAddress == null ? "" : ipAddress;
    String finalAddress = address == null ? "" : address;
    return getAll().stream().filter(e -> e.getAddress().indexOf(finalIpAddress) > 0 || finalIpAddress == "")
        .filter(e -> e.getIp_address().indexOf(finalAddress) > 0 || finalAddress == "")
        .collect(Collectors.toList());
  }

  public void sendOpenClosePostRequest(int id, boolean setAction) {
    Assert.notNull(id, "id must not be null");
    Assert.notNull(setAction, "setAction must not be null");
    List<String> params = new ArrayList<>();
    params.add("{}");
    int respCode = 0;
    try {
      HttpURLConnection response = requestService
          .postRequest(params, setAction ? (URL_APPL + "gate/open-gate/" + id) : (URL_APPL + "gate/close-gate/" + id));
      respCode = response.getResponseCode();
      System.out.println(response.getResponseMessage());

    } catch (IOException e) {
      throw new IllegalRequestDataException(e.getMessage());
    } finally {
      if (respCode >= 300) {
        throw new IllegalRequestDataException(
            "Код ошибки " + HttpStatus.valueOf(respCode).value() + " " + HttpStatus.valueOf(respCode).getReasonPhrase());
      }
    }
  }
}
