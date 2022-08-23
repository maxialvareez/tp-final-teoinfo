
package Ejercicio1;


import java.util.Scanner;

public class Canal {

    
    
    public int[] obtenerCambios(Scanner input){
        int [] cambios = new int[1000];
        int s = 0;
        int i=0;
        cambios[i]=1; //La primera cotizacion mantiene en relacion a la anterior desconocida. 
        i++;
        String primerSimbolo = input.nextLine(); //Tomo la primera cotizacion
        while(input.hasNextLine()) {
            String segundoSimbolo = input.nextLine();
            if(primerSimbolo.compareTo(segundoSimbolo) == 0)
                s=1; //Si da 1 es pq el precio se mantuvo
            else
                if(primerSimbolo.compareTo(segundoSimbolo) < 0)
                    s=2; //Si da 2 es pq el precio subiÃ³
                else
                    if(primerSimbolo.compareTo(segundoSimbolo) > 0)
                        s=0; //Si da 0 es pq el precio disminuye 
            cambios[i]=s;
            i++;
            primerSimbolo = segundoSimbolo;
        }
    return cambios;
    }

    
}
