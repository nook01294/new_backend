package th.go.customs.example.app.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.app.service.ReportService;


@RestController
@RequestMapping("api/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/exportPdf")
	@ResponseBody
	public void exportPdfMTDREFcl( HttpServletResponse response) throws Exception {
		byte[] reportFile = reportService.exportReport();
		String filename = "Backend_Example.pdf";
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");
		FileCopyUtils.copy(reportFile, response.getOutputStream());
		
	}

}
