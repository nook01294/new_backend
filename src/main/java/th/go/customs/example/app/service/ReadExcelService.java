package th.go.customs.example.app.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;

import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.FwUserRepo;
import th.go.customs.example.framework.bean.ExcelUtils;

@Service
public class ReadExcelService {

	@Autowired
	private FwUserRepo fwUserRepo;

	public void readExcel(MultipartFile files) throws IOException, ParseException {

		XSSFWorkbook wb = new XSSFWorkbook(files.getInputStream());
		XSSFSheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			if (row.getRowNum() > 0) {
				FwUserModel data = new FwUserModel();
				data.setUsername(ExcelUtils.getCellValueAsString(row.getCell(1)));
				data.setFirstName(ExcelUtils.getCellValueAsString(row.getCell(2)));
				data.setLastName(ExcelUtils.getCellValueAsString(row.getCell(3)));
				data.setPassword(ExcelUtils.getCellValueAsString(row.getCell(4)));
				data.setDateOfBirth(fomatDate(ExcelUtils.getCellValueAsString(row.getCell(5))));
				data.setMobile(ExcelUtils.getCellValueAsString(row.getCell(6)));
				data.setEmail(ExcelUtils.getCellValueAsString(row.getCell(7)));
				data.setCreatedBy(ExcelUtils.getCellValueAsString(row.getCell(8)));
				data.setCreatedDate(fomatDate(ExcelUtils.getCellValueAsString(row.getCell(9))));
				data.setUpdatedBy(ExcelUtils.getCellValueAsString(row.getCell(10)));
				data.setUpdatedDate(fomatDate(ExcelUtils.getCellValueAsString(row.getCell(11))));
				data.setRoleCode(ExcelUtils.getCellValueAsString(row.getCell(12)));
				fwUserRepo.save(data);
			}
		}

// *** read all file excel print show data in file excel  ****			

//			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
//			while (itr.hasNext()) {
//				Row row = itr.next();
//				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					switch (cell.getCellType()) {
//					case Cell.CELL_TYPE_STRING: // field that represents string cell type
//						System.out.print(cell.getStringCellValue() + "\t\t\t");
//						break;
//					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
//						System.out.print(cell.getNumericCellValue() + "\t\t\t");
//						break;
//					default:
//					}
//				}
//				System.out.println("");
//			}

	}

	public Date fomatDate(String date) throws ParseException {
		Date format = null;
		if (date != null) {
			format = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		}
		return format;

	}

}
