/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFXutilities.properties;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Ajikozau
 */
public class MathIntegerProperty extends SimpleIntegerProperty {
    public void plus(int i){
        setValue(intValue()+i);
    }    
    
    public void minus(int i){
        setValue(intValue()-i);
    }

    public MathIntegerProperty(int initialValue) {
        super(initialValue);
    }
    
}
