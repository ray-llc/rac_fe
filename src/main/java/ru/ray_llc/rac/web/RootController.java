package ru.ray_llc.rac.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ray_llc.rac.model.Task;

@Controller
public class RootController {

  @GetMapping("/")
  public String root() {
    return "tasks";
  }

  //  @Secured("ROLE_ADMIN")
//  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/users")
  public String getUsers() {
    return "users";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/barriers")
  public String getBarriers() {
    return "barriers";
  }

  @GetMapping("/addcase")
  public String getAddcase(ModelMap model) {
    model.addAttribute("taskTo", new Task());
    return "addcase";
  }

  @GetMapping("/tasks")
  public String getTasks() {
    return "tasks";
  }

  @GetMapping("/map")
  public String getMap() {
    return "map";
  }
}
