package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.ray_llc.rac.repository.UserRepository;
import ru.ray_llc.rac.to.TaskIntegrationTo;

@Service
public class RequestService {

  public RequestService(UserRepository userRepository) {
  }

  //http://localhost:8081/api/application/list/ACTIVE
  public HttpURLConnection postRequest(List<String> params, String postPath) throws IOException {
    URL url = new URL(postPath);

    url.toString().replace(url.getPath(), "");
    StringBuilder postData = new StringBuilder();

    for (String item : params) {
      if (postData.length() > 0) {
        postData.append(",");
      }
      postData.append(item);
    }

    System.out.println(postData);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);
    if (postData.length() > 0) {
//      postData.append("]");
      byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
      conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      conn.getOutputStream().write(postDataBytes);
    }
    System.out.println(conn.getResponseCode());
    conn.disconnect();
    return conn;
  }

  //http://localhost:8081/api/application/list/ACTIVE
  public HttpURLConnection getRequest(String getPath) throws IOException {
    URL url = new URL(getPath);
    url.toString().replace(url.getPath(), "");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setDoOutput(true);


//    List<TaskIntegrationTo> taskList = new ArrayList(Arrays.asList(taskIntegrationTos));
//    System.out.println(taskList.size());
//    List<TaskIntegrationTo> taskIntegrationTos =  mapper.readValue(response.toString().substring(response.toString().indexOf('[')+1, response.toString().lastIndexOf(']')), new TypeReference<List<TaskIntegrationTo>>(){});
//    System.out.println(taskIntegrationTos.size());
//    JsonNode matrix =  mapper.readValue(conn.getInputStream(), JsonNode.class);



//    JSONArray jsonArray = new JSONArray(response.toString());
//    ArrayList<String> listdata = new ArrayList<String>();
//    for(int n = 0; n < jsonArray.length(); n++)
//    {
//      JSONObject object = jsonArray.getJSONObject(n);
//      listdata.add(object.optString("nr"));
//    }
//    StringBuilder respBody = new StringBuilder();

    conn.disconnect();
    return conn;
  }
}
