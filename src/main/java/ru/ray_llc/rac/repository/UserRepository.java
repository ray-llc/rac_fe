package ru.ray_llc.rac.repository;

import java.util.List;
import ru.ray_llc.rac.model.User;

public interface UserRepository {

  // null if not found, when updated
  User save(User user);

  // false if not found
  boolean delete(int id);

  // null if not found
  User get(int id);

  // null if not found
  User getByEmail(String email);

  // null if not found
  User getByLogin(String login);

  List<User> getAll();

}