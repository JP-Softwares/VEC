/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.visao;

import com.jp.controle.*;

/**
 *
 * @author Woly
 */
public class Run {

    public static App app;

    public static TelaPrincipal telaPrincipal;

    public static TelaHome telaHome;

    public static TelaVeiculos telaVeiculos;

    public static TelaProprietarios telaProprietarios;

    public static TelaTipoDeGasto telaTipoDeGasto;

    public static TelaGastos telaGastos;

    public static TelaGrafico telaGrafico;

    public static MarcasEdit marcasEdit;

    public static ModelosEdit modelosEdit;

    public static VeiculosEdit veiculosEdit;

    public static ProprietariosEdit proprietariosEdit;

    public static TipoDeGastosEdit tipoDeGastosEdit;

    public static GastosEdit gastosEdit;


    public static IMarcaControle marcaControle;
    public static IModeloControle modeloControle;
    public static IVeiculoControle veiculoControle;
    public static IProprietarioControle proprietarioControle;
    public static ITipoDeGastosControle tipoDeGastosControle;
    public static IGastosControle gastosControle;

    public static void main(String[] args){
        try {
            marcaControle = new MarcaControle();
            modeloControle = new ModeloControle();
            veiculoControle = new VeiculoControle();
            proprietarioControle = new ProprietarioControle();
            tipoDeGastosControle = new TipoDeGastosControle();
            gastosControle = new GastosControle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        App.main(args);
    }
}
