package th.go.customs.example.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.customs.example.app.model.FwRoleModel;
import th.go.customs.example.app.model.UserPageDtlModel;
import th.go.customs.example.app.model.UserPageGroupModel;
import th.go.customs.example.app.repo.jpa.FwRoleRepo;
import th.go.customs.example.app.repo.jpa.UserPageDtlRepo;
import th.go.customs.example.app.repo.jpa.UserPageGroupRepo;
import th.go.customs.example.app.vo.res.GetRoleRes;
import th.go.customs.example.app.vo.res.GetRoleUserPageDtlRes;
import th.go.customs.example.app.vo.res.GetRoleUserPageGroupRes;
import th.go.customs.example.framework.bean.ConvertDateUtils;
import th.go.customs.example.framework.security.config.UserLoginUtil;


@Service
public class RoleService {
	
	@Autowired
	private FwRoleRepo fwRoleRepo;
	
	@Autowired
	private UserPageGroupRepo userPageGroupRepo;
	
	@Autowired
	private UserPageDtlRepo userPageDtlRepo;
	
	public GetRoleRes getRoleByRoleCode(String roleCode) {
		GetRoleRes dataRes = new GetRoleRes();
		List<GetRoleUserPageGroupRes> roleUserPageGroupList = new ArrayList<GetRoleUserPageGroupRes>();
		GetRoleUserPageGroupRes roleUserPageGroup;
		List<GetRoleUserPageDtlRes> roleUserPageDtlList;
		GetRoleUserPageDtlRes roleUserPageDtl;

		FwRoleModel fwRole = fwRoleRepo.findByRoleCode(roleCode);
		dataRes.setFwRoleId(fwRole.getFwRoleId());
		dataRes.setRoleCode(fwRole.getRoleCode());
		dataRes.setRoleName(fwRole.getRoleName());
		dataRes.setRoleDescription(fwRole.getRoleDescription());
		dataRes.setCreateBy(fwRole.getCreateBy());
		dataRes.setCreateDate(ConvertDateUtils.formatDateToString(fwRole.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
		dataRes.setUpdateBy(fwRole.getUpdateBy());
		dataRes.setUpdateDate(ConvertDateUtils.formatDateToString(fwRole.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));

		// find and loop userPageGroupList
		List<UserPageGroupModel> userPageGroupList = userPageGroupRepo.findByRoleCode(fwRole.getRoleCode());
		for (UserPageGroupModel userPageGroup : userPageGroupList) {
			roleUserPageGroup = new GetRoleUserPageGroupRes();
			roleUserPageGroup.setRoleCode(fwRole.getRoleCode());
			roleUserPageGroup.setPageGroupCode(userPageGroup.getPageGroupCode());
			roleUserPageGroup.setReadMark(userPageGroup.getReadMark());
			roleUserPageGroup.setWriteMark(userPageGroup.getWriteMark());
			roleUserPageGroup.setEditMark(userPageGroup.getEditMark());
			roleUserPageGroup.setApproveMark(userPageGroup.getApproveMark());
			roleUserPageGroup.setCreateBy(UserLoginUtil.getUsername());
			roleUserPageGroup.setCreateBy(userPageGroup.getCreateBy());
			roleUserPageGroup.setCreateDate(ConvertDateUtils.formatDateToString(userPageGroup.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
			roleUserPageGroup.setUpdateBy(userPageGroup.getUpdateBy());
			roleUserPageGroup.setUpdateDate(ConvertDateUtils.formatDateToString(userPageGroup.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
			// find and loop userPageDtlList
			List<UserPageDtlModel> userPageDtlList = userPageDtlRepo.findByRoleCodeAndPageGroupCode(fwRole.getRoleCode(), userPageGroup.getPageGroupCode());
			roleUserPageDtlList = new ArrayList<GetRoleUserPageDtlRes>();
			for (UserPageDtlModel userPageDtl : userPageDtlList) {
				roleUserPageDtl = new GetRoleUserPageDtlRes();
				// set list roleUserPageDtl
				roleUserPageDtl.setRoleCode(fwRole.getRoleCode());
				roleUserPageDtl.setPageDtlCode(userPageDtl.getPageDtlCode());
				roleUserPageDtl.setPageGroupCode(userPageDtl.getPageGroupCode());
				roleUserPageDtl.setReadMark(userPageDtl.getReadMark());
				roleUserPageDtl.setWriteMark(userPageDtl.getWriteMark());
				roleUserPageDtl.setEditMark(userPageDtl.getEditMark());
				roleUserPageDtl.setApproveMark(userPageDtl.getApproveMark());
				roleUserPageDtl.setCreateBy(userPageDtl.getCreateBy());
				roleUserPageDtl.setCreateDate(ConvertDateUtils.formatDateToString(userPageDtl.getUpdateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
				roleUserPageDtl.setUpdateBy(userPageDtl.getUpdateBy());
				roleUserPageDtl.setUpdateDate(ConvertDateUtils.formatDateToString(userPageDtl.getCreateDate(), ConvertDateUtils.DD_MM_YYYY_HHMM));
				roleUserPageDtlList.add(roleUserPageDtl);
			}
			roleUserPageGroup.setListDetail(roleUserPageDtlList);
			// set list roleUserPageGroupde
			roleUserPageGroupList.add(roleUserPageGroup);
		}
		// set list roleUserPageGroupList
		dataRes.setListGroup(roleUserPageGroupList);
		return dataRes;
	}

	
}
