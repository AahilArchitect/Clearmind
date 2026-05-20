package com.clearmind.export;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class PdfExporter {

    public static void exportToPdf(String text, String outputPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));

            document.open();

            String[] lines = text.split("\n");
            for (String line : lines) {
                document.add(new Paragraph(line));
            }

            document.close();

            System.out.println("PDF exported to: " + outputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
