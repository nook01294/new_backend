package th.go.customs.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.FwUserRepo;
import th.go.customs.example.app.vo.req.FwUserReq;

@Service
public class FwUserService {
	
	@Autowired
	private FwUserRepo fwUserRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	public void register(FwUserReq req) {
		FwUserModel res = new FwUserModel();
		res.setUsername(req.getUsername());
		res.setPassword(bcryptEncoder.encode(req.getPassword().trim()));
		String spiltName[] =  req.getName().split(" ");
		res.setFirstName(spiltName[0]);
		res.setLastName(spiltName[1]);
		res.setDateOfBirth(req.getDateOfBirth());
		res.setEmail(req.getEmail());
		res.setMobile(req.getPhone());
		res.setRoleCode("USER");
		fwUserRepo.save(res);
		
	}
	
}
