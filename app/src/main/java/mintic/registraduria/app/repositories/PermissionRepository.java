package mintic.registraduria.app.repositories;

import mintic.registraduria.app.models.Permission;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}
	