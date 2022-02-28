package th.go.customs.example.app.vo.req;

import java.util.Date;

import lombok.Data;

@Data
public class FwUserReq {
	
	private String username;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String name;
	private Date dateOfBirth;
	private String phone;
	private String email;

}
