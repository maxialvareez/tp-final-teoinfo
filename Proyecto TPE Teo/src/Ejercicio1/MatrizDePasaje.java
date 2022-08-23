
package Ejercicio1;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MatrizDePasaje {
    
private double[][] transiciones;
private Scanner input;

public MatrizDePasaje(Scanner i){
    this.transiciones = new double [3][3];
    this.input = i; 
    }
    
    public double[][] llenarMatriz() throws FileNotFoundException{
        int[] cantIteraciones = {0,0,0};
        int tipoTransActual = 1; //Es 1 ya que la primera cotización mantiene el precio.
        int s = 0,i=0;
        if(this.input.hasNext()){
        String primerSimbolo = this.input.nextLine(); //Tomo la primera cotizacion     
        while(this.input.hasNextLine()) {
            String segundoSimbolo = this.input.nextLine();
            if(primerSimbolo.compareTo(segundoSimbolo) == 0)
                s=1; //Si da 1 es pq el precio se mantuvo
            else
                if(primerSimbolo.compareTo(segundoSimbolo) < 0)
                    s=2; //Si da 2 es pq el precio subió
                else
                    if(primerSimbolo.compareTo(segundoSimbolo) > 0)
                        s=0; //Si da 0 es pq el precio disminuye 
            
            cantIteraciones[tipoTransActual]+= 1;
            primerSimbolo = segundoSimbolo;
            transiciones[s][tipoTransActual] += 1;
            tipoTransActual = s;
        }}
        for(int t=0;t<3;t++){
            for(int j=0;j<3;j++)
                transiciones[t][j] = (transiciones[t][j]/cantIteraciones[j]);
        }
    return transiciones;
    }
    
    public double [][] getMatrizAcumulada(){
        double[][] aux = new double[3][3];       
        double [][]transicionesAcum = new double[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==0)
                    transicionesAcum[i][j] = transiciones[i][j];
                else
                    transicionesAcum[i][j] = transiciones[i][j] + transicionesAcum[i-1][j];
            }
        }
    return transicionesAcum;
    
    }
}
    
    
    
