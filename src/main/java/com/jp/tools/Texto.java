/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.tools;

/**
 *
 * @author Woly
 */
public class Texto {
    
    public static enum tipoDoTexto{
        STRING, INT, INT_STRING, STRING_SPACE, INT_STRING_SPACE
    }
    
    public static String toString(char vetor[]) throws Exception{
        if(vetor.length > 0){
            String texto = "";
            for(int i = 0; i < vetor.length; i++){
                texto += vetor[i];
            }

            return texto;
        }else throw new Exception("Vetor vazio");
    }
    
    public static String Maiuscula(String texto){
        char letras[] = texto.toCharArray();
        texto = "";
        texto = Character.toUpperCase(letras[0]) + "";
        
        for(int i = 1; i < letras.length; i++){
            if(letras[i] == ' '){
                texto += letras[i];
                texto += Character.toUpperCase(letras[i+1]);
                i = i+1;
            }else texto += Character.toLowerCase(letras[i]);
            
        }
        return texto;
    }
    
    public static String[] MaiusculaEnum(Enum[] enumeros){
        
        String valores[] = new String[enumeros.length];
        for(int i = 0; i < enumeros.length; i++){
            valores[i] = Maiuscula(enumeros[i].name());
        }
        
        return valores;
    }
    
    public static void validarString(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, boolean toUpperCase, char... charAceitos){
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            jTextField.setText(Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.STRING, toUpperCase, charAceitos));
        }
    }
    
    public static void validarNumero(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, char... charAceitos){
        if(qtd == 0 || qtd > 10) qtd = 10;
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            jTextField.setText(Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.INT, false, charAceitos));
        }
    }
    
    public static void validarNumeroReal(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, char... charAceitos){
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            String texto = Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.INT, false, charAceitos);
            if(!texto.equals("")){
                double numero = Long.parseLong(texto)/100.0;
                jTextField.setText(Numero.real(numero) + "");
            }else jTextField.setText(Numero.real(0));
        }
    }
    
    public static void validarLetrasENumeros(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, boolean toUpperCase, char... charAceitos){
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            jTextField.setText(Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.INT_STRING, toUpperCase, charAceitos));
        }
    }
    
    public static void validarLetrasEEspaco(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, boolean toUpperCase, char... charAceitos){
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            jTextField.setText(Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.STRING_SPACE, toUpperCase, charAceitos));
        }
    }
    
    public static void validarLetrasEspacoENumero(javax.swing.JTextField jTextField, int qtd, java.awt.event.KeyEvent evt, boolean toUpperCase, char... charAceitos){
        char c = evt.getKeyChar();
        if(c != ''){
            evt.consume();
            jTextField.setText(Texto.validadorX(jTextField.getText() + c, qtd, tipoDoTexto.INT_STRING_SPACE, toUpperCase, charAceitos));
        }
    }
    
    private static String validadorX(String texto, int qtd, tipoDoTexto tipo, boolean toUpperCase, char... charAceitos){
        char letras[] = texto.toCharArray();
        
        int cont = 0;
        boolean qtdIlimitada = qtd == 0;
        texto = "";

        for(int i = 0; i < letras.length; i++){
            boolean positivo = false;
            if(charAceitos != null){
                for(int j = 0; j < charAceitos.length; j++){
                    int num = Character.valueOf(letras[i]).compareTo(charAceitos[j]);
                    if(num == 0 || num == -8166){
                        texto += charAceitos[j];
                        break;
                    }
                }
            }
            
            
            if(toUpperCase) letras[i] = Character.toUpperCase(letras[i]);
            switch (tipo) {
                case INT:
                    positivo = Character.isDigit(letras[i]);
                    break;
                case STRING:
                    positivo = Character.isLetter(letras[i]);
                    break;
                case INT_STRING:
                    positivo = Character.isLetterOrDigit(letras[i]);
                    break;
                case STRING_SPACE:
                    positivo = Character.isLetter(letras[i]) || letras[i] == ' ';
                    break;
                case INT_STRING_SPACE:
                    positivo = Character.isLetterOrDigit(letras[i]) || letras[i] == ' ';
                    break;
            }
            if(positivo){
                texto += letras[i];
                cont++;
                if(cont == qtd && !qtdIlimitada) break;
            }
        }
//        if(apagar){
//            texto = "";
//            for(int i = 0; i < letras.length - 1; i++){
//                texto += letras[i];
//            }
//        }else{
//            
//        }
        return texto;
    }
}
