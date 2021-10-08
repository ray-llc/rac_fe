package ru.ray_llc.rac.repository.datajpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.repository.UserRepository;

@Repository
@Transactional(readOnly = true)
public class DataJpaUserRepository implements UserRepository {

  private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

  @Autowired
  private CrudUserRepository crudRepository;

//    public DataJpaUserRepository(CrudUserRepository crudRepository) {
//        this.crudRepository = crudRepository;
//    }

  @Override
  public User save(User user) {
    return crudRepository.save(user);
  }

  @Override
  public boolean delete(int id) {
    return crudRepository.delete(id) != 0;
  }

  @Override
  public User get(int id) {
    return crudRepository.findById(id).orElse(null);
  }

  @Override
  public User getByEmail(String email) {
    return crudRepository.getByEmail(email);
  }

  @Override
  public User getByLogin(String login) {
    return crudRepository.getByLogin(login);
  }

  @Override
  public List<User> getAll() {
    return crudRepository.findAll(SORT_NAME_EMAIL);
  }

}