package com.jetbone.others.hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris
 * @date 2020-09-21
 */
public class Test {

    public static void main(String[] args) throws IOException {

        String sourcePath = "~/BS主要数据导入模板(风险准备).xlsx";
        String destPath = "~/BS.xlsx";

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        ExcelReader excelReader = ExcelUtil
                .getReader(sourceFile);
        ExcelWriter excelWriter = excelReader.getWriter();
        excelWriter.setSheet("存货风险准备");
        excelWriter.setDestFile(destFile);

        List<String> bRows = new ArrayList<>();
        bRows.add("测试1");
        bRows.add("测试2");
        bRows.add("测试3");
        bRows.add("测试3");
        bRows.add("测试3");
        bRows.add("测试3");
        bRows.add("测试3");
        bRows.add("测试3");

        List<String> cRows = new ArrayList<>();
        cRows.add("测试1");
        cRows.add("测试2");
        cRows.add("测试3");
        cRows.add("测试3");
        cRows.add("测试3");
        cRows.add("测试3");
        cRows.add("测试3");
        cRows.add("测试3");
        cRows.add("测试3");

        Cell originCCell = excelWriter.getCell(1, 21);
        Cell originDCell = excelWriter.getCell(1, 25);
        Cell originECell = excelWriter.getCell(1, 26);
        Cell originFCell = excelWriter.getCell(1, 27);
        Cell originTailCell = excelWriter.getCell(1, 28);


        calc(excelWriter, 12, bRows, cRows, 0d, originCCell, originDCell, originECell, originFCell, originTailCell);

    }

    /**
     * 动态生成excel后半部分 从计算b行开始
     * @param excelWriter writer
     * @param calcBRowNum b行行数，代码行数
     * @param bRows list，需要生成的行标题
     * @param cRows list，需要生成的行标题
     * @param e 默认e的数值
     * @param originCCell 原c的标题cell
     * @param originDCell 原d的标题cell
     * @param originECell 原e的标题cell
     * @param originFCell 原f的标题cell
     * @param originTailCell 原末尾cell
     */
    public static ExcelWriter calc(ExcelWriter excelWriter, int calcBRowNum, List<String> bRows, List<String> cRows, double e,
                            Cell originCCell,
                            Cell originDCell,
                            Cell originECell,
                            Cell originFCell,
                            Cell originTailCell) {

        // 计算新的c行数
        int calcCRowNum = calcBRowNum + bRows.size() + 1;
        // 计算新的d行数
        int calcDRowNum = calcCRowNum + cRows.size() + 1;
        // 计算新的f行数
        int calcFRowNum = calcDRowNum + 2;

        // 计算新共识
        String calcBFormula = "SUM(D" + (calcBRowNum + 2) + ":D" + calcCRowNum + ")";
        String calcCFormula = "SUM(D" + (calcCRowNum + 2) + ":D" + calcDRowNum + ")";
        String calcDFormula = "SUM(D" + (calcBRowNum + 1) + ",D" + (calcBRowNum - 1) + ")-D" + (calcCRowNum + 1);
        String calcFFormula = "D" + calcFRowNum + "-D" + (calcDRowNum + 1);

        // 获取一些通用的style
        CellStyle formulaStyle = excelWriter.getCell(3, calcBRowNum).getCellStyle();
        CellStyle titleStyle = excelWriter.getCell(1, calcBRowNum + 1).getCellStyle();
        CellStyle blankStyle = excelWriter.getCell(2, calcBRowNum + 1).getCellStyle();
        CellStyle numStyle = excelWriter.getCell(3, calcBRowNum + 1).getCellStyle();

        // 设置b
        excelWriter.getCell(3, calcBRowNum).setCellFormula(calcBFormula);

        // 设置c
        Cell actualCCell = excelWriter.getCell(1, calcCRowNum, true);
        actualCCell.setCellValue(originCCell.getStringCellValue());
        actualCCell.setCellStyle(originCCell.getCellStyle());
        Cell actualCBlankCell = excelWriter.getCell(2, calcCRowNum, true);
        actualCBlankCell.setBlank();
        actualCBlankCell.setCellStyle(blankStyle);
        Cell actualCFormulaCell = excelWriter.getCell(3, calcCRowNum, true);
        actualCFormulaCell.setCellFormula(calcCFormula);
        actualCFormulaCell.setCellStyle(formulaStyle);

        // 设置d
        Cell actualDCell = excelWriter.getCell(1, calcDRowNum, true);
        actualDCell.setCellValue(originDCell.getStringCellValue());
        actualDCell.setCellStyle(originDCell.getCellStyle());
        Cell actualDBlankCell = excelWriter.getCell(2, calcDRowNum, true);
        actualDBlankCell.setBlank();
        actualDBlankCell.setCellStyle(blankStyle);
        Cell actualDFormulaCell = excelWriter.getCell(3, calcDRowNum, true);
        actualDFormulaCell.setCellFormula(calcDFormula);
        actualDFormulaCell.setCellStyle(formulaStyle);

        // 设置e
        Cell actualECell = excelWriter.getCell(1, calcFRowNum - 1, true);
        actualECell.setCellValue(originECell.getStringCellValue());
        actualECell.setCellStyle(originECell.getCellStyle());
        Cell actualEBlankCell = excelWriter.getCell(2, calcFRowNum - 1, true);
        actualEBlankCell.setBlank();
        actualEBlankCell.setCellStyle(blankStyle);
        Cell actualEFormulaCell = excelWriter.getCell(3, calcFRowNum - 1, true);
        actualEFormulaCell.setCellFormula(null);
        actualEFormulaCell.setCellValue(e);
        actualEFormulaCell.setCellStyle(formulaStyle);

        // 设置f
        Cell actualFCell = excelWriter.getCell(1, calcFRowNum, true);
        actualFCell.setCellValue(originFCell.getStringCellValue());
        actualFCell.setCellStyle(originFCell.getCellStyle());
        Cell actualFBlankCell = excelWriter.getCell(2, calcFRowNum, true);
        actualFBlankCell.setBlank();
        actualFBlankCell.setCellStyle(blankStyle);
        Cell actualFFormulaCell = excelWriter.getCell(3, calcFRowNum, true);
        actualFFormulaCell.setCellFormula(calcFFormula);
        actualFFormulaCell.setCellStyle(formulaStyle);

        // 设置最后一行数据
        Cell actualTailCell = excelWriter.getCell(1, calcFRowNum + 1, true);
        actualTailCell.setCellValue(originTailCell.getStringCellValue());
        actualTailCell.setCellStyle(originTailCell.getCellStyle());
        Cell actualTailBlankCell = excelWriter.getCell(2, calcFRowNum + 1, true);
        actualTailBlankCell.setBlank();
        actualTailBlankCell.setCellStyle(blankStyle);
        Cell actualTailFormulaCell = excelWriter.getCell(3, calcFRowNum + 1, true);
        actualTailFormulaCell.setBlank();
        actualTailFormulaCell.setCellStyle(blankStyle);


        // 填充b数据
        for (int i = calcBRowNum + 1, j = 0; i < calcCRowNum; i++, j++) {
            Cell titleCell = excelWriter.getCell(1, i, true);
            Cell blankCell = excelWriter.getCell(2, i, true);
            Cell numCell = excelWriter.getCell(3, i, true);
            titleCell.setCellValue((i + 1) + ":" + bRows.get(j));
            titleCell.setCellStyle(titleStyle);
            blankCell.setBlank();
            blankCell.setCellStyle(blankStyle);
            numCell.setBlank();
            numCell.setCellStyle(numStyle);
        }

        // 填充c数据
        for (int i = calcCRowNum + 1, j = 0; i < calcDRowNum; i++, j++) {
            Cell titleCell = excelWriter.getCell(1, i, true);
            Cell blankCell = excelWriter.getCell(2, i, true);
            Cell numCell = excelWriter.getCell(3, i, true);
            titleCell.setCellValue((i + 1) + ":" + cRows.get(j));
            titleCell.setCellStyle(titleStyle);
            blankCell.setBlank();
            blankCell.setCellStyle(blankStyle);
            numCell.setBlank();
            numCell.setCellStyle(numStyle);
        }

        // 如果最终行数比原来模版行数少，则需要将模版里多余的行数清空，取四个sheet的最大行30行
        if (calcFRowNum + 2 < 30) {
            for (int i = calcFRowNum + 2; i <= 30; i++) {
                Cell titleCell = excelWriter.getCell(1, i);
                Cell blankCell = excelWriter.getCell(2, i);
                Cell numCell = excelWriter.getCell(3, i);
                if (titleCell != null) {
                    titleCell.setBlank();
                    titleCell.setCellStyle(blankStyle);
                }
                if (blankCell != null) {
                    blankCell.setBlank();
                    blankCell.setCellStyle(blankStyle);
                }
                if (numCell != null) {
                    numCell.setBlank();
                    numCell.setCellStyle(blankStyle);
                }
            }
        }

        return excelWriter;
    }
}
