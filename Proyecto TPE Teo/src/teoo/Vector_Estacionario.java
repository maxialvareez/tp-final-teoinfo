
package teoo;

import static java.lang.Math.abs;


public class Vector_Estacionario {
    
    private double [][] Matriz;
    private double epsilon;
    public Vector_Estacionario(double [][] m,double epsilon){
        this.Matriz = m;
        this.epsilon = epsilon;
    }
    
    public double [] calcular_vector_estacionario(){
        int S_MIN = 100000;
        double [] emisiones = new double [3];
        double [] ve = new double [3];
        double [] ve_ant = new double [3];
        double cant_simb = 0;
        
        //Inicializacion
        for(int i=0; i<3; i++){
            ve[i] = 0;
            ve_ant[i]=-1;
            emisiones[i]=0;
        }
        
        int s=1;
        
        while (!converge(ve,ve_ant)|| (cant_simb < S_MIN)) {
            cant_simb++;
            s= sig_dado_ant(s);
            emisiones[s]++;
            ve_ant=ve;
            for (int j=0;j<3;j++){
               ve[j] = (emisiones[j]/cant_simb); 
            }
    
        }
        
    return ve;
    }
    

    public boolean converge(double [] ve, double [] ve_ant){
        for(int i=0; i<3; i++){
            if(abs(ve[i]-ve_ant[i])> this.epsilon)
                return false;
        }
        return true;
    }
    
    public int sig_dado_ant(int simb_ant){
        double r = Math.random();
        for(int i=0; i<3; i++){
            if(r<(this.Matriz[i][simb_ant]))
                return i;
        }
    return 0;
    }
}

