package ru.ray_llc.rac.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.ray_llc.rac.AbstractControllerTest;
import ru.ray_llc.rac.to.TaskTo;


/*
 * @author Alexandr.Yakubov
 **/

class RequestServiceTest extends AbstractControllerTest {

  @Test
  void postRequest() throws IOException {

    TaskTo taskTo = new TaskTo(111D, 222D, "addressss", "9182498619", "123AA22");
    List<String> params = new ArrayList<>();
    params.add(taskTo.toStringForPost());
    HttpURLConnection httpURLConnection = requestService.postRequest(params, "http://localhost:8081/api/application/list/ACTIVE");

//    assertEquals(httpURLConnection.getResponseCode(), 201);
  }
}