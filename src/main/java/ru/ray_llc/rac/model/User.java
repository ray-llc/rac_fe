package ru.ray_llc.rac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
    @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
    @NamedQuery(name = User.BY_LOGIN, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.login=?1"),
    @NamedQuery(name = User.BY_EMAIL, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
    @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email"),
})
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

  public static final String DELETE = "User.delete";
  public static final String BY_LOGIN = "User.getByLogin";
  public static final String BY_EMAIL = "User.getByEmail";
  public static final String ALL_SORTED = "User.getAllSorted";

  @Column(name = "email", nullable = false)
  @Email
  @NotBlank
  @Size(max = 100)
  private String email;

  @Column(name = "login", nullable = false, unique = true)
  @NotBlank
  @Size(max = 100)
  private String login;

  @Column(name = "password", nullable = false)
  @NotBlank
  @Size(min = 5, max = 100)
  // https://stackoverflow.com/a/12505165/548473
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
  private boolean enabled = true;

  @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
  @NotNull
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Date registered = new Date();

  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
  @BatchSize(size = 200)
  @JoinColumn(name = "user_id") //https://stackoverflow.com/a/62848296/548473
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Role> roles;

  public User() {
  }

  public User(User u) {
    this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getLogin(),
        u.isEnabled(), u.getRegistered(), u.getRoles());
  }

  public User(Integer id, String name, String email, String password, String login,
      Role role, Role... roles) {
    this(id, name, email, password, login, true, new Date(), EnumSet.of(role, roles));
  }

  public User(Integer id, String name, String email, String password, String login,
      boolean enabled, Date registered, Collection<Role> roles) {
    super(id, name);
    this.email = email;
    this.password = password;
    this.login = login;
    this.enabled = enabled;
    this.registered = registered;
    setRoles(roles);
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getRegistered() {
    return registered;
  }

  public void setRegistered(Date registered) {
    this.registered = registered;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles =
        CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", email=" + email +
        ", name=" + name +
        ", enabled=" + enabled +
        ", roles=" + roles +
        ", login=" + login +
        '}';
  }
}