package mintic.registraduria.app.controllers;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mintic.registraduria.app.models.Rol;
import mintic.registraduria.app.services.RolServices;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rol")
public class  RolController {
	@Autowired
	private RolServices rolServices;
	
	@GetMapping("/All")
	public List<Rol> getAllRoles(){
		return this.rolServices.index();
	}
	
	@GetMapping("/{id}")
	public	Optional<Rol> getRolById(@PathVariable("id") int id){
	return this.rolServices.show(id);
	
	}
	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol insertRol(@RequestBody Rol rol) {
		return this.rolServices.create(rol);		
	}
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Rol updateRol(@PathVariable("id")int id, @RequestBody Rol rol) {return this.rolServices.update(id,rol);	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteRol(@PathVariable("id")int id) {
		return this.rolServices.delete(id);
	}
}

