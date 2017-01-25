/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  javafx.beans.binding.Bindings
 *  javafx.beans.binding.StringBinding
 *  javafx.beans.property.SimpleBooleanProperty
 */
package javaFXutilities.properties;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;

public class YesNoProperty
extends SimpleBooleanProperty {
    public YesNoProperty(boolean initialValue) {
        super(initialValue);
    }

    public YesNoProperty(boolean initialValue, String name) {
        super((Object)initialValue, name);
    }

    public StringBinding asString() {
        if (this.getValue().booleanValue()) {
            return (StringBinding)Bindings.format((String)"Yes", (Object[])new Object[]{this});
        }
        return (StringBinding)Bindings.format((String)"No", (Object[])new Object[]{this});
    }

    public StringBinding nameSelf() {
        if (this.getValue().booleanValue()) {
            return (StringBinding)Bindings.format((String)this.getName(), (Object[])new Object[]{this});
        }
        return (StringBinding)Bindings.format((String)"", (Object[])new Object[]{this});
    }
}
