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

    public static MarcasEdit marcasEdit;

    public static ModelosEdit modelosEdit;

    public static VeiculosEdit veiculosEdit;


    public static IMarcaControle marcaControle = null;
    public static IModeloControle modeloControle = null;
    public static IVeiculoControle veiculoControle = null;

    public static IProprietarioControle proprietarioControle = null;

    public static void main(String[] args){
        new Thread(() -> {
            try {
                marcaControle = new MarcaControle();
                modeloControle = new ModeloControle();
                veiculoControle = new VeiculoControle();
                proprietarioControle = new ProprietarioControle();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        App.main(args);
    }
}
