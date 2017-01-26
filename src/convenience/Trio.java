/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convenience;

/**
 *
 * @author Ajikozau
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class Trio<A, B, C> {
    
    private A a;
    public A getA(){ return a; }
    
    private B b;
    public B getB(){ return b; }
    
    private C c;
    public C getC(){ return c; }
    
    public Trio(A a, B b, C c){
        this.a = a; 
        this.b = b;
        this.c = c;
    }
    
    
    
 
    
    
}
