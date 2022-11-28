package mintic.registraduria.app.services;

import mintic.registraduria.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mintic.registraduria.app.repositories.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

 @Service
public class UserServices {
	@Autowired	
	private UserRepository userRepository;
	
	public List<User> index() {
		return (List<User>)this.userRepository.findAll();
		
	}
	public Optional<User> show (int id) {

		return this.userRepository.findById(id);
	}
	public Optional<User>showByNickname(String nickname){
		return this.userRepository.findByNickname(nickname);
	}
	public Optional<User>showByEmail(String email){
		return this.userRepository.findByEmail(email);
		}
	public User create(User newUser){
		if(newUser.getId() == null){
			if (newUser.getEmail() != null && newUser.getNickname() != null && newUser.getPassword() != null)
				return this.userRepository.save(newUser);
		 else {
			return newUser;
		}
	}
		else {
		 return newUser;
	 }
 }
	public User update(int id, User updatedUser) {
		if (id > 0) {
			Optional<User> tempUser = this.show(id);
			if (tempUser.isPresent()) {
				if (updatedUser.getNickname() != null)
					tempUser.get().setNickname(updatedUser.getNickname());
				if (updatedUser.getPassword() != null)
					tempUser.get().setPassword(this.convertToSHA256(updatedUser.getPassword()));
				return this.userRepository.save(tempUser.get());
			} else {
				return updatedUser;
			}
		} else {
			return updatedUser;
		}
	}
		
	public boolean delete (int id) {
		boolean success = this.show(id).map(user -> {
			this.userRepository.delete(user);
			return true;
		}).orElse(false);
		return success;
	}
	public ResponseEntity<User>  login (User user){
		User response ;
		if (user.getPassword()!=null && user.getEmail()!= null){
			String email= user.getEmail();
			String password = this.convertToSHA256(user.getPassword());
			Optional<User> result=this.userRepository.login(email,password);
			if(result != null)
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
						"Invalid access.");
			else
				response = result.get();
		}

		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Mandatory fields had not been send");
			return new ResponseEntity<>(response, HttpStatus.OK);




	}
	
	public String convertToSHA256(String password){
	MessageDigest md= null;
	try {
		md = MessageDigest.getInstance( "SHA-256");
	}
	catch(NoSuchAlgorithmException e) {
		e.printStackTrace();
		return null;
	}
	byte[]hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	for (byte b: hash)
		sb.append(String.format("%02x", b));
	return sb.toString();
	
	}
}
