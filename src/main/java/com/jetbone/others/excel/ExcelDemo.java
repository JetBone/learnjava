package com.jetbone.others.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Chris on 2020/5/20
 */
public class ExcelDemo {

    public static void main(String[] args) throws Exception {

        InputStream is = new FileInputStream("/Users/123.xlsx");
        OutputStream os = new FileOutputStream("/Users/456.xlsx");

        Workbook workbook = new XSSFWorkbook(is);

        CellStyle inputStyle = workbook.createCellStyle();
        inputStyle.setAlignment(HorizontalAlignment.LEFT);
        CellStyle tableStyle = workbook.createCellStyle();
        tableStyle.setAlignment(HorizontalAlignment.LEFT);
        tableStyle.setBorderTop(BorderStyle.THIN);
        tableStyle.setBorderBottom(BorderStyle.THIN);
        tableStyle.setBorderLeft(BorderStyle.THIN);
        tableStyle.setBorderRight(BorderStyle.THIN);

        Sheet sheet = workbook.getSheetAt(0);

        Cell inputCell = sheet.getRow(0).getCell(1);
        inputCell.setCellStyle(inputStyle);
        inputCell.setCellValue("哪哈懊恼范德萨发");

        for (int m = 0; m < 3; m++) {
            Row row = sheet.createRow(15+m);
            for (int n = 0; n < 10; n++) {
                Cell tableCell = row.createCell(n);
                tableCell.setCellStyle(tableStyle);
                tableCell.setCellValue("第 " + m + " 行, 第 " + n + " 列");
            }
        }


        workbook.write(os);

    }
}
