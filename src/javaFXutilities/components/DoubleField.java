/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  javafx.beans.property.StringProperty
 *  javafx.beans.value.ChangeListener
 *  javafx.beans.value.ObservableValue
 *  javafx.scene.control.TextField
 */
package javaFXutilities.components;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DoubleField
extends TextField {
    private double minValue = 0.0;

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    private void initialize() {
        this.setText(String.format("%f", this.minValue));
    }

    public DoubleField() {
        this.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.equals("")) {
                this.setText(String.format("%f", this.minValue));
            } else {
                this.setText(newVal.replaceAll("[^\\d.]", ""));
                if (Double.parseDouble(this.getText()) < this.minValue) {
                    this.setText(String.format("%f", this.minValue));
                }
            }
        }
        );
    }

    public DoubleField(String text) {
        super(text);
        this.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.equals("")) {
                this.setText(String.format("%f", this.minValue));
            } else {
                this.setText(newVal.replaceAll("[^\\d.]", ""));
                if (Double.parseDouble(this.getText()) < this.minValue) {
                    this.setText(String.format("%f", this.minValue));
                }
            }
        }
        );
    }

    public double getValue() {
        return Double.parseDouble(this.getText());
    }
}
