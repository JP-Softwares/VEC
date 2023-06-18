package com.jp.tools;
import java.awt.*;
import java.awt.print.PageFormat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.text.DecimalFormat;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.jp.controle.GastosControle;
import com.jp.modelos.Gastos;
import com.jp.modelos.Veiculo;
import javafx.print.PageLayout;

import javax.swing.*;

public class GerarPDF {

    public void gerarPDFMes(String caminho, Veiculo objeto, int ano){
        GastosControle gastosControle = new GastosControle();
        // criação do documento
        Document document = new Document();
        try {
            int cont = 1;
            int LimitePagina = 27;
            double soma = 0.0;
            double TotalDoAno = 0.0;
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();
            HashMap<Integer, ArrayList> hm = new HashMap<>();
            hm = gastosControle.listarPorMes(objeto);
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Relatorio de Gastos - "+objeto.getModelo().getNome(), FontFactory.getFont(FontFactory.TIMES_BOLD, 26)));
            document.add(new Paragraph("Placa: "+objeto.getPlaca() +" | Ano de Fabricação: "+objeto.getAnoFabricacao()+ " | Ano do Modelo: "+objeto.getAnoModelo(), FontFactory.getFont(FontFactory.TIMES, 11)));
            document.add(new Paragraph("\n \nJaneiro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            if(hm.get(1) != null){
                Iterator<Gastos> listaJaneiro = hm.get(1).iterator();
                while(listaJaneiro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJaneiro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$ "+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData().toLocalDate(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 4;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String total = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+total, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }
            document.add(new Paragraph("\n \nFevereiro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(2) != null){
                Iterator<Gastos> listaFevereiro = hm.get(2).iterator();
                while(listaFevereiro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaFevereiro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalfevereiro = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalfevereiro, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }
            document.add(new Paragraph("\n \nMarço", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(3) != null){
                Iterator<Gastos> listaMarco = hm.get(3).iterator();
                while(listaMarco.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaMarco.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalMarco = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalMarco, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nAbril", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(4) != null){
                Iterator<Gastos> listaAbril = hm.get(4).iterator();
                while(listaAbril.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaAbril.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalAbril = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalAbril, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;


            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }


            document.add(new Paragraph("\n \nMaio", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(5) != null){
                Iterator<Gastos> listaMaio = hm.get(5).iterator();
                while(listaMaio.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaMaio.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalMaio = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalMaio, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;


            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }


            document.add(new Paragraph("\n \nJunho", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(6) != null){
                Iterator<Gastos> listaJunho = hm.get(6).iterator();
                while(listaJunho.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJunho.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalJunho = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalJunho, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;


            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nJulho", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(7) != null){
                Iterator<Gastos> listaJulho = hm.get(7).iterator();
                while(listaJulho.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJulho.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalJulho = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalJulho, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;


            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nAgosto", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(8) != null){
                Iterator<Gastos> listaAgosto = hm.get(8).iterator();
                while(listaAgosto.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaAgosto.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalAgosto = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalAgosto, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nSetembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(9) != null){
                Iterator<Gastos> listaSetembro = hm.get(9).iterator();
                while(listaSetembro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaSetembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalSetembro = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalSetembro, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;


            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nOutubro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(10) != null){
                Iterator<Gastos> listaOutubro = hm.get(10).iterator();
                while(listaOutubro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaOutubro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalOutubro = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalOutubro, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nNovembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(11) != null){
                Iterator<Gastos> listaNovembro = hm.get(11).iterator();
                while(listaNovembro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaNovembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalNovembro = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalNovembro, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nDezembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(12) != null){
                Iterator<Gastos> listaDezembro = hm.get(12).iterator();
                while(listaDezembro.hasNext()){
                    if(cont > LimitePagina){
                        document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaDezembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+aux1.getData(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    soma = soma + aux1.getValor();
                    cont = cont + 6;
                }
            }
            TotalDoAno = TotalDoAno + soma;
            String totalDezembro = new DecimalFormat("0.00").format(soma);
            document.add(new Paragraph("TOTAL:           | R$ "+totalDezembro, FontFactory.getFont( FontFactory.TIMES_BOLD)));
            soma = 0.0;

            if(cont > LimitePagina){
                document.newPage();
                cont = 0;
            }

            String TotalAnual = new DecimalFormat("0.00").format(TotalDoAno);
            document.add(new Paragraph("\n\n\n" + ano, FontFactory.getFont( FontFactory.TIMES_BOLD, 20)));
            document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
            document.add(new Paragraph("TOTAL:           | R$ "+TotalAnual + "   " +
                    "                                 Gerado em: ", FontFactory.getFont( FontFactory.TIMES_BOLD)));

        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        catch (Exception erro){
            erro.printStackTrace();
        }
        document.close();
    }

    public void gerarPDFTipo(String caminho, Veiculo objeto){
        GastosControle gastosControle = new GastosControle();
        // criação do documento
        Document document = new Document();
        try {
            int cont = 1;
            int LimitePagina = 23;
            double soma = 0.0;
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();

            HashMap<Integer, ArrayList> hm = new HashMap<>();
            hm = gastosControle.listarPorMes(objeto);
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Relatorio de Gastos - "+objeto.getModelo().getNome(), FontFactory.getFont(FontFactory.TIMES, 26)));

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

    private static Element Paragraph(String texto) {
        return null;
    }
}
