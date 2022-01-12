package th.go.customs.example.app.repo.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.go.customs.example.app.model.UserPageGroupModel;


@Repository
public interface UserPageGroupRepo extends CrudRepository<UserPageGroupModel, Long>{
	public List<UserPageGroupModel> findByRoleCode(String roleCode);

}
