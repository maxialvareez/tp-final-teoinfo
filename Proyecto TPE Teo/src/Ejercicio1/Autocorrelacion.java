package Ejercicio1;

import static java.lang.Math.sqrt;


public class Autocorrelacion {

    private double [] autoCorrelacion;
    private double [] media1;
    private double [] media2;
    private double [] coefAutocorrelacion;
    private int t1;
    private int t2;
    private double [] varianza1;
    private double [] varianza2;
    private double [] desvioEstandar1;
    private double [] desvioEstandar2;
    
    public Autocorrelacion(int t1, int t2){
        this.autoCorrelacion = new double [t2+1];
        this.media1 = new double [t2+1];
        this.media2 = new double [t2+1];
        this.coefAutocorrelacion = new double [t2+1];
        this.t1 = t1;
        this.t2 = t2;
        this.varianza1 = new double[t2+1];
        this.varianza2 = new double[t2+1];
        this.desvioEstandar1 = new double[t2+1];
        this.desvioEstandar2 = new double[t2+1];
    }
    
    public void calcularAutoCorrelacion(double arreglo[]){
        
        for(int i=1;i<this.autoCorrelacion.length;i++){
            this.autoCorrelacion[i]=0;
            this.media1[i]=0;
            this.media2[i]=0;
            this.coefAutocorrelacion[i]=0;
            this.varianza1[i]=0;
            this.varianza2[i]=0;
            this.desvioEstandar1[i]=0;
            this.desvioEstandar2[i]=0;
        }
        int emisiones = arreglo.length;
        for(int i=this.t1; i<=this.t2;i++){
            for(int j=0;j<emisiones-i;j++){
                this.autoCorrelacion[i]+= (arreglo[j] * arreglo[j+i])/(emisiones-i);
                this.media1[i]+=(arreglo[j])/(emisiones-i);
                this.media2[i]+=(arreglo[j+i])/(emisiones-i);
                        }
        }
     
//Calculos para el coeficiente de autocorrelacion:
    for(int j=this.t1;j<=this.t2;j++){
        for(int i=0;i<emisiones-j;i++){      
            this.varianza1[j]+=Math.pow(arreglo[i]-this.media1[j],2f);
            this.varianza2[j]+=Math.pow(arreglo[i]-this.media2[j],2f);
   }}
   
   for(int j=this.t1;j<=this.t2;j++){
          this.varianza1[j]=this.varianza1[j]/(emisiones-j);
          this.varianza2[j]=this.varianza2[j]/(emisiones-j);
                  }
   for(int j=this.t1;j<=this.t2;j++){
       this.desvioEstandar1[j]=sqrt(this.varianza1[j]);
       this.desvioEstandar2[j]=sqrt(this.varianza2[j]);
   }
    for(int i=1;i<this.coefAutocorrelacion.length;i++)
       this.coefAutocorrelacion[i]=((this.autoCorrelacion[i]-(this.media1[i]*this.media2[i]))/(this.desvioEstandar1[i]*this.desvioEstandar2[i]));
   
    
    }

    public double [] get_Autocorrelacion(){
        return this.autoCorrelacion;
    }

    public double [] get_Media(){
        return this.media1;
    }
    public double [] get_Coef_Autocorrelacion(){
        return this.coefAutocorrelacion;
    }
}