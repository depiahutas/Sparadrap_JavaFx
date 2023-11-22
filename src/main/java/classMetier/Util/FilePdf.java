package classMetier.Util;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FilePdf {

    static File test;

    public static void createPdf(String title, String texte, String name,String date,String Medecin) throws FileNotFoundException, DocumentException {

        Document document = new Document();
        String Realname=".\\src\\main\\java\\PDF\\"+name+"-"+date+".pdf";
        Paragraph nomMed =new Paragraph("Dr."+Medecin);
        nomMed.setAlignment(Element.ALIGN_RIGHT);

        PdfWriter.getInstance(document,new FileOutputStream(Realname));
        {
            document.open();
            document.addTitle(title);
            document.add(new Paragraph(title));
            document.add(new Paragraph());
            document.add(nomMed);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(texte));

        }
        document.close();

    }


    /*
        Ouvre le PDF dans le navigateur
        !!! BUG -> réouvre les PDF ouvert précédement dans le navigateur
     */
    public static void openPdf(String name,String date) throws IOException {

        String Realname=".\\src\\main\\java\\PDF\\"+name+"-"+date+".pdf";
         test= new File(Realname);
        // ouvre aussi les pdf des anciennes ordonnance
        Desktop.getDesktop().open(test);
    }
}
