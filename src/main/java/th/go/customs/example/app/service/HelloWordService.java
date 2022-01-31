package th.go.customs.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.customs.example.app.model.CountAssetDataModel;
import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.CountAssetDataRepo;
import th.go.customs.example.app.repo.jpa.FwUserRepo;

@Service
public class HelloWordService {

	@Autowired
	FwUserRepo FwUserRepo;

	@Autowired
	CountAssetDataRepo countAssetDataRepo;

	public String showText() {
		return "HELLO WORD";
	}

	public FwUserModel getProfile(String username) {
		FwUserModel data = FwUserRepo.findByUsername(username);
		return data;
	}

	public List<CountAssetDataModel> getListData() {
		List<CountAssetDataModel> data = countAssetDataRepo.findAll();
		return data;
	}
	
	public CountAssetDataModel getCountAsset(String id) {
		CountAssetDataModel data = null;
		data = countAssetDataRepo.findById(Long.parseLong(id)).get();
		return data;
	}

}
