package th.go.customs.example.framework.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import th.go.customs.example.framework.bean.ReportConstants.PATH;

public class ReportUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(ReportUtils.class);
	
	public static JasperPrint getJasperPrint(String fileName, Map<String, Object> params) throws JRException, IOException {
		return getJasperPrint(fileName, params, new JREmptyDataSource());
	}
	
	public static JasperPrint getJasperPrint01(String fileName, Map<String, Object> params, JRDataSource dataSource) throws JRException, IOException {
		logger.info("getJasperPrint fileName={}", fileName);
		
		JasperPrint jasperPrint = null;
		if (FilenameUtils.isExtension(fileName, ReportConstants.FILE.JASPER)) {	
			InputStream jasperFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
			jasperFile.close();
		} else if (FilenameUtils.isExtension(fileName, ReportConstants.FILE.JRXML)) {
			InputStream jrxmlFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			jrxmlFile.close();
		}
		
		return jasperPrint;
	}
	
	public static JasperPrint getJasperPrint(String fileName, Map<String, Object> params, JRDataSource dataSource) throws JRException, IOException {
		logger.info("getJasperPrint fileName={}", fileName);
		
		JasperPrint jasperPrint = null;
		if (FilenameUtils.isExtension(fileName, ReportConstants.FILE_EXTENSION.JASPER)) {
			InputStream jasperFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			jasperPrint = JasperFillManager.fillReport(jasperFile, params, dataSource);
			jasperFile.close();
		} else if (FilenameUtils.isExtension(fileName, ReportConstants.FILE_EXTENSION.JRXML)) {
			InputStream jrxmlFile = getResourceFile(ReportConstants.PATH.JRXML_PATH, fileName);
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			jrxmlFile.close();
		}
		
		return jasperPrint;	
	}
	
	public static InputStream getResourceFile(String path, String fileName) throws IOException {
		String inputPath = path + "/" + fileName;
		logger.debug("getResourceFile inputPath={}", inputPath);
		// App Path
		InputStream resourceFile = ReportUtils.class.getResourceAsStream(inputPath);
		if (resourceFile == null) {
			// Web Path
			resourceFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputPath);
		}
		logger.debug("getResourceFile resourceFile={}", resourceFile);
		return resourceFile;
	}
	
	public static void prepareSubReport(Map<String, Object> params, String paramsKey, String subReportName) throws IOException, JRException {
		InputStream jasperStream = null;
		try {
			jasperStream = getResourceFile(PATH.JRXML_PATH, subReportName);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			params.put(paramsKey, jasperReport);
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (jasperStream != null) {
				jasperStream.close();
			}
		}
	}

	public static JasperPrint exportReport(String reportName, Map<String, Object> params, JRDataSource dataSource) throws IOException, JRException {
		logger.debug("exportReport reportName=" + reportName);
		
		for (Entry<String, Object> e : params.entrySet()) {
			logger.debug("param=" + e.getKey() + ", value=" + e.getValue());
		}
		
		JasperPrint jasperPrint = null;
		InputStream jasperStream = null;
		try {
			jasperStream = getResourceFile("reports/jrxml", reportName + ".jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		} catch (JRException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (jasperStream != null) {
				jasperStream.close();
			}
		}
		
		return jasperPrint;
	}
	
	public static void closeResourceFileInputStream(Map<String, Object> params) {
		for (Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() instanceof InputStream) {
				try {
					((InputStream) entry.getValue()).close();
					logger.debug("Close ResourceFileInputStream of key:{} Success", entry.getKey());
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
	
}
