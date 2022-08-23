
package Ejercicio2;


public class Nodo {
    private double precio;
    private double prob;
    private Nodo left = null;
    private Nodo right = null;
    
    
    public Nodo(int precio,double prob){
        this.precio = precio;
        this.prob = prob;
    }

    public Nodo() {
    }
    
    public Nodo getLeft(){
        return left;
    }
    
    public void setLeft(Nodo left){
        this.left = left;
    }
    
    public Nodo getRight(){
        return right;
    }
    
    public void setRight(Nodo right){
        this.right = right;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public double getProb(){
        return prob;
    }
    
    public void setProb(double prob){
        this.prob = prob;
    }
    
    public boolean esHoja(){
        return this.getLeft() == null && this.getRight() == null;
    }
}