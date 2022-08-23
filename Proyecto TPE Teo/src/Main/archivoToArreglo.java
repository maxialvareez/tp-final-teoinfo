
package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class archivoToArreglo {
private double[] array_a;
private double[] array_b;

public archivoToArreglo(){
    this.array_a = new double[1000];
    this.array_b = new double[1000];
}

public double [] obtenerEmisiones(String moneda) throws FileNotFoundException{
        int i =0;
        Scanner input1 = new Scanner(new File("./ArchivosCatedra/BTC.txt")); 
        Scanner input2 = new Scanner(new File("./ArchivosCatedra/ETH.txt")); 
        while(input1.hasNextLine()){
            double simbBTC = input1.nextDouble(); //Tomo la cotizacion
            double simbETH = input2.nextDouble(); //Tomo la cotizacion
            this.array_a[i]=simbBTC;
            this.array_b[i]=simbETH;
            i++;
        }
    if ("btc".equals(moneda))
        return array_a;
    else
        return array_b;
    }
}