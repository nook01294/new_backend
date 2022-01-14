package th.go.customs.example.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	private static final Logger LOGGER = LogManager.getLogger(HelloWordController.class);
	
	@GetMapping("/get-show")
	@ResponseBody
	public ResponseData<String> getAll() {
		ResponseData<String> response = new ResponseData<String>();
		try {
			LOGGER.info("Info level log message");
			LOGGER.debug("Debug level log message");
			LOGGER.error("Error level log message");
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
