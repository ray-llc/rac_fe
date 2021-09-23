package ru.ray_llc.rac.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.ray_llc.rac.model.Role;
import ru.ray_llc.rac.model.Task;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.to.TaskTo;
import ru.ray_llc.rac.to.UserTo;

public class UserUtil {

  public static User createNewFromTo(UserTo userTo) {
    return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(),
        userTo.getLogin(), Role.USER);
  }

  public static UserTo asTo(User user) {
    return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getLogin());
  }

  public static TaskTo asTo(Task task) {
    return new TaskTo(task.getLongitude(), task.getLatitude(), task.getAddress(), task.getPhone(),
        task.getNumber_auto());
  }

  public static Task fromTo(TaskTo task) {
    return new Task(task.getLongitude(), task.getLatitude(), task.getAddress(), task.getPhone(),
        task.getNumber_auto());
  }


  public static User updateFromTo(User user, UserTo userTo) {
    user.setName(userTo.getName());
    user.setEmail(userTo.getEmail().toLowerCase());
    user.setLogin(userTo.getLogin());
    user.setPassword(userTo.getPassword());
    return user;
  }

  public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
    String password = user.getPassword();
    user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
    user.setEmail(user.getEmail().toLowerCase());
    return user;
  }
}