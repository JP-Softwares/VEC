package com.jp.tools;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jp.controle.GastosControle;
import com.jp.modelos.Gastos;

public class GerarPDF {

    public static void main(String[] args) {
        GastosControle gastosControle = new GastosControle();
        // criação do documento
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\andre\\OneDrive\\Documentos\\TestePDF PI\\PDF_DevMedia.pdf"));
            document.open();

            // adicionando um parágrafo no documento
            document.add(new Paragraph("Relatorio de gastos"));
            String dados = "";
            Iterator<Gastos> listaGastos = gastosControle.listarPorMes().get(1).iterator();
            while(listaGastos.hasNext()){
                Gastos aux1 = listaGastos.next();
                dados += aux1.getTipoDeGastos().getNome() + "  |  " + aux1.getData() + "  |  " + aux1.getDescricao() + "  |  " + aux1.getValor() + "\n";
            }
            document.add(new Paragraph("Gastos de Janeiro" + "\n"+ dados));
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        catch (Exception erro){
        }
        document.close();
    }
}
