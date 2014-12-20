/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex03;

import java.util.Objects;
import java.util.function.Supplier;
import javafx.beans.property.Property;

/**
 *
 * @author yoshiki
 */
public class LazyProperty<T> {

    private T value;
    private Property<T> property;
    private final Supplier<Property<T>> supplier;

    /**
     * Constructs a LazyProperty with a default value
     *
     * @param supplier a property creator
     * @param defaultValue a default value for this property
     * @throws NullPointerException if either supplier or defaultValue is null
     */
    public LazyProperty(Supplier<Property<T>> supplier, T defaultValue) {
        Objects.requireNonNull(supplier, "supplier is null");
        Objects.requireNonNull(defaultValue, "defaultValue is null");

        this.supplier = supplier;
    }

    public final void setValue(T value) {
        if (property != null) {
            property.setValue(value);
        } else {
            this.value = value;
        }
    }

    public final T getValue() {
        return property != null ? property.getValue() : value;
    }

    public final Property<T> property() {

        if (property != null) {
            return property;
        }

        property = supplier.get();
        property.setValue(value);
        value = null;
        return property;
    }
}
