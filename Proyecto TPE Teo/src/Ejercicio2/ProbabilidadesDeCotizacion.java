
package Ejercicio2;

import java.util.ArrayList;
import java.util.HashMap;

//Clase para obtener las probabilidades de emision de cada cotizacion, como su codigo en RLC y RLC mejorado.
public class ProbabilidadesDeCotizacion {
    
    
    private double [] cotizaciones;

    public ProbabilidadesDeCotizacion(double arr[]){
        this.cotizaciones = arr;
    }

    public HashMap<Double,Double> getProbabilidadYcotizacion(){
    HashMap<Double,Double> cotYprob = new HashMap<Double,Double>();
        double probabilidad = 0;
        for(int i=0;i<this.cotizaciones.length;i++){
            int contador = 0;
            if(!cotYprob.containsKey(this.cotizaciones[i])){
               for(int j=0;j<this.cotizaciones.length;j++){
                   if (this.cotizaciones[j] == this.cotizaciones[i])
                   contador++;
               }
               probabilidad = (double)(contador)/this.cotizaciones.length;
               cotYprob.put(this.cotizaciones[i], probabilidad);
            }
                
            
        }
        
    return cotYprob;   
    }
    
    public ArrayList<Double> getCodRLC(){
        ArrayList<Double> datosRLC = new ArrayList<>();
        int j = 0;
        double contador = 1;
        for(int i=1;i<=999;i++){
            if(this.cotizaciones[j] == this.cotizaciones[i]){
                contador++;
            }
            else{
                datosRLC.add(this.cotizaciones[j]);
                datosRLC.add(contador);
                contador = 1;
                j = i;
            }
        }
    return datosRLC;
    }
        
    public ArrayList<Double> getCodRLCmejorado(int repeticiones){
        ArrayList<Double> datosRLCmejorado = new ArrayList<>();
        int j = 0;
        double contador = 1;
        for(int i=1;i<=999;i++){
            if(this.cotizaciones[j] == this.cotizaciones[i]){
                contador++;
            }
            else{
                if(contador>=repeticiones){
                datosRLCmejorado.add(1.0);
                datosRLCmejorado.add(this.cotizaciones[j]);
                datosRLCmejorado.add(contador);
                contador = 1;
                j = i;
                }
                else{
                    for(int k=0;k<contador;k++){
                        datosRLCmejorado.add(0.0);
                        datosRLCmejorado.add(this.cotizaciones[j]);
                    }
                j = i;
                contador = 1;
                }
                    
            }
        }
    return datosRLCmejorado;
    }
    
    
    
    
}
