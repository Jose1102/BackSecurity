package mintic.registraduria.app.repositories;

import mintic.registraduria.app.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository	
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User>findByNickname(String nickname);
	Optional<User>findByEmail(String email);
	
	@Query(value= "SELECT * FROM  user WHERE email=? AND Password=?;",nativeQuery=true)
	public Optional<User> login (String email,String password);
	
	
}
