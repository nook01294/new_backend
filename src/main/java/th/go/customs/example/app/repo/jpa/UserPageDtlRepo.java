package th.go.customs.example.app.repo.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.go.customs.example.app.model.UserPageDtlModel;


@Repository
public interface UserPageDtlRepo extends CrudRepository<UserPageDtlModel, Long> {
	public List<UserPageDtlModel> findByRoleCodeAndPageGroupCode(String roleCode, String pageGroupCode);

}
