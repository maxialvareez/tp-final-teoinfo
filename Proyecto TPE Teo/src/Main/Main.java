package Main;

import Ejercicio1.*;
import Ejercicio2.*;
import Ejercicio3.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.log;
import java.util.Scanner;



public class Main {

    //Se utiliza para obtener el codigo Huffman.
    public static void codificar(Nodo raiz, String str,ArrayList<String> codificacionEnBits,ArrayList<Double> cotizaciones,ArrayList<Double> entropia) {
        if (raiz.getLeft() == null && raiz.getRight() == null) {
            System.out.println(raiz.getPrecio() + ":" + str);
            codificacionEnBits.add(str);
            cotizaciones.add(raiz.getPrecio());
            entropia.add(raiz.getProb());
            entropia.add((double)str.length());
            return;
        }
        codificar(raiz.getLeft(), str + '0',codificacionEnBits,cotizaciones,entropia);
        codificar(raiz.getRight(), str + '1',codificacionEnBits,cotizaciones,entropia);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

    //------------------------- EJERCICIO 1 -------------------------
    
    //Nueva señal indicando si el precio sube, baja o se mantiene.
        Scanner input1 = new Scanner(new File("./ArchivosCatedra/BTC.txt")); 
        Scanner input2 = new Scanner(new File("./ArchivosCatedra/ETH.txt")); 
        Scanner input3 = new Scanner(new File("./ArchivosCatedra/BTC.txt")); 
        Scanner input4 = new Scanner(new File("./ArchivosCatedra/ETH.txt"));
        
        int [] cambiosBtc = new int[1000]; 
        int [] cambiosEth = new int[1000];
        Canal c1 = new Canal();
        cambiosBtc = c1.obtenerCambios(input1); //Obtengo nueva señal para Btc con 0, 1 o 2 dependiendo si baja, se mantiene o sube la cotizaciones respecto de la anterior.
        cambiosEth = c1.obtenerCambios(input2); //Obtengo nueva señal para Eth con 0, 1 o 2 dependiendo si baja, se mantiene o sube la cotizaciones respecto de la anterior.
        System.out.println("");
        System.out.println("Nueva señal para BTC: ");
        for(int i=0;i<1000;i++){
            System.out.print(cambiosBtc[i]);
        }
        System.out.println("");
        
        System.out.println("Nueva señal para ETH: ");
        for(int i=0;i<1000;i++){
            System.out.print(cambiosEth[i]);
        }
        System.out.println("");
//1-A)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 1-A");
System.out.println("--------------");
System.out.println("");

    double[][] MatrizBtc = new double[3][3];
    double[][] MatrizEth = new double[3][3];
    double[][] MatrizBtcAcum = new double[3][3];
    double[][] MatrizEthAcum = new double[3][3];
    MatrizDePasaje m = new MatrizDePasaje(input3);
    MatrizDePasaje m2 = new MatrizDePasaje(input4);
    MatrizBtc = m.llenarMatriz();
    MatrizEth = m2.llenarMatriz();

    System.out.println("Matriz de pasaje para BTC:");   
    for(int i = 0;i<3;i++){
            System.out.print("|");
            for(int t = 0;t<3;t++){
                System.out.print (MatrizBtc[i][t] + "|");
            }System.out.println("");
    }
    System.out.println("");
    System.out.println("Matriz de pasaje para Eth:");       
    for(int i = 0;i<3;i++){
            System.out.print("|");
            for(int t = 0;t<3;t++){
                System.out.print (MatrizEth[i][t] + "|");
            }System.out.println("");
    }    
    
    MatrizBtcAcum = m.getMatrizAcumulada();
    System.out.println("");
    System.out.println("Matriz de pasaje acumulada de BTC:");   
    for(int i = 0;i<3;i++){
            System.out.print("|");
            for(int t = 0;t<3;t++){
                System.out.print (MatrizBtcAcum[i][t] + "|");
            }System.out.println("");
    }

    MatrizEthAcum = m2.getMatrizAcumulada();
    System.out.println("");
    System.out.println("Matriz de pasaje acumulada de ETH:");   
    for(int i = 0;i<3;i++){
            System.out.print("|");
            for(int t = 0;t<3;t++){
                System.out.print (MatrizEthAcum[i][t] + "|");
            }System.out.println("");
    }
   
//Conversion de archivo de texto de cotizaciones a arreglo:
    archivoToArreglo archivo = new archivoToArreglo();
    double [] emisionesEth = new double[1000];
    double [] emisionesBtc = new double[1000];
    emisionesEth = archivo.obtenerEmisiones("eth");
    emisionesBtc= archivo.obtenerEmisiones("btc");

//1-B)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 1-B");
System.out.println("--------------");
System.out.println("");
//Autocorrelacion para BTC
    Autocorrelacion a = new Autocorrelacion(1,50);
    double[] autocorrelacionBtc = new double[1000];
    a.calcularAutoCorrelacion(emisionesBtc);
    autocorrelacionBtc = a.get_Autocorrelacion();
    System.out.println("Autocorrelacion para BTC:");
    for (int j=1;j<autocorrelacionBtc.length;j++)
            System.out.println("tau"+" "+j+": "+autocorrelacionBtc[j]);
        System.out.println("");
        System.out.println("Coeficiente de autoCorrelacion para BTC:");
        
//Coeficiente de autocorrelacion para BTC
    double[] coef_Autocorrelacion_Btc = new double[1000];
    coef_Autocorrelacion_Btc = a.get_Coef_Autocorrelacion();
    for (int j=1;j<autocorrelacionBtc.length;j++)
            System.out.println("tau"+" "+j+": "+coef_Autocorrelacion_Btc[j]);
    
//Autocorrelacion para ETH
    double[] autocorrelacionEth = new double[1000];
    a.calcularAutoCorrelacion(emisionesEth);
    autocorrelacionEth = a.get_Autocorrelacion();
    System.out.println("");
    System.out.println("Autocorrelacion para ETH:");
    for (int j=1;j<autocorrelacionEth.length;j++)
            System.out.println("tau"+" "+j+": "+autocorrelacionEth[j]); 
    
//Coeficiente de autocorrelacion para ETH
    System.out.println("");
    System.out.println("Coeficiente de autoCorrelacion para ETH:");
    double[] coef_Autocorrelacion_Eth = new double[1000];
    coef_Autocorrelacion_Eth = a.get_Coef_Autocorrelacion();
    for (int j=1;j<autocorrelacionBtc.length;j++)
            System.out.println("tau"+" "+j+": "+coef_Autocorrelacion_Eth[j]);
    
//1-C)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 1-C");
System.out.println("--------------");  
System.out.println("");

    int [] tiempos = {0,50,100,150,200};
//BTC ETH
    CorrelacionCruzada c = new CorrelacionCruzada(tiempos,emisionesBtc,emisionesEth);
    double [] correlacionCruzada = new double[5];
    double [] coefCorrelacionCruzada = new double[5];
    c.calcularCorrelacionCruzada();
    correlacionCruzada = c.get_correlacion_cruzada();
    coefCorrelacionCruzada = c.get_coef_correlacion_cruzada();
        System.out.println("Correlacion cruzada BTC ETH:");  
        for(int p=0;p<correlacionCruzada.length;p++)
                System.out.println("tau "+tiempos[p]+": "+correlacionCruzada[p]);
        System.out.println("");
        System.out.println("Coeficiente de correlacion cruzada BTC ETH:");
        for(int p=0;p<coefCorrelacionCruzada.length;p++)
                System.out.println("tau "+tiempos[p]+": "+coefCorrelacionCruzada[p]);
        
//ETH BTC
    CorrelacionCruzada c2 = new CorrelacionCruzada(tiempos,emisionesEth,emisionesBtc);
    double [] correlacionCruzada2 = new double[5];
    double [] coefCorrelacionCruzada2 = new double[5];
    c2.calcularCorrelacionCruzada();
    correlacionCruzada2 = c2.get_correlacion_cruzada();
    coefCorrelacionCruzada2 = c2.get_coef_correlacion_cruzada();
    System.out.println("");
    System.out.println("Correlacion cruzada ETH BTC:");  
        for(int p=0;p<correlacionCruzada2.length;p++)
                System.out.println("tau "+tiempos[p]+": "+correlacionCruzada2[p]);
    System.out.println("");
    System.out.println("Coeficiente de correlacion cruzada ETH BTC:");
        for(int p=0;p<coefCorrelacionCruzada2.length;p++)
                System.out.println("tau "+tiempos[p]+": "+coefCorrelacionCruzada2[p]);
    //------------------------- EJERCICIO 2 -------------------------
   
//2-A)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 2-A");
System.out.println("--------------");  
System.out.println("");
        //Distribucion de probabilidades para BTC:
        ArrayList<Double> CotizacionesLeidasBtc = new ArrayList<>(); //Arreglo para ir anotando las cotizaciones para las cuales ya calcule su probabilidad de emision. De esta forma me aseguro de no repetir 
        ProbabilidadesDeCotizacion probabilidadBtc = new ProbabilidadesDeCotizacion(emisionesBtc);
        HashMap<Double,Double> cotYprobBtc = new HashMap<Double,Double>();
        cotYprobBtc = probabilidadBtc.getProbabilidadYcotizacion();
        System.out.println("Distribucion de probabilidades para BTC:"); 
        for(int i=0; i<emisionesBtc.length;i++){
            if (!CotizacionesLeidasBtc.contains(emisionesBtc[i])){
            CotizacionesLeidasBtc.add(emisionesBtc[i]);
            System.out.println("Valor de cotizacion : "+emisionesBtc[i]+", probabilidad de emision: "+cotYprobBtc.get(emisionesBtc[i]));
            }}   
        
        //Distribucion de probabilidades para ETH:
        ArrayList<Double> CotizacionesLeidasEth = new ArrayList<>(); //Arreglo para ir anotando las cotizaciones para las cuales ya calcule su probabilidad de emision. De esta forma me aseguro de no repetir 
        ProbabilidadesDeCotizacion probabilidadEth = new ProbabilidadesDeCotizacion(emisionesEth);
        HashMap<Double,Double> cotYprobEth = new HashMap<Double,Double>();
        cotYprobEth = probabilidadEth.getProbabilidadYcotizacion();
        System.out.println("");
        System.out.println("Distribucion de probabilidades para ETH:");  
        for(int i=0; i<emisionesEth.length;i++){
            if (!CotizacionesLeidasEth.contains(emisionesEth[i])){
            CotizacionesLeidasEth.add(emisionesEth[i]);
            System.out.println("Valor de cotizacion : "+emisionesEth[i]+", probabilidad de emision: "+cotYprobEth.get(emisionesEth[i]));
            }}   
    
//2-B)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 2-B");
System.out.println("--------------");  
System.out.println("");
//CODIFICACION CON EL METODO DE HUFFMAN SEMI-ESTATICO PARA BTC:
System.out.println("CODIFICACION HUFFMAN SEMI-ESTATICO PARA BTC:"); 
System.out.println("");

        ArrayList <String> codificacionEnBitsBtc = new ArrayList<>();
        ArrayList <Double> cotizacionesBtc = new ArrayList<>();
        ArrayList<Double> entropiaBtc = new ArrayList<>();
        PriorityQueue<Nodo> q = new PriorityQueue<Nodo>(1000, (Comparator<? super Nodo>) new Comparador());
        
        for (int i = 0; i <CotizacionesLeidasBtc.size(); i++) {
            Nodo nodo = new Nodo();
            nodo.setPrecio(CotizacionesLeidasBtc.get(i));
            nodo.setProb(cotYprobBtc.get(CotizacionesLeidasBtc.get(i)));
            nodo.setLeft(null);
            nodo.setRight(null);
            q.add(nodo);
        }
        Nodo raiz = null;

        while (q.size() > 1) {  
            Nodo x = q.peek();
            q.poll();       
            Nodo y = q.peek();
            q.poll();           
            Nodo l = new Nodo();
            l.setProb(x.getProb() + y.getProb());
            l.setPrecio(-1);
            l.setLeft(x);
            l.setRight(y);
            raiz = l;
            q.add(l);            
        }
        codificar(raiz,"",codificacionEnBitsBtc,cotizacionesBtc,entropiaBtc);

        //Generacion de arreglo en bits de los mil valores de cotizacion.
        ArrayList<String> cotizacionEnBitsBtc = new ArrayList<>();
        double aux3=0;
        String aux4;
        for(int i=0;i<1000;i++){
            aux3=emisionesBtc[i];
            aux4 = codificacionEnBitsBtc.get(cotizacionesBtc.indexOf(aux3));
            cotizacionEnBitsBtc.add(aux4);
        }
        
        //Generacion de la cabecera para Huffman semi-estatico
        ArrayList<Double> cabeceraBtc = new ArrayList<>();
        cabeceraBtc.add(1000.0);
        for(int t=0;t<CotizacionesLeidasBtc.size();t++){
            cabeceraBtc.add(CotizacionesLeidasBtc.get(t));
            cabeceraBtc.add(cotYprobBtc.get(CotizacionesLeidasBtc.get(t))*1000);   
        }

    System.out.println("");  


    
//CODIFICACION CON EL METODO DE HUFFMAN-SEMI ESTATICO PARA ETH:
System.out.println("");
System.out.println("CODIFICACION HUFFMAN SEMI-ESTATICO PARA ETH:"); 
System.out.println("");

        ArrayList <String> codificacionEnBitsEth = new ArrayList<>();
        ArrayList <Double> cotizacionesEth = new ArrayList<>();
        ArrayList<Double> entropiaEth = new ArrayList<>();
        PriorityQueue<Nodo> q1 = new PriorityQueue<Nodo>(1000, (Comparator<? super Nodo>) new Comparador());
                
        for (int i = 0; i <CotizacionesLeidasEth.size(); i++) {
            Nodo nodo = new Nodo();
            nodo.setPrecio(CotizacionesLeidasEth.get(i));
            nodo.setProb(cotYprobEth.get(CotizacionesLeidasEth.get(i)));
            nodo.setLeft(null);
            nodo.setRight(null);
            q1.add(nodo);
        }
        Nodo raiz1 = null;

        while (q1.size() > 1) {  
            Nodo x = q1.peek();
            q1.poll();       
            Nodo y = q1.peek();
            q1.poll();           
            Nodo l = new Nodo();
            l.setProb(x.getProb() + y.getProb());
            l.setPrecio(-1);
            l.setLeft(x);
            l.setRight(y);
            raiz1 = l;
            q1.add(l);            
        }
        codificar(raiz1,"",codificacionEnBitsEth,cotizacionesEth,entropiaEth);

        //Generacion de arreglo en bits de los mil valores de cotizacion.
        ArrayList<String> cotizacionEnBitsEth = new ArrayList<>();
        double aux10=0;
        String aux11;
        for(int i=0;i<1000;i++){
            aux10=emisionesEth[i];
            aux11 = codificacionEnBitsEth.get(cotizacionesEth.indexOf(aux10));
            cotizacionEnBitsEth.add(aux11);
        }

        //Generacion de la cabecera para Huffman semi-estatico
        ArrayList<Double> cabeceraEth = new ArrayList<>();
        cabeceraEth.add(1000.0);
        for(int t=0;t<CotizacionesLeidasEth.size();t++){
            cabeceraEth.add(CotizacionesLeidasEth.get(t));
            cabeceraEth.add(cotYprobEth.get(CotizacionesLeidasEth.get(t))*1000);   
        }
        
    System.out.println("");  
    
//2-C)-----
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 2-C");
System.out.println("--------------");  
System.out.println("");    
    //Generacion e impresion de las cotizaciones codificadas con el metodo RLC para BTC
    ArrayList<Double> cotizacionesRLCBtc = new ArrayList<>();
    cotizacionesRLCBtc = probabilidadBtc.getCodRLC();
        System.out.println("COTIZACIONES CON RLC PARA BTC:");
        System.out.println("");
        for (int i=0;i<cotizacionesRLCBtc.size()-1;i=i+2){
            System.out.println("Cotizacion:"+cotizacionesRLCBtc.get(i) + "| repeticion: " + cotizacionesRLCBtc.get(i+1));
        }
        System.out.println("");
    
    //Generacion e impresion de las cotizaciones codificadas con el metodo RLC para ETH
    ArrayList<Double> cotizacionesRLCEth = new ArrayList<>();
    cotizacionesRLCEth = probabilidadEth.getCodRLC();
        System.out.println("COTIZACIONES CON RLC PARA ETH:");
        System.out.println("");
        for (int i=0;i<cotizacionesRLCEth.size()-1;i=i+2){
            System.out.println("Cotizacion:"+cotizacionesRLCEth.get(i) + "| repeticion: " + cotizacionesRLCEth.get(i+1));
        }
    
    
    /*//Generacion e impresion de las cotizaciones codificadas con el metodo RLC mejorado
    ArrayList<Double> aux5 = new ArrayList<>();
    aux5 = probabilidadEth.getCodRLCmejorado(2);
        for (Double aux7 : aux5) {
            System.out.print(aux7 + " ");
        }    
      */ 
    
//2-D)-----
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 2-D");
System.out.println("--------------");  
System.out.println("");  
    //Tasa de compresion de RLC para BTC
    double cantBitsRLCBtc = (cotizacionesRLCBtc.size()*16);
    double tasaCompresionRlcBtc = 16000/cantBitsRLCBtc;
    System.out.println("");
    System.out.println("Tasa de compresion RLC para BTC: "+tasaCompresionRlcBtc);

    
    //Tasa de compresion de RLC para ETH
    
    double cantBitsRLCEth = (cotizacionesRLCEth.size()*16);
    double tasaCompresionRlcEth = 16000/cantBitsRLCEth;
    System.out.println("");
    System.out.println("Tasa de compresion RLC para ETH: "+tasaCompresionRlcEth);
    System.out.println("");


   /* //Tasa de compresion de RLC mejorado. La usamos para analizar los resultados en el ejercicio 2.e
    double cantBitsRlcMejorado = 0;
    int f = 0;
    while(f < aux5.size()){
        if (aux5.get(f)==0.0){
            cantBitsRlcMejorado+= 17;
            f = f + 2;
        }else{
            cantBitsRlcMejorado+= 33;
            f = f + 3;
        }
    }     
            
    double tasaCompresionRlcMejorado = 16000/cantBitsRlcMejorado;    
    System.out.println("");
    System.out.println(cantBitsRlcMejorado);
    System.out.println("Tasa de compresion para RLC mejorado: "+tasaCompresionRlcMejorado);
    System.out.println("");    
        System.out.println("Tamaño de aux5 "+aux5.size());
    
    */

    //Taza de compresion de huffman semi-estatico para BTC
    int cantidadBitsBtc = 0;
    for(int l=0;l<1000;l++){
        cantidadBitsBtc+= cotizacionEnBitsBtc.get(l).length();
    }
    double cantBitsHuffmanBtc = ((cabeceraBtc.size()*16)+cantidadBitsBtc); //Estoy sumando la cantidad de bits que me costo comprimir, mas la cabecera, la cual seria el respectivo arreglo con 2 bytes para cada valor.
    double tasaCompresionBtc = 16000/cantBitsHuffmanBtc;
    System.out.println("Tasa de compresion para Huffman semi-estatico para BTC: "+tasaCompresionBtc);
    System.out.println("");
    
    //Taza de compresion de huffman semi-estatico para ETH
    int cantidadBitsEth = 0;
    for(int l=0;l<1000;l++){
        cantidadBitsEth+= cotizacionEnBitsEth.get(l).length();
    }
    double cantBitsHuffmanEth = ((cabeceraEth.size()*16)+cantidadBitsEth); //Estoy sumando la cantidad de bits que me costo comprimir, mas la cabecera, la cual seria el respectivo arreglo con 2 bytes para cada valor.
    double tasaCompresionEth = 16000/cantBitsHuffmanEth;
    System.out.println("Tasa de compresion para Huffman semi-estatico para ETH: "+tasaCompresionEth);
    System.out.println("");
    
     
//2-F)--------
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 2-F");
System.out.println("--------------");  
System.out.println("");
   
    //Entropia para BTC:
    double denominador = Math.log10(2);   
    double numeradorBtc = 0;
    double resultBtc = 0;
    double entropiaHuffmanBtc=0;
    for(int i = 0;i<CotizacionesLeidasBtc.size();i++){
            numeradorBtc = Math.log10(cotYprobBtc.get(CotizacionesLeidasBtc.get(i))); 
            resultBtc += cotYprobBtc.get(CotizacionesLeidasBtc.get(i))*(numeradorBtc/denominador)*(-1);
        }
        System.out.println("Entropia para BTC: "+resultBtc);    
    
        for(int i=0;i<entropiaBtc.size()-1;i=i+2)
            entropiaHuffmanBtc+=(entropiaBtc.get(i)*entropiaBtc.get(i+1));
        System.out.println("");
        System.out.println("Entropia Huffman para BTC: "+entropiaHuffmanBtc);
        System.out.println("");

    //Entropia para ETH:
    double resultEth = 0;
    double numeradorEth=0;
    double entropiaHuffmanEth=0;
    for(int i = 0;i<CotizacionesLeidasEth.size();i++){
            numeradorEth = Math.log10(cotYprobEth.get(CotizacionesLeidasEth.get(i))); 
            resultEth += cotYprobEth.get(CotizacionesLeidasEth.get(i))*(numeradorEth/denominador)*(-1);
        }
        System.out.println("Entropia para ETH: "+resultEth);    
        System.out.println("");
        for(int i=0;i<entropiaEth.size()-1;i=i+2)
            entropiaHuffmanEth+=(entropiaEth.get(i)*entropiaEth.get(i+1));
        System.out.println("Entropia Huffman para ETH: "+entropiaHuffmanEth);
        System.out.println("");

    
    
    
//Generacion de archivos:

            int contador = 0;
            File folder = new File("Archivos Comprimidos");
            folder.mkdir();
            //Archivo BTC para huffman
            File fileBtc = new File(folder, "Archivo btcHuffman.txt");
            // Si el archivo no existe es creado
            if (!fileBtc.exists()) {
                fileBtc.createNewFile();
            }
            FileWriter fwBtc = new FileWriter(fileBtc);
            BufferedWriter bwBtc = new BufferedWriter(fwBtc);
            bwBtc.write("[" + cabeceraBtc.get(0));
            for(int h=1;h<cabeceraBtc.size();h++)
                if(contador==0){
                    bwBtc.write("("+cabeceraBtc.get(h) + ",");
                    contador++;
                }else{
                    bwBtc.write(cabeceraBtc.get(h)+")");
                    contador=0;
                }
            bwBtc.write("]");
            bwBtc.write("[");
            for (int r=0;r<cotizacionEnBitsBtc.size();r++)
                bwBtc.write(cotizacionEnBitsBtc.get(r));
            bwBtc.write("]");
            bwBtc.close();           
            
            //Archivo ETH para huffman
            contador=0;
            File fileEth = new File(folder, "Archivo ethHuffman.txt");
            // Si el archivo no existe es creado
            if (!fileEth.exists()) {
                fileEth.createNewFile();
            }
            FileWriter fwEth = new FileWriter(fileEth);
            BufferedWriter bwEth = new BufferedWriter(fwEth);
            bwEth.write("[" + cabeceraEth.get(0));
            for(int h=1;h<cabeceraEth.size();h++)
                if(contador==0){
                    bwEth.write("("+cabeceraEth.get(h) + ",");
                    contador++;
                }else{
                    bwEth.write(cabeceraEth.get(h)+")");
                    contador=0;
                }
            bwEth.write("]");
            bwEth.write("[");
            for (int r=0;r<cotizacionEnBitsEth.size();r++)
                bwEth.write(cotizacionEnBitsEth.get(r));
            bwEth.write("]");
            bwEth.close();
               
            //Archivo BTC para RLC
            File fileRLCBtc = new File(folder, "Archivo btcRLC.txt");
            // Si el archivo no existe es creado
            if (!fileRLCBtc.exists()) {
                fileRLCBtc.createNewFile();
            }
            FileWriter fwRLCBtc = new FileWriter(fileRLCBtc);
            BufferedWriter bwRLCBtc = new BufferedWriter(fwRLCBtc);
            bwRLCBtc.write("[");
            for (int r=0;r<cotizacionesRLCBtc.size();r++)
                bwRLCBtc.write(String.valueOf(cotizacionesRLCBtc.get(r)+"-"));
            
            bwRLCBtc.write("]");
            bwRLCBtc.close();          
    
            //Archivo ETH para RLC
            File fileRLCEth = new File(folder, "Archivo ethRLC.txt");
            // Si el archivo no existe es creado
            if (!fileRLCEth.exists()) {
                fileRLCEth.createNewFile();
            }
            FileWriter fwRLCEth = new FileWriter(fileRLCEth);
            BufferedWriter bwRLCEth = new BufferedWriter(fwRLCEth);
            bwRLCEth.write("[");
            for (int r=0;r<cotizacionesRLCEth.size();r++)
                bwRLCEth.write(String.valueOf(cotizacionesRLCEth.get(r)+"-"));
            
            bwRLCEth.write("]");
            bwRLCEth.close();          
    

    //------------------------- EJERCICIO 3 -------------------------
   //3-a)
System.out.println("");
System.out.println("--------------");
System.out.println("EJERCICIO 3-A");
System.out.println("--------------");  
System.out.println("");
           
        matrizConjunta m1 = new matrizConjunta();
        double [][] matConjunta = new double[3][3];
        matConjunta = m1.llenarMatConjunta(cambiosBtc, cambiosEth);
        System.out.println("Matriz Conjunta BTC,ETH");
        System.out.println(" baja  mant  sube");
        for(int i=0;i<3;i++){
            System.out.print("|");
            for(int s=0;s<3;s++){
                System.out.print(matConjunta[i][s]+"|");
            }
            System.out.println("");
        }
    
        System.out.println("");
        
    }
}