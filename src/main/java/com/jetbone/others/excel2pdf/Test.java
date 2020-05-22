package com.jetbone.others.excel2pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * Created by Chris on 2020/5/22
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Document document = new Document(PageSize.A4.rotate());
//
        BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//        Font font = new Font(bfChinese, 12, Font.NORMAL);
//
//
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("./demoPdf.pdf"));



        InputStream is = new FileInputStream("/Users/222.xlsx");

        Workbook workbook = new XSSFWorkbook(is);

        Sheet sheet = workbook.getSheetAt(0);

        PdfPTable table = new Test().buildPdfTable(sheet, bfChinese);
        table.setKeepTogether(true);
        table.setWidthPercentage(100);
//        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        document.open();
        document.add(table);

        document.close();

    }

    private PdfPTable buildPdfTable(Sheet sheet, BaseFont baseFont) {
        PdfPTable table = new PdfPTable(10);

        List<CellRangeAddress> cellRangeAddressList = sheet.getMergedRegions();

        int rows = sheet.getPhysicalNumberOfRows();
        System.out.println("real row num: " + rows);

        for (int m = 0; m < rows; m++) {
            Row row = sheet.getRow(m);
            int columns = row.getPhysicalNumberOfCells();
            for (int n = 0; n < columns; n++) {
                Cell cell = row.getCell(n);
                CellRangeAddress cellRangeAddress = getCellRangeAddress(cellRangeAddressList, m, n);
                PdfPCell pdfPCell = getPdfCell(cell, baseFont);
                if (cellRangeAddress != null) {
                    int colspan = cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn() + 1;
                    int rowspan = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                    pdfPCell.setColspan(colspan);
                    pdfPCell.setRowspan(rowspan);
                    n = n + colspan - 1;
                }
                table.addCell(pdfPCell);
            }
        }

        return table;
    }

    private CellRangeAddress getCellRangeAddress(List<CellRangeAddress> cellRangeAddressList, int rowIndex, int columnIndex) {
        for (CellRangeAddress cellRangeAddress : cellRangeAddressList) {
            if (cellRangeAddress.getFirstRow() == rowIndex && cellRangeAddress.getFirstColumn() == columnIndex) {
                return cellRangeAddress;
            }
        }
        return null;
    }

    private PdfPCell getPdfCell(Cell cell, BaseFont baseFont) {

        PdfPCell pdfCell = new PdfPCell();

        XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();

        Phrase phrase = new Phrase(cell.getStringCellValue(), getFont(cellStyle.getFont(), baseFont));
        pdfCell.setPhrase(phrase);

        pdfCell.setHorizontalAlignment(getHorizontalAlignment(cellStyle));
        pdfCell.setVerticalAlignment(getVerticalAlignment(cellStyle));
        setBorderWidth(pdfCell, cellStyle);

        return pdfCell;
    }

    /**
     * 不支持字体颜色
     * @param baseFont
     * @param xssfFont
     * @return
     */
    private Font getFont(XSSFFont xssfFont, BaseFont baseFont) {
        Font font = new Font(baseFont, 12, Font.NORMAL);
        if (xssfFont.getBold()) {
            font.setStyle(Font.BOLD);
        }

        return font;
    }

    private int getVerticalAlignment(CellStyle cellStyle) {
        VerticalAlignment align = cellStyle.getVerticalAlignment();
        if (align.equals(VerticalAlignment.BOTTOM)) {
            return Element.ALIGN_BOTTOM;
        }
        if (align.equals(VerticalAlignment.CENTER)) {
            return Element.ALIGN_MIDDLE;
        }
        if (align.equals(VerticalAlignment.JUSTIFY)) {
            return Element.ALIGN_JUSTIFIED;
        }
        if (align.equals(VerticalAlignment.TOP)) {
            return Element.ALIGN_TOP;
        }
        return 0;
    }

    private int getHorizontalAlignment(CellStyle cellStyle) {
        HorizontalAlignment align = cellStyle.getAlignment();
        if (align.equals(HorizontalAlignment.LEFT)) {
            return Element.ALIGN_LEFT;
        }
        if (align.equals(HorizontalAlignment.RIGHT)) {
            return Element.ALIGN_RIGHT;
        }
        if (align.equals(HorizontalAlignment.JUSTIFY)) {
            return Element.ALIGN_JUSTIFIED;
        }
        if (align.equals(HorizontalAlignment.CENTER)) {
            return Element.ALIGN_CENTER;
        }
        return 0;
    }

    private void setBorderWidth(PdfPCell pdfPCell, CellStyle cellStyle) {
        if (cellStyle.getBorderTop().equals(BorderStyle.NONE)) {
            pdfPCell.setBorderWidthTop(0);
        }
        if (cellStyle.getBorderBottom().equals(BorderStyle.NONE)) {
            pdfPCell.setBorderWidthBottom(0);
        }
        if (cellStyle.getBorderLeft().equals(BorderStyle.NONE)) {
            pdfPCell.setBorderWidthLeft(0);
        }
        if (cellStyle.getBorderRight().equals(BorderStyle.NONE)) {
            pdfPCell.setBorderWidthRight(0);
        }
    }

}
