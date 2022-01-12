package th.go.customs.example.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "fw_users")
@Data
public class FwUserModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -725000429402385783L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String username;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "created_by", length = 25)
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate = new Date();

	@Column(name = "updated_by", length = 25)
	private String updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "role_code",length = 255)
	private String roleCode;

}
