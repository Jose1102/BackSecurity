package mintic.registraduria.app.services;


import mintic.registraduria.app.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mintic.registraduria.app.repositories.PermissionRepository;
import java.util.List;
import java.util.Optional;

 @Service
public class PermissionServices {
	@Autowired	
	private PermissionRepository permissionRepository;
	
	public List<Permission> index() {
		return (List<Permission>)this.permissionRepository.findAll();
		
	}
	public Optional<Permission> show (int id) {
		return this.permissionRepository.findById(id);
	}
	public Permission create (Permission newPermission) {

		return this.permissionRepository.save(newPermission);

	}
	public Permission update(int id, Permission updatedPermission) {
		if (id>0) {
			Optional<Permission> tempPermission = this.show(id);
			if (tempPermission.isPresent()) {
				if (updatedPermission.getUrl()!=null)
					tempPermission.get().setUrl(updatedPermission.getUrl());}
				if (updatedPermission.getMethod()!=null)
					tempPermission.get().setMethod(updatedPermission.getMethod());
			return this.permissionRepository.save(tempPermission.get());
			}
	
		else {
			return updatedPermission;
		}
	}
	
		
	public boolean delete (int id) {
		boolean success = this.show(id).map(permission -> {
			this.permissionRepository.delete(permission);
			return true;
		}).orElse(false);
		return success;
	}
}