package ru.ray_llc.rac;

import java.util.Arrays;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.ray_llc.rac.model.Role;
import ru.ray_llc.rac.model.User;
import ru.ray_llc.rac.web.user.AdminRestController;

public class SpringMain {

  public static void main(String[] args) {
    // java 7 automatic resource management (ARM)
    try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
      appCtx.getEnvironment()
          .setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
      appCtx.load("spring/inmemory.xml");
      appCtx.refresh();

      System.out
          .println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
      AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
      adminUserController
          .create(
              new User(null, "userName", "email@mail.ru", "password", "loginTest",
                  Role.ADMIN));
      System.out.println();

    }
  }
}
