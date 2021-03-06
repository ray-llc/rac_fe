package ru.ray_llc.rac.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import org.hibernate.Hibernate;
import org.springframework.util.Assert;
import ru.ray_llc.rac.HasId;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
//@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public abstract class AbstractBaseEntity implements HasId {

  public static final int START_SEQ = 100000;

  @Id
  @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
  //    @Column(name = "id", unique = true, nullable = false, columnDefinition = "integer default nextval('global_seq')")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
//  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  protected Integer id;

  protected AbstractBaseEntity() {
  }

  protected AbstractBaseEntity(Integer id) {
    this.id = id;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  // doesn't work for hibernate lazy proxy
  public int id() {
    Assert.notNull(id, "Entity must have id");
    return id;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ":" + id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !getClass().equals(Hibernate.getClass(o))) {
      return false;
    }
    AbstractBaseEntity that = (AbstractBaseEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id == null ? 0 : id;
  }
}