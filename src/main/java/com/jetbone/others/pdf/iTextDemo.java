package com.jetbone.others.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.Cell;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Chris on 2020/3/27
 */
public class iTextDemo {

    public static void main(String[] args) throws Exception {

        Document document = new Document(PageSize.A4);
        Rectangle rect = new Rectangle(40, 806, 60, 788);

        BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 12,Font.NORMAL);


        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("./demoPdf.pdf"));

//        PdfFormField form = PdfFormField.createEmpty(pdfWriter);
        document.open();


//        TextField tf = new TextField(pdfWriter, rect, "function");
//        tf.setBorderColor(BaseColor.BLACK);
//        tf.setOptions(TextField.READ_ONLY | TextField.MULTILINE);
////        tf.setBorderWidth(2);
////        tf.setTextColor(BaseColor.RED);
//        tf.setFontSize(12);
//        tf.setText("Text field");
//        pdfWriter.addAnnotation(tf.getTextField());

        Paragraph paragraph1 = new Paragraph("function: ");
        paragraph1.add(new Chunk("value1"));
        paragraph1.add(new Chunk("            "));
        paragraph1.add(new Chunk("Category: "));
        paragraph1.add(new Chunk("value2"));
        paragraph1.add(new Chunk("            "));
        paragraph1.add(new Chunk("Scope: "));
        paragraph1.add(new Chunk("中文", font));

        Paragraph paragraph2 = new Paragraph("function: ");
        paragraph2.add(new Chunk("value1"));
        paragraph2.add(new Chunk("            "));
        paragraph2.add(new Chunk("Category: "));
        paragraph2.add(new Chunk("value2"));
        paragraph2.add(new Chunk("            "));
        paragraph2.add(new Chunk("Scope: "));
        paragraph2.add(new Chunk("value3"));

        document.add(paragraph1);
        document.add(paragraph2);
        document.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(6);
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");
        table.addCell("123");

        document.add(table);



//        document.add(new Paragraph("Hello world"));
//        document.add(new Paragraph("Paragraph1"));
//        document.add(new Paragraph("Paragraph2"));

        document.close();

    }

}
