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
@Table(name = "user_page_dtl")
@Data
public class UserPageDtlModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -651689081382811403L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_page_dtl_id")
	private Long userPageDtlId;

	@Column(name = "role_code", length = 25)
	private String roleCode;

	@Column(name = "page_group_code", length = 25)
	private String pageGroupCode;

	@Column(name = "page_dtl_code", length = 25)
	private String pageDtlCode;

	@Column(name = "create_by", length = 25)
	private String createBy;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "update_by", length = 25)
	private String updateBy;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "read_mark")
	private Boolean readMark;

	@Column(name = "write_mark")
	private Boolean writeMark;

	@Column(name = "edit_mark")
	private Boolean editMark;

	@Column(name = "approve_mark")
	private Boolean approveMark;

}
