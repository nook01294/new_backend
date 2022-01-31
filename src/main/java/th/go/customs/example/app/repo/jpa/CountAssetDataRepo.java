package th.go.customs.example.app.repo.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import th.go.customs.example.app.model.CountAssetDataModel;

@Repository
public interface CountAssetDataRepo extends CrudRepository<CountAssetDataModel, Long> {

	List<CountAssetDataModel> findAll();
	
	
}
