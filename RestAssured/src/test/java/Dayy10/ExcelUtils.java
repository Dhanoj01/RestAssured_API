package Dayy10;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    String path;
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public ExcelUtils(String path, String sheetName) throws IOException {
        this.path = path;
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    // Get total rows (excluding header)
    public int getRowCount() {
        return sheet.getLastRowNum(); 
    }

    // Get column count
    public int getCellCount(int rownum) {
        return sheet.getRow(rownum).getLastCellNum();
    }

    // Get cell data as String
    public String getCellData(int rownum, int colnum) {
        return sheet.getRow(rownum).getCell(colnum).toString();
    }
}
