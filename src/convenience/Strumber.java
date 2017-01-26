package convenience;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ajikozau
 */
public class Strumber {
    
    private String s;
    public String getS(){ return s; }
    
    private int i;
    public int getI(){ return i; }
    
    private double d; 
    public double getD(){ return d; }
    
    public Strumber(String s, int i){
        this.s = s;
        this.i = i;
    }
    
    public Strumber(String s, double d){
        this.s = s;
        this.d = d;
    }
}
