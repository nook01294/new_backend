package th.go.customs.example.app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.FwUserRepo;
import th.go.customs.example.framework.bean.ReportUtils;

@Service
public class ReportService {
	
	@Autowired
	private FwUserRepo fwUserRepo;
	
	public byte[] exportReport() throws JRException, IOException {
		
		String reportName = "data_user.jrxml";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("", "");
		List<FwUserModel> data = fwUserRepo.findAll();
		JRDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint01 = ReportUtils.getJasperPrint01(reportName, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint01);
		ReportUtils.closeResourceFileInputStream(params);

		return content;	
	}
	

}
