package mintic.registraduria.app.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="permission")

public class Permission implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idPermission;
@Column(name="url", nullable=false,unique=true)
private String url;
@Column(name="method", nullable=false)
private String method;
@ManyToMany(mappedBy="permissions")
private Set<Rol> roles;
public Integer getId() {
	return getIdPermission();
}
public void setId(Integer id) {
	this.setIdPermission(id);
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getMethod() {
	return method;
}
public void setMethod(String method) {
	this.method = method;
}
private Integer getIdPermission() {
	return idPermission;
}
private void setIdPermission(Integer idPermission) {
	this.idPermission = idPermission;
}

}
