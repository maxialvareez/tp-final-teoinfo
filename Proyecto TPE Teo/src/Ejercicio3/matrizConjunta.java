
package Ejercicio3;


public class matrizConjunta {
    
       
    public double [][] llenarMatConjunta(int[]cambiosBTC,int[] cambiosETH){
        double[][]mConjunta = new double[3][3];
        for(int i=0;i<cambiosBTC.length;i++){
            mConjunta[cambiosETH[i]][cambiosBTC[i]]+=1;
        }
        
        for(int t=0;t<3;t++){
            for(int j=0;j<3;j++){
                mConjunta[t][j] = mConjunta[t][j]/1000; 
            }
        }
    return mConjunta;
    }
    
    
    
}
