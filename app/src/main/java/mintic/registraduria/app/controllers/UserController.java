package mintic.registraduria.app.controllers;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mintic.registraduria.app.models.User;
import mintic.registraduria.app.services.UserServices;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServices userServices;
	
	@GetMapping("/All")
	public List<User> getAllUsers(){
		return this.userServices.index();
	}
	
	@GetMapping("/{id}")
	public	Optional<User> getUserById(@PathVariable("id") int id){
	return this.userServices.show(id);
	
	}
	@GetMapping("/by_nickname/{nickname}")
	public Optional<User> getUserByNickname(@PathVariable("nickname")String nickname){
		return this.userServices.showByNickname(nickname);
	}
	@GetMapping("/by_email/{email}")
	public Optional<User> getUserByEmail(@PathVariable("email")String email){
		return this.userServices.showByEmail(email);
	}
	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public User insertUser(@RequestBody User user) {
		return this.userServices.create(user);		
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		return this.userServices.login(user);
	}
	
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public User updateUser(@PathVariable("id")int id, @RequestBody User user) {
		return this.userServices.update(id,user);
		
		
	}
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteUser(@PathVariable("id")int id) {
		return this.userServices.delete(id);
	}
}	
