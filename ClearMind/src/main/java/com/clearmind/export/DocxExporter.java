package com.clearmind.export;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocxExporter {

    public static void exportToDocx(String text, String outputPath) {
        try (XWPFDocument doc = new XWPFDocument()) {

            String[] lines = text.split("\n");

            for (String line : lines) {
                XWPFParagraph paragraph = doc.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(line);
            }

            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                doc.write(out);
            }

            System.out.println("DOCX exported to: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
