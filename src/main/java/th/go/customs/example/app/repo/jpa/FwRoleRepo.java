package th.go.customs.example.app.repo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import th.go.customs.example.app.model.FwRoleModel;


@Repository
public interface FwRoleRepo extends CrudRepository<FwRoleModel, Long>{

	public FwRoleModel findByRoleCode(String roleCode); 
	
}
