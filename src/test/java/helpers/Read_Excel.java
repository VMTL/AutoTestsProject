package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class Read_Excel{

	public String fileName;
	public FileInputStream ExcelFile;
	public XSSFWorkbook ExcelWBook;
	private XSSFSheet ExcelWSheet;
	private String Cell;
	private XSSFRow Row;
	
	public Read_Excel(String fileName){
		this.fileName = fileName;
		
		try {
			ExcelFile = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		try {
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (IOException e) {
			System.out.println("Cannot open the file");
			e.printStackTrace();
		}
	}

	public Object[][] excelToArray(String sheetName) {
		String[][] arrayExcelData = {};
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			int LastRowNum = ExcelWSheet.getLastRowNum();
			int FirstRowNum = ExcelWSheet.getFirstRowNum();
			int LastColumnNumber = ExcelWSheet.getRow(0).getLastCellNum();
			int FirstColumnNumber = ExcelWSheet.getRow(0).getFirstCellNum();
			DataFormatter formatter = new DataFormatter();
			
			arrayExcelData = new String[LastRowNum + 1][LastColumnNumber];
			int ci = 0;
			for (int i = FirstRowNum; i <= LastRowNum; i++, ci++) {
				Row = ExcelWSheet.getRow(i);
				for (int j = FirstColumnNumber; j < LastColumnNumber; j++) {
					Cell = formatter.formatCellValue(Row.getCell(j));
					arrayExcelData[ci][j] = Cell;
				}
			}
		}catch (NullPointerException e) {
			System.out.println("NullPointerException excelToArray[][]");
			e.printStackTrace();
		}catch (IllegalStateException e) {
			System.out.println("Data type error Excel");
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	public Map<String, String> excelToMap(String sheetName){
		Object [][] excelArray = excelToArray(sheetName);
		Map<String, String> excelMap = new HashMap<String, String>();
		
		for(int i = 0; i < excelArray.length; i++) {
			excelMap.put(excelArray[i][0].toString(), excelArray[i][1].toString());
		}
		return excelMap;		
	}
	
	public String[] getExcelSheets() {
		String[] arrayExcelSheets = {};
		try {
			arrayExcelSheets = new String[ExcelWBook.getNumberOfSheets()];
			
			for(int i = 0; i < ExcelWBook.getNumberOfSheets(); i++) {
				arrayExcelSheets[i] = ExcelWBook.getSheetName(i);
			}
		}catch (NullPointerException e) {
			System.out.println("NullPointerException Excel");
			//e.printStackTrace();
		}
		return arrayExcelSheets;		
	}
	
	public String[] getExcelDataSimpleArray(String sheetName) {
		String[] arrayExcelData = {};
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			int LastRowNum = ExcelWSheet.getLastRowNum();
			int FirstRowNum = ExcelWSheet.getFirstRowNum();
			DataFormatter formatter = new DataFormatter();
			
			arrayExcelData = new String[LastRowNum + 1];
			for (int i = FirstRowNum; i <= LastRowNum; i++) {
				Row = ExcelWSheet.getRow(i);
				Cell = formatter.formatCellValue(Row.getCell(0));
				arrayExcelData[i] = Cell;
			}
		}catch (NullPointerException e) {
			System.out.println("NullPointerException Excel");
			//e.printStackTrace();
		}catch (IllegalStateException e) {
			System.out.println("Data type error Excel");
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	public JSONObject excelToJson(String sheetName){
		Object[][] excelObject = excelToArray(sheetName);
		JSONObject requestParams = new JSONObject();
			for(int i = 0; i < excelObject.length; i++) {
				for(int j = 0; j < 2; j++) {
					requestParams.put((String) excelObject[i][0], excelObject[i][j]);
				}
			}
	return requestParams;
	}
	
	public JSONObject exceSimpleArraylToJson(String sheetName){
		String[] excelObject = getExcelDataSimpleArray(sheetName);
		JSONObject requestParams = null;
		try {
			requestParams = (JSONObject) new JSONParser().parse(excelObject[0].toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	return requestParams;
	}
}