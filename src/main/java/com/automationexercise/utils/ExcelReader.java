package com.automationexercise.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ExcelReader {
    private ExcelReader() {
        //utility class
    }

    public static List<Map<String, String>> readSheet(String resourcePath, String sheetName) {
        try (InputStream is = ExcelReader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Test Data File not found" + resourcePath);
            }
            try (Workbook workbook = WorkbookFactory.create(is)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet " + sheetName + "not found!");
                }
                return parseSheet(workbook,sheet);
            }
        }
        catch (IOException e){
            throw new RuntimeException("File Not Found Error : "+ resourcePath, e);
        }
    }

    private static List<Map<String, String>> parseSheet(Workbook workbook, Sheet sheet) {
        // Date Formatter converts any cell into text exactly as Excel displays it
        DataFormatter formatter = new DataFormatter();
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        Row rowHeader =sheet.getRow(sheet.getFirstRowNum());
        List<String> headers = new ArrayList<>();
        for(Cell cell: rowHeader){
            headers.add(formatter.formatCellValue(cell).trim());
        }

        List<Map<String, String>> rows = new ArrayList<>();
        for(int i=sheet.getFirstRowNum()+1; i<= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if(row == null){
                continue;
            }
            Map<String,String> rowData = new LinkedHashMap<>();
            boolean hasData = false;
            for(int j = 0; j<headers.size(); j++){
                Cell cell = row.getCell(j);
                String value = formatter.formatCellValue(cell, evaluator).trim();
                if(!value.isEmpty())
                    hasData=true;
                rowData.put(headers.get(j),value);
            }
            if(hasData)
                rows.add(rowData);
        }
        return rows;
    }
}
