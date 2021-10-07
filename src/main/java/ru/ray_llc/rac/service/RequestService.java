package ru.ray_llc.rac.service;

/*
 * @author Alexandr.Yakubov
 **/

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.ray_llc.rac.repository.UserRepository;

@Service
public class RequestService {

  public RequestService(UserRepository userRepository) {
  }

  //http://localhost:8081/api/application/list/ACTIVE
  public HttpURLConnection PostRequest(List<String> params, String postPath) throws IOException {
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
      postData.append("]");
      byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
      conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      conn.getOutputStream().write(postDataBytes);
    }
    System.out.println(conn.getResponseCode());
    conn.disconnect();
    return conn;
  }
}
