package ua.nicety.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import ua.nicety.database.entity.Day;
import ua.nicety.http.dto.read.EventReadDto;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.awt.Color.GREEN;

@Service
public class PdfGeneratorService {
    public static final String DEST = "schedule.pdf";

    public String generatePdf(Map<Day, List<EventReadDto>> mapEvents, String scheduleName) {
        try {
            Path fullPdfPath = Path.of(DEST);

            File file = new File(DEST);
            if (!Files.exists(fullPdfPath)) {
                Files.createFile(fullPdfPath);
            }

            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(DEST));
            doc.open();

            Paragraph messageToPrint = new Paragraph("Schedule:  " + scheduleName, new Font(Font.NORMAL, 14,
                    Font.BOLDITALIC, GREEN));

            PdfContentByte cb = writer.getDirectContent();

            PdfPTable table = new PdfPTable(5);

            for (Map.Entry<Day, List<EventReadDto>> entry : mapEvents.entrySet()) {
                PdfPCell cell1 = new PdfPCell(new Phrase(entry.getKey().getValue()));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setRowspan(entry.getValue().size());
                cell1.setVerticalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);

                for (EventReadDto event: entry.getValue()) {
                    table.addCell(event.getName());
                    table.addCell(event.getTime().toString());
                    table.addCell(event.getSmiles());
                    table.addCell(event.getDescription());
                }
            }

            table.setTotalWidth(500);

            table.writeSelectedRows(0, 10, 0, -1, 25, 700, cb);


            doc.add(messageToPrint);


            doc.close();
            writer.flush();
            writer.close();
            return Base64Utils.encodeToString( FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            throw new RuntimeException("Pdf generation failed: " + e.getMessage());
        }
    }



}

