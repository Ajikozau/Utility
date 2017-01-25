/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convenience;

/**
 *
 * @author Ajikozau
 */
public class Trio<A, B, C> {
    
    public Trio(A a, B b, C c){
        this.a = a; 
        this.b = b;
        this.c = c;
    }
    
    private A a;
    private B b;
    private C c;
    
    public A getA(){
        return a;
    }
    public B getB(){
        return b;       
    }
    public C getC(){
        return c;
    }
}
