package Dayy10;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviders {

    // ------------------ EXCEL ------------------
    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {

        String path = "./test-data/orders_excel_data.xlsx";

        ExcelUtils xl = new ExcelUtils(path, "sheet1");

        int rownum = xl.getRowCount();
        int colCount = xl.getCellCount(1);

        Object[][] dataArray = new Object[rownum][colCount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colCount; j++) {
                dataArray[i - 1][j] = xl.getCellData(i, j);
            }
        }

        return dataArray;
    }

    // ------------------ JSON ------------------
    @DataProvider(name = "jsonDataProvider")
    public Object[][] jsonDataProvider() throws IOException {

        String filePath = "./test-data/book.json";

        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON → List<Map<String,String>>
        List<Map<String, String>> dataList = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<Map<String, String>>>() {}
        );

        // Convert List → Object[][]
        Object[][] dataArray = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }

        return dataArray;
    }
}
