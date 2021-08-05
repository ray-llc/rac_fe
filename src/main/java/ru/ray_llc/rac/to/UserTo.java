package ru.ray_llc.rac.to;

import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserTo extends BaseTo implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @NotBlank
  @Size(min = 2, max = 100)
  private String name;

  @Email
  @NotBlank
  @Size(max = 100)
  private String email;

  @NotBlank
  @Size(max = 100)
  private String login;

  @NotBlank
  @Size(min = 5, max = 32, message = "length must be between 5 and 32 characters")
  private String password;

  public UserTo() {
  }

  public UserTo(Integer id, String name, String email, String password, String login) {
    super(id);
    this.name = name;
    this.email = email;
    this.password = password;
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public String toString() {
    return "UserTo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", login='" + login + '\'' +
        '}';
  }
}
