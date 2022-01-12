package th.go.customs.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.framework.bean.ResponseData;
import th.go.customs.example.app.service.ResetPasswordService;
import th.go.customs.example.app.vo.req.FwUserReq;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_MESSAGE;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;

@RestController
@RequestMapping("api/authen")
public class ResetPasswordController {
	
	@Autowired
	ResetPasswordService resetPasswordService;
	
	@GetMapping("/reset_password")
	@ResponseBody
	public ResponseData<?> resetPassword(@RequestBody FwUserReq req) {
		ResponseData<?> response = new ResponseData<>();
		try {
			resetPasswordService.sendVerificationEmail(req);
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
