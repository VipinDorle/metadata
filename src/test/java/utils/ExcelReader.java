package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {

    private static final String TESTDATA_FILE_PATH = "src/test/resources/testdata/metadata_testdata.xlsx";
    private static final String SHEET_NAME = "Sheet1"; // Change if your sheet name is different

    public static Map<String, String> getMetadataForTestCase(String testCaseId) {
        Map<String, String> metadata = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(TESTDATA_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            Iterator<Row> rows = sheet.iterator();
            Row headerRow = rows.next(); // First row assumed as header

            int testCaseIdColumnIndex = -1;
            for (org.apache.poi.ss.usermodel.Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("TestCaseID")) {
                    testCaseIdColumnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (testCaseIdColumnIndex == -1) {
                throw new RuntimeException("TestCaseID column not found in Excel");
            }

            while (rows.hasNext()) {
                Row row = rows.next();
                org.apache.poi.ss.usermodel.Cell testCaseCell = row.getCell(testCaseIdColumnIndex);

                if (testCaseCell != null && testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseId)) {
                    for (org.apache.poi.ss.usermodel.Cell cell : row) {
                        String headerName = headerRow.getCell(cell.getColumnIndex()).getStringCellValue();
                        String cellValue = getCellValueAsString(cell);
                        metadata.put(headerName, cellValue);
                    }
                    break; // Found matching row, exit loop
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage());
        }

        if (metadata.isEmpty()) {
            throw new RuntimeException("TestCaseID " + testCaseId + " not found in Excel sheet");
        }

        return metadata;
    }

    private static String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long)cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
