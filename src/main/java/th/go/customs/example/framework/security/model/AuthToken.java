package th.go.customs.example.framework.security.model;

import java.util.Date;

import lombok.Data;
import th.go.customs.example.app.vo.res.GetRoleRes;

@Data
public class AuthToken {

    private String token;
    private String username;
	private String firstName;
	private String lastName;
	private GetRoleRes role;
	private Date dateOfBirth;
	private String mobile;
	private String email;

}
