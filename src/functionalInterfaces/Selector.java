/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionalInterfaces;

/**
 *
 * @author Ajikozau
 */
@FunctionalInterface
public interface Selector<T> {
    public void action(T o);   
}
