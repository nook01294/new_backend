package th.go.customs.example.framework.bean;

import lombok.Data;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;


@Data
public class ResponseData<T> {
	private RESPONSE_STATUS status = RESPONSE_STATUS.FAILED;
	private String message;
	private T data;
}