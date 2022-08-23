
package Ejercicio1;

import static java.lang.Math.sqrt;


public class CorrelacionCruzada {
    
    private double [] array_1;
    private double [] array_2;
    private int [] tiempo;
    private double [] correlacion;
    private double [] media1;
    private double [] media2;
    private double [] coefcorrelacionCruzada;
    private double [] varianza1;
    private double [] varianza2;
    private double [] desvioEstandar1;
    private double [] desvioEstandar2;
    public CorrelacionCruzada(int [] tiempo, double [] array_1, double [] array_2){
        this.array_1 = array_1;
        this.array_2=array_2;
        this.tiempo=tiempo;
        this.correlacion = new double[tiempo.length];
        this.media1 = new double[tiempo.length];
        this.media2 = new double[tiempo.length];
        this.varianza1 = new double[tiempo.length];
        this.varianza2 = new double[tiempo.length];
        this.desvioEstandar1 = new double[tiempo.length];
        this.desvioEstandar2 = new double[tiempo.length];
        this.coefcorrelacionCruzada = new double[tiempo.length];
    }
    
    public void calcularCorrelacionCruzada(){


        int cantEmisiones = array_1.length;      

        for(int i=1;i<this.correlacion.length;i++){
            this.correlacion[i]=0;
            this.varianza1[i]=0;
            this.varianza2[i]=0;
            this.desvioEstandar1[i]=0;
            this.desvioEstandar2[i]=0;
            this.media1[i]=0;
            this.media2[i]=0;
            this.coefcorrelacionCruzada[i]=0;}
        
        for(int i=0;i<this.tiempo.length;i++){
        for(int j=0;j<cantEmisiones-this.tiempo[i];j++){
            this.correlacion[i]+= (array_1[j]*array_2[j+this.tiempo[i]])/(cantEmisiones - this.tiempo[i]);
            this.media1[i]+=(array_1[j])/(cantEmisiones - this.tiempo[i]);
            this.media2[i]+=(array_2[j+this.tiempo[i]])/(cantEmisiones - this.tiempo[i]);
        }
            
        }
   //Calculos para el coeficiente de correlacion cruzada:
   for(int j=0;j<this.tiempo.length;j++){
        for(int i=0;i<cantEmisiones-this.tiempo[j];i++){      
   this.varianza1[j]+=Math.pow(array_1[i]-this.media1[j],2f);
   this.varianza2[j]+=Math.pow(array_2[i]-this.media2[j],2f);
   }}
   
   for(int j=0;j<this.tiempo.length;j++){
          this.varianza1[j]=this.varianza1[j]/(cantEmisiones-this.tiempo[j]);
          this.varianza2[j]=this.varianza2[j]/(cantEmisiones-this.tiempo[j]);
                  }
   for(int j=0;j<this.tiempo.length;j++){
       this.desvioEstandar1[j]=sqrt(this.varianza1[j]);
       this.desvioEstandar2[j]=sqrt(this.varianza2[j]);

   }
      for(int i=0;i<this.coefcorrelacionCruzada.length;i++)
       this.coefcorrelacionCruzada[i]=((this.correlacion[i]-(this.media1[i]*this.media2[i]))/(this.desvioEstandar1[i]*this.desvioEstandar2[i]));
    } 

public double [] get_correlacion_cruzada(){
        return this.correlacion;
    }
    public double [] get_media(){
        return this.media1;
    }

    public double [] get_coef_correlacion_cruzada(){
        return this.coefcorrelacionCruzada;
    }

}