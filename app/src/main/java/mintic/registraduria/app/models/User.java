package mintic.registraduria.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
	@Table(name = "user")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUser;
	@Column(name="nickname", nullable=false,unique=true)
	private String nickname;
	@Column(name= "email", nullable=false,unique=true)
	private String email;
	@Column(name="password",nullable=false)
	private String password;
	@ManyToOne	
	@JoinColumn(name="idRol")
	@JsonIgnoreProperties("users")
	private Rol rol;
	
	public Integer getId() {
		return idUser;
	}
	public void setId(Integer id){
		this.idUser = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol=rol;
	}


	

}
