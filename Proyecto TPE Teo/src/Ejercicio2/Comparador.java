
package Ejercicio2;

import java.util.Comparator;


public class Comparador implements Comparator<Nodo> {
    @Override
    public int compare(Nodo x, Nodo y){
        if (x.getProb() < y.getProb()) return -1;
        if (x.getProb() > y.getProb()) return 1;
        return 0;
    }
}