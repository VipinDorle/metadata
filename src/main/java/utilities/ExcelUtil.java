package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {
    private String filePath;
    private String sheetName;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtil(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet: " + sheetName + " not found.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath);
        }
    }

    public List<Map<String, String>> getDataList() {
        List<Map<String, String>> dataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                String header = headerRow.getCell(j).getStringCellValue();
                String cellValue = (row.getCell(j) != null) ? row.getCell(j).toString() : "";
                dataMap.put(header.trim(), cellValue.trim());
            }
            dataList.add(dataMap);
        }
        return dataList;
    }

    public void close() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
