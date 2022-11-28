package mintic.registraduria.app.repositories;

import org.springframework.stereotype.Repository;
import mintic.registraduria.app.models.Rol;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer>{

}
