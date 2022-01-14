package th.go.customs.example.app.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.go.customs.example.app.model.FwUserModel;



@Repository
public interface FwUserRepo extends CrudRepository<FwUserModel, Long> {
	
	FwUserModel findByUsername(String username);
	
	@Query(value = " select * from fw_users ", nativeQuery = true)
	public  List<FwUserModel> findAll();

}
