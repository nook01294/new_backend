package th.go.customs.example.app.vo.res;

import java.util.List;

import lombok.Data;

@Data
public class GetRoleUserPageGroupRes {
	
	 private Long userPageDtlId;
	 private String roleCode;
	 private String pageGroupCode;
	 private String pageDtlCode;
	 private String createBy;
	 private String createDate;
	 private String updateBy;
	 private String updateDate;
	 private Boolean readMark;
	 private Boolean writeMark;
	 private Boolean editMark;
	 private Boolean approveMark;
	 private List<GetRoleUserPageDtlRes> listDetail;

}
