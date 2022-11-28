package mintic.registraduria.app.services;

import mintic.registraduria.app.models.Permission;
import mintic.registraduria.app.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mintic.registraduria.app.repositories.RolRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolServices {
	@Autowired	
	private RolRepository rolRepository;
	
	public List<Rol> index() {
		return (List<Rol>)this.rolRepository.findAll();
		
	}
	public Optional<Rol> show (int id) {
		return this.rolRepository.findById(id);
	}
	public Rol create (Rol newRol) {
		return this.rolRepository.save(newRol);

	}
	public Rol update(int id, Rol updatedRol) {
		if (id>0) {
			Optional<Rol> tempRol = this.show(id);
			if (tempRol.isPresent()) {
				if (updatedRol.getName()!=null)
					tempRol.get().setName(updatedRol.getName());}
				if (updatedRol.getDescription()!=null)
					tempRol.get().setDescription(updatedRol.getDescription());
			return this.rolRepository.save(tempRol.get());
			}
	
		else {
			return updatedRol;
		}
	}
			
	public boolean delete (int id) {
		boolean success = this.show(id).map(rol -> {
			this.rolRepository.delete(rol);
			return true;
		}).orElse(false);
		return success;
	}

 }