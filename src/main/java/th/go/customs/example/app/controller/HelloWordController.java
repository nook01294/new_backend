package th.go.customs.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.app.service.HelloWordService;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_MESSAGE;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;
import th.go.customs.example.framework.bean.ResponseData;

@RestController
@RequestMapping("api/test")
public class HelloWordController {
	
	@Autowired
	private HelloWordService helloWordService;
	
	@GetMapping("/get-show")
	@ResponseBody
	public ResponseData<String> getAll() {
		ResponseData<String> response = new ResponseData<String>();
		try {
			response.setData(helloWordService.showText());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
