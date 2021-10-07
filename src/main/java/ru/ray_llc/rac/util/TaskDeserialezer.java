package ru.ray_llc.rac.util;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import ru.ray_llc.rac.model.GeoLocation;
import ru.ray_llc.rac.to.TaskIntegrationTo;

public class TaskDeserialezer extends StdDeserializer {

  protected TaskDeserialezer() {
    this(null);
  }

  protected TaskDeserialezer(Class vc) {
    super(vc);
  }

  @Override
  public TaskIntegrationTo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
    JsonNode taskNode = jsonParser.getCodec().readTree(jsonParser);
    TaskIntegrationTo task = new TaskIntegrationTo();
    task.setId(Integer.parseInt(taskNode.get("id").textValue()));
    task.setAddress(taskNode.get("address").textValue());
    task.setPhone(taskNode.get("phone").textValue());
    task.setNumberAuto(taskNode.get("numberAuto").textValue());
    task.setStatus(taskNode.get("status").textValue());

    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setLongitude(Double.parseDouble(taskNode.get("geoLocation").get("longitude").textValue()));
    geoLocation.setLatitude(Double.parseDouble(taskNode.get("geoLocation").get("latitude").textValue()));
    task.setGeoLocation(geoLocation);

    DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    try {
      task.setRegistered(dateFormat.parse(taskNode.get("registered").textValue()));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return task;
  }
}
