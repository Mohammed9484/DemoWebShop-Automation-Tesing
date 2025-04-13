package DemoWebShop.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	public List<List<String>> getData(String testCaseName) throws IOException {
	    List<List<String>> allData = new ArrayList<>();
	    FileInputStream file = new FileInputStream("E:\\ITI Testing\\Automation Project\\Book1.xlsx");
	    XSSFWorkbook workBook = new XSSFWorkbook(file);

	    try {
	        int sheets = workBook.getNumberOfSheets();
	        for (int i = 0; i < sheets; i++) {
	            if (workBook.getSheetName(i).equalsIgnoreCase("testdata")) {
	                XSSFSheet sheet = workBook.getSheetAt(i);
	                Iterator<Row> rows = sheet.iterator();
	                Row firstRow = rows.next();
	                Iterator<Cell> ce = firstRow.iterator();

	                int k = 0, column = -1;
	                while (ce.hasNext()) {
	                    Cell value = ce.next();
	                    if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
	                        column = k;
	                        break;
	                    }
	                    k++;
	                }

	                if (column == -1) {
	                    System.out.println("Error: 'TestCase' column not found!");
	                    return allData;
	                }

	                while (rows.hasNext()) {
	                    Row r = rows.next();
	                    Cell testCaseCell = r.getCell(column);

	                    if (testCaseCell != null && testCaseCell.getCellType() == CellType.STRING &&
	                        testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {

	                        List<String> rowData = new ArrayList<>();
	                        Iterator<Cell> cv = r.cellIterator();

	                        while (cv.hasNext()) {
	                            Cell c = cv.next();
	                            if (c != null) {
	                                switch (c.getCellType()) {
	                                    case STRING:
	                                        rowData.add(c.getStringCellValue());
	                                        break;
	                                    case NUMERIC:
	                                        rowData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
	                                        break;
	                                    case BOOLEAN:
	                                        rowData.add(String.valueOf(c.getBooleanCellValue()));
	                                        break;
	                                    case BLANK:
	                                        rowData.add("");
	                                        break;
	                                    default:
	                                        rowData.add("");
	                                }
	                            }
	                        }
	                        allData.add(rowData);
	                    }
	                }
	            }
	        }
	    } finally {
	        workBook.close();
	        file.close();
	    }

	    return allData;
	}




	public static void main(String[] args) throws IOException {

	}

}
