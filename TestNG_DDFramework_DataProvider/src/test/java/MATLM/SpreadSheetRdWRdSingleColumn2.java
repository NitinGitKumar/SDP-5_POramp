package MATLM;

import java.io.File;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.*;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.*;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetRdWRdSingleColumn2 {
	
	
	//public static ArrayList<String> readExcel(String file1,String sheetName) throws IOException{
		
		public static String[] extractExcelContentByColumnIndex(String file1,String sheetName,int columnIndex){
	        ArrayList<String> columndata = null;
	        try {
	            File f = new File(file1);
	            FileInputStream ios = new FileInputStream(f);
	            HSSFWorkbook workbook = new HSSFWorkbook(ios);
	            HSSFSheet sheet = workbook.getSheet(sheetName);
	            Iterator<Row> rowIterator = sheet.iterator();
	             columndata = new ArrayList<String>();
	             

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();

	                    if(row.getRowNum() > 0){ //To filter column headings
	                        if(cell.getColumnIndex() == columnIndex){// To match column index
	                            switch (cell.getCellType()) {
	                            case NUMERIC:
	                                columndata.add(cell.getNumericCellValue()+"");
	                                break;
	                            case STRING:
	                                columndata.add(cell.getStringCellValue());
	                                
	                            
	                         
	                                break;
	                            }
	                        }
	                    }
	                }
	            }
	            ios.close();
	            System.out.println(columndata);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(columndata);
	        String[] array = columndata.toArray(new String[columndata.size()]);
	        return array;
	    }

	

	public static void writeExcel(String file1,String sheetName,String dataToWrite, int columnIndex) throws IOException{

	    //Create an object of File class to open xlsx file

	    File file =   new File(file1);

	    //Create an object of FileInputStream class to read excel file

	    FileInputStream inputStream = new FileInputStream(file);
	   
        //If it is xls file then create object of HSSFWorkbook class

    	HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
    	
	    //Read sheet inside the workbook by its name

	    HSSFSheet Sheet2 = workbook.getSheet(sheetName);
	    
	    

	    //Find number of rows in excel file
	  
	    int rowCount = Sheet2.getLastRowNum()-Sheet2.getFirstRowNum();
	    System.out.println(Sheet2.getLastRowNum());
	    System.out.println(Sheet2.getFirstRowNum());


	    //Create a loop over all the rows of excel file to read it

	    for (int i = 1; i < rowCount+1; i++) {

	        Row row = Sheet2.getRow(i);
	        
	        
	        int LastColumn=row.getLastCellNum();
	        System.out.println(LastColumn);
	        
	        //Cell cell = row.createCell(LastColumn);
	       // cell.setCellValue(dataToWrite);
	        
	        Cell cell = row.createCell(columnIndex);
		    cell.setCellValue(dataToWrite);

	    } 
	    inputStream.close();
	     //Write the workbook in file system
	      FileOutputStream outputStream = new FileOutputStream(file);
	      workbook.write(outputStream);
	      outputStream.close();
	}

	public static String extractExcelContentByColumnRowIndex(String file1,String sheetName,int rowIndex,int columnIndex){
		   String ReadCellContent = null;
        try {
        	//Create an object of File class to open xls file
            File file =    new File(file1);
            //Create an object of FileInputStream class to read excel file
            FileInputStream inputStream = new FileInputStream(file);
            //If it is xls file then create object of HSSFWorkbook class          
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //Read sheet inside the workbook by its name
            HSSFSheet sheet = workbook.getSheet(sheetName);
            //Fetch row
            Row row = sheet.getRow(rowIndex);
            //get cell value for given row and column            
            ReadCellContent =row.getCell(columnIndex).getStringCellValue();
            inputStream.close();         	
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ReadCellContent);
		return ReadCellContent;
    }
	
	
	
	public static void writeExcelByColumnRowIndex(String file1,String sheetName,String dataToWrite,int rowIndex, int columnIndex) throws IOException{

	    //Create an object of File class to open xlsx file

	    File file =   new File(file1);
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);	   
        //If it is xls file then create object of HSSFWorkbook class
    	HSSFWorkbook workbook = new HSSFWorkbook(inputStream);   	
	    //Read sheet inside the workbook by its name
	    HSSFSheet Sheet2 = workbook.getSheet(sheetName);
	    //Create a loop over all the rows of excel file to read it
	        Row row = Sheet2.getRow(rowIndex);     
	        Cell cell = row.createCell(columnIndex);
		    cell.setCellValue(dataToWrite);
	    inputStream.close();
	     //Write the workbook in file system
	      FileOutputStream outputStream = new FileOutputStream(file);
	      workbook.write(outputStream);
	      outputStream.close();
	}

	
    public static void main(String[] args) throws IOException 
    {
      extractExcelContentByColumnRowIndex("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1",1,0);
   	  writeExcelByColumnRowIndex("/Users/nitinkumar/Desktop/Minakshi /Book1.xls","Sheet1","Apt-111",1,3);
	   
	}
    

}




