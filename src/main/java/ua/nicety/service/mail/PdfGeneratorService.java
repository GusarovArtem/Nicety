package ua.nicety.service.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

            Font font1 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);

            Font font2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

//            String imageUrl = "src/main/resources/static/images/nicety-logo.png";
//            Image stamp = Image.getInstance(imageUrl);
//            stamp.setAlignment(Element.ALIGN_RIGHT);
//            doc.add(stamp);

            Paragraph title = new Paragraph("Schedule:  " + scheduleName, font1);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(16);
            doc.add(title);

            PdfContentByte cb = writer.getDirectContent();

            PdfPTable table = new PdfPTable(5);

            PdfPCell cellDay = new PdfPCell(new Phrase("Day", font2));
            cellDay.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cellName = new PdfPCell(new Phrase("Name", font2));
            cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cellTime = new PdfPCell(new Phrase("Time", font2));
            cellTime.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cellSmiles = new PdfPCell(new Phrase("Smiles", font2));
            cellSmiles.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cellDescription = new PdfPCell(new Phrase("Description", font2));
            cellDescription.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cellDay);
            table.addCell(cellName);
            table.addCell(cellTime);
            table.addCell(cellSmiles);
            table.addCell(cellDescription);

            for (Map.Entry<Day, List<EventReadDto>> entry : mapEvents.entrySet()) {
                PdfPCell cell1 = new PdfPCell(new Phrase(entry.getKey().getValue(), font2));
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setRowspan(entry.getValue().size());
                cell1.setPaddingTop((4 * entry.getValue().size()));
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


            doc.close();
            writer.flush();
            writer.close();
            return Base64Utils.encodeToString( FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            throw new RuntimeException("Pdf generation failed: " + e.getMessage());
        }
    }



}

