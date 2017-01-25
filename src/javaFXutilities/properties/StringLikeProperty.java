/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFXutilities.properties;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

/**
 *
 * @author Ajikozau
 */
public class StringLikeProperty extends MathIntegerProperty {
    private final String[] sentences;
    
    public StringLikeProperty(int i, String[] sentences){
        super(i);
        this.sentences = sentences;
    }    
    
    @Override
    public StringBinding asString() {
        return (StringBinding) Bindings.format(sentences[intValue()], this);
    }
}
