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

public class IntegerField
extends TextField {
    private int minValue = 0;

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    private void initialize() {
        this.setText(String.format("%d", this.minValue));
    }

    public IntegerField() {
        this.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.equals("")) {
                this.setText(String.format("%d", this.minValue));
            } else {
                this.setText(newVal.replaceAll("[^\\d]", ""));
                if (Integer.parseInt(this.getText()) < this.minValue) {
                    this.setText(String.format("%d", this.minValue));
                }
            }
        }
        );
    }

    public IntegerField(String text) {
        super(text);
        this.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.equals("")) {
                this.setText(String.format("%d", this.minValue));
            } else {
                this.setText(newVal.replaceAll("[^\\d]", ""));
                if (Integer.parseInt(this.getText()) < this.minValue) {
                    this.setText(String.format("%d", this.minValue));
                }
            }
        }
        );
    }

    public int getValue() {
        return Integer.parseInt(this.getText());
    }
}
