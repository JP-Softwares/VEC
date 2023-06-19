package com.jp.tools;
import java.awt.*;
import java.awt.print.PageFormat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.text.DecimalFormat;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.jp.controle.GastosControle;
import com.jp.controle.TipoDeGastosControle;
import com.jp.modelos.Gastos;
import com.jp.modelos.TipoDeGastos;
import com.jp.modelos.Veiculo;
import javafx.print.PageLayout;

import javax.swing.*;

public class GerarPDF {

    public static void gerarPDFMes(String caminho, Veiculo objeto, int ano){
        GastosControle gastosControle = new GastosControle();
        // criação do documento
        Rectangle pageSize = new Rectangle(595, 842);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        Document document = new Document(pageSize);
        try {
            int cont = 1;
            int LimitePagina = 27;
            double soma = 0.0;
            double TotalDoAno = 0.0;
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            document.open();
            HashMap<Integer, ArrayList> hm = new HashMap<>();
            hm = gastosControle.listarPorMes(objeto, ano);
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Relatorio de Gastos - " + ano, FontFactory.getFont(FontFactory.TIMES_BOLD, 26)));
            document.add(new Paragraph("Modelo: "+objeto.getModelo().getNome()+"  |  Placa: "+objeto.getPlaca() +" | Ano de Fabricação: "+objeto.getAnoFabricacao()+ " | Ano do Modelo: "+objeto.getAnoModelo() + " | Marca: "+objeto.getModelo().getMarca().getNome(), FontFactory.getFont(FontFactory.TIMES, 11)));
            document.add(new Paragraph("\n \nJaneiro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            if(hm.get(1) != null){
                Iterator<Gastos> listaJaneiro = hm.get(1).iterator();
                while(listaJaneiro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJaneiro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$ "+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }
            document.add(new Paragraph("\n \nFevereiro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(2) != null){
                Iterator<Gastos> listaFevereiro = hm.get(2).iterator();
                while(listaFevereiro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaFevereiro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }
            document.add(new Paragraph("\n \nMarço", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(3) != null){
                Iterator<Gastos> listaMarco = hm.get(3).iterator();
                while(listaMarco.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaMarco.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nAbril", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(4) != null){
                Iterator<Gastos> listaAbril = hm.get(4).iterator();
                while(listaAbril.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaAbril.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }


            document.add(new Paragraph("\n \nMaio", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(5) != null){
                Iterator<Gastos> listaMaio = hm.get(5).iterator();
                while(listaMaio.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaMaio.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }


            document.add(new Paragraph("\n \nJunho", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(6) != null){
                Iterator<Gastos> listaJunho = hm.get(6).iterator();
                while(listaJunho.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJunho.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nJulho", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(7) != null){
                Iterator<Gastos> listaJulho = hm.get(7).iterator();
                while(listaJulho.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaJulho.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nAgosto", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(8) != null){
                Iterator<Gastos> listaAgosto = hm.get(8).iterator();
                while(listaAgosto.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaAgosto.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nSetembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(9) != null){
                Iterator<Gastos> listaSetembro = hm.get(9).iterator();
                while(listaSetembro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaSetembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nOutubro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(10) != null){
                Iterator<Gastos> listaOutubro = hm.get(10).iterator();
                while(listaOutubro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaOutubro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nNovembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(11) != null){
                Iterator<Gastos> listaNovembro = hm.get(11).iterator();
                while(listaNovembro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaNovembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }

            document.add(new Paragraph("\n \nDezembro", FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
            document.add(new Paragraph("______________________________________________________________________________"));
            cont = cont + 4;
            if(hm.get(12) != null){
                Iterator<Gastos> listaDezembro = hm.get(12).iterator();
                while(listaDezembro.hasNext()){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Gastos aux1 = listaDezembro.next();
                    document.add(new Paragraph("Tipo de Gasto: | " + aux1.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Descrição:       | " + aux1.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Valor:               | R$"+aux1.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                    document.add(new Paragraph("Data:               | "+ArrumarData(aux1.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
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
                //document.newPage();
                cont = 0;
            }
            Calendar c = Calendar.getInstance();
            String TotalAnual = new DecimalFormat("0.00").format(TotalDoAno);
            document.add(new Paragraph("\n\n\n" + ano + "\n", FontFactory.getFont( FontFactory.TIMES_BOLD, 20)));
            document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
            document.add(new Paragraph("TOTAL:           | R$ "+TotalAnual + "   " +
                    "                                                              Gerado em: " + c.getTime().toLocaleString().substring(0,18), FontFactory.getFont( FontFactory.TIMES_BOLD)));
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

    public static void gerarPDFTipo(String caminho, Veiculo objeto, int ano){
        GastosControle gastosControle = new GastosControle();
        Rectangle pageSize = new Rectangle(595, 842);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        Document document = new Document(pageSize);
        try {
            int cont = 1;
            int LimitePagina = 36;
            double soma = 0.0;
            double valorFinal = 0.0;
            String MesAtual = "";
            PdfWriter.getInstance(document, new FileOutputStream(caminho));
            TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
            document.open();

            HashMap<Integer, ArrayList> hm = gastosControle.listarPorMes(objeto, ano);
            HashMap<String, ArrayList> tp = new HashMap<>();
            //coloco os tipos de gasto em uma lista de depois preencho um hashmap usando eles de key;
            ArrayList<TipoDeGastos> listaDeTipos = tipoDeGastosControle.listar();
            Iterator<TipoDeGastos> itezinho = listaDeTipos.iterator();
            while(itezinho.hasNext()){
                TipoDeGastos aux = itezinho.next();
                tp.put(aux.getNome(), null);
            }
            //Passando pelos gastos dos 12 meses usando o HashMap HM
            for(int i = 1; i < 13; i++){
                ArrayList<Gastos> lista = hm.get(i);
                //se existir um Array de Gastos relacionado com aquele Mes eu entro no IF
                if(lista != null){
                    Iterator<Gastos> indice = lista.iterator();
                    //Para cada Gasto nesse Mes eu executo esse While
                    while(indice.hasNext()){
                        Gastos atual = indice.next();
                        TipoDeGastos aux = atual.getTipoDeGastos();
                        //Usando o Tipo de Gasto como Key eu consigo checar se ja existe uma Arraylist de gastos Relacionada a ele ou não
                        if(tp.get(aux.getNome()) == null){
                            //se não existir eu crio uma vazia, coloco o gasto atual nela e guardo no HashMap
                            ArrayList<Gastos> Comgastos = new ArrayList<>();
                            Comgastos.add(atual);
                            tp.put(aux.getNome(),Comgastos);
                        }else{
                            //se ja existir eu adiciono o Gasto na Arraylist
                            tp.get(aux.getNome()).add(atual);
                        }
                    }
                }

            }
            //Neste ponto eu tenho um HashMap com os tipos de gasto como chave e uma ArrayList de Gastos relacionados a ele;
            document.add(new Paragraph("Relatorio de Gastos - " + ano, FontFactory.getFont(FontFactory.TIMES_BOLD, 26)));
            document.add(new Paragraph("Modelo: "+objeto.getModelo().getNome()+"  |  Placa: "+objeto.getPlaca() +" | Ano de Fabricação: "+objeto.getAnoFabricacao()+ " | Ano do Modelo: "+objeto.getAnoModelo() + " | Marca: "+objeto.getModelo().getMarca().getNome(), FontFactory.getFont(FontFactory.TIMES, 11)));
            Iterator<TipoDeGastos> Tipo = listaDeTipos.iterator();
            while(Tipo.hasNext()){
                TipoDeGastos aux = Tipo.next();
                if (tp.get(aux.getNome()) != null){
                    if(cont > LimitePagina){
                        //document.newPage();
                        cont = 0;
                    }
                    Iterator<Gastos> GR = tp.get(aux.getNome()).iterator();
                    soma = 0;
                    document.add(new Paragraph("\n \n" + aux.getNome(), FontFactory.getFont( FontFactory.TIMES_BOLD, 20)));
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    MesAtual = "";
                    while (GR.hasNext()){
                        Gastos gts = GR.next();
                        if(cont > LimitePagina){
                            //document.newPage();
                            cont = 0;
                        }
                        if(gts.getData().toLocalDate().getMonth().toString().equals(MesAtual)){
                            document.add(new Paragraph("Tipo de Gasto: | " + gts.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Descrição:       | " + gts.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Valor:               | R$"+gts.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Data:               | "+ArrumarData(gts.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("______________________________________________________________________________", FontFactory.getFont(FontFactory.TIMES, 10)));
                            cont = cont+5;
                        }else{
                            document.add(new Paragraph(""+ traduzir(gts.getData().toLocalDate().getMonth().toString()) , FontFactory.getFont( FontFactory.TIMES_BOLD, 15)));
                            document.add(new Paragraph("______________________________________________________________________________", FontFactory.getFont(FontFactory.TIMES, 10)));
                            document.add(new Paragraph("Tipo de Gasto: | " + gts.getTipoDeGastos().getNome(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Descrição:       | " + gts.getDescricao(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Valor:               | R$"+gts.getValor(), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("Data:               | "+ArrumarData(gts.getData()), FontFactory.getFont(FontFactory.TIMES, 9)));
                            document.add(new Paragraph("______________________________________________________________________________", FontFactory.getFont(FontFactory.TIMES, 10)));
                            cont = cont+8;
                        }
                        soma = soma + gts.getValor();

                        MesAtual = gts.getData().toLocalDate().getMonth().toString();
                    }
                    document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
                    String totalParcial = new DecimalFormat("0.00").format(soma);
                    document.add(new Paragraph("TOTAL "+aux.getNome()+": R$ "+totalParcial+ "\n", FontFactory.getFont( FontFactory.TIMES_BOLD)));
                    valorFinal = valorFinal + soma;
                    cont = cont+ 3;
                }
            }
            Calendar c = Calendar.getInstance();
            String TotalAnual = new DecimalFormat("0.00").format(valorFinal);
            document.add(new Paragraph("\n\n\n" + ano, FontFactory.getFont( FontFactory.TIMES_BOLD, 20)));
            document.add(new Paragraph("_______________________________________________________________________________________", FontFactory.getFont( FontFactory.TIMES)));
            document.add(new Paragraph("TOTAL:           | R$ "+TotalAnual + "   " +
                    "                                                              Gerado em: " + c.getTime().toLocaleString().substring(0,18), FontFactory.getFont( FontFactory.TIMES_BOLD)));

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
    private static String traduzir(String mes){
        switch (mes){
            case "JANUARY":
                return "Janeiro";
            case "FEBRUARY":
                return "Fevereiro";
            case "MARCH":
                return "Março";
            case "APRIL":
                return "Abril";
            case "MAY":
                return "Maio";
            case "JUNE":
                return "Junho";
            case "JULY":
                return "Julho";
            case "AUGUST":
                return "Agosto";
            case "SEPTEMBER":
                return "Setembro";
            case "OCTOBER":
                return "Outubro";
            case "NOVEMBER":
                return "Novembro";
            case "December":
                return "Dezembro";
            default:
                break;
        }
        return null;
    }

    public static String ArrumarData(Date data){

        String dia = data.toLocalDate().getDayOfMonth() + "";
        if(dia.length() < 2){
            dia = "0"+dia;
        }
        String mes = data.toLocalDate().getMonthValue() + "";
        if(mes.length() < 2){
            mes = "0"+mes;
        }
        String ano = data.toLocalDate().getYear() + "";
        return dia+ "/" + mes + "/" + ano;
    }

    private static Element Paragraph(String texto) {
        return null;
    }

}
