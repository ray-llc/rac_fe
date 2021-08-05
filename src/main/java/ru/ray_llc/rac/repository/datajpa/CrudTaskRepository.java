package ru.ray_llc.rac.repository.datajpa;

/*
 * @author Alexandr.Yakubov
 **/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ray_llc.rac.model.Task;

@Transactional(readOnly = true)
public interface CrudTaskRepository extends JpaRepository<Task, Integer> {

  @Transactional
  @Modifying
  @Query("DELETE FROM Task u WHERE u.id=:id")
  int delete(@Param("id") int id);

}
