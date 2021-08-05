package ru.ray_llc.rac;

import java.io.Serial;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.to.UserTo;
import ru.ray_llc.rac.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

  @Serial
  private static final long serialVersionUID = 1423423423423424L;

  private UserTo userTo;

  public AuthorizedUser(User user) {
    super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
    this.userTo = UserUtil.asTo(user);
  }

  public int getId() {
    return userTo.id();
  }

  public void update(UserTo newTo) {
    userTo = newTo;
  }

  public UserTo getUserTo() {
    return userTo;
  }

  @Override
  public String toString() {
    return userTo.toString();
  }
}