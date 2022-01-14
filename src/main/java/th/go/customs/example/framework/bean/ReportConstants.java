package th.go.customs.example.framework.bean;

public class ReportConstants {

	public static final class FILE {
		public static final String JASPER = "jasper";
		public static final String JRXML = "jrxml";
		public static final String PNG = "png";
		public static final String PDF = "pdf";
	}

	// Path of JasperReports
	public static final class PATH {
		public static final String ROOT_PATH = "/reports";
		public static final String FONT_PATH = ROOT_PATH + "/fonts";
		public static final String IMAGE_PATH = ROOT_PATH + "/images";
		public static final String JRXML_PATH = ROOT_PATH + "/jrxml";
		// For Test
		public static final String TEST_PATH = "/tmp/";
	}

	// File Extension in JasperReports
	public static final class FILE_EXTENSION {
		public static final String JASPER = ".jasper";
		public static final String JRXML = "jrxml";
		public static final String JPG = "jpg";
		public static final String PNG = "png";
		public static final String PDF = "pdf";
		public static final String XLSX = "xlsx";
		public static final String DOCX = "docx";
	}

}
