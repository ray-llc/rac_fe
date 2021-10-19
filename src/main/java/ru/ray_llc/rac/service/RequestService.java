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
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);
    if (postData.length() > 0) {
      byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
      conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      conn.getOutputStream().write(postDataBytes);
    }
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
    conn.disconnect();
    return conn;
  }

  //http://localhost:8081/api/application/close/{id}
  public HttpURLConnection deletePUTRequest(String getPath) throws IOException {
    URL url = new URL(getPath);
    url.toString().replace(url.getPath(), "");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("PUT");
    conn.setDoOutput(true);
    conn.disconnect();
    return conn;
  }

  //http://localhost:8081/api/application/close/{id}
  public HttpURLConnection deleteRequest(String getPath) throws IOException {
    URL url = new URL(getPath);
    url.toString().replace(url.getPath(), "");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("DELETE");
    conn.setDoOutput(true);
    conn.disconnect();
    return conn;
  }


  //http://localhost:8081/api/application/list/ACTIVE
  public HttpURLConnection putRequest(List<String> params, String postPath) throws IOException {
    URL url = new URL(postPath);

    url.toString().replace(url.getPath(), "");
    StringBuilder postData = new StringBuilder();

    for (String item : params) {
      if (postData.length() > 0) {
        postData.append(",");
      }
      postData.append(item);
    }

    System.out.println(postData.toString());

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("PUT");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);
    if (postData.length() > 0) {
      byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);
      conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      conn.getOutputStream().write(postDataBytes);
    }
    conn.disconnect();
    return conn;
  }

}
