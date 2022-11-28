package mintic.registraduria.app.models;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.JoinColumn;

@Entity
@Table(name = " rol")
public class Rol implements Serializable{ 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;
	@Column(name= "name",nullable=	false,unique=true,length=50 )
	private String name;
	private String description;
	@OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "rol")
	@JsonIgnoreProperties("rol")
	private List<User> users;
	
	@ManyToMany
	@JoinTable(
			name=" permissions_rol",
			joinColumns= @JoinColumn(name = "idRol"),
			inverseJoinColumns =@JoinColumn(name = "idPermission")
			)
	private Set<Permission>permissions;
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer id) {
		this.idRol = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Permission> getPermissions(){
		return permissions;
	}
	public void setPermissions(Set<Permission>permissions) {
		this.permissions=permissions;
		
	}
	public List<User> getUsers(){
	return users;
	}
	public void steUsers(List<User>users) {
	this.users=users;
	}
}

