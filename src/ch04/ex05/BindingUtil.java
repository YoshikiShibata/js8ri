/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex05;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

/**
 * Write methods
 *
 * <pre>
 * public static <T, R> ObservableValue<R> observe(
 * Function<T, R> f, ObservableValue<T> t)
 * </pre>
 *
 * <pre>
 * public static <T, U, R> ObservableValue<R> observe(
 * BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u)
 * </pre>
 *
 * that return observable values whose getValue method returns the value of the
 * lambda expression, and whose invalidation and change listeners are fired when
 * any of the inputs become invalid or change. For example,
 *
 * <pre>
 * larger.disableProperty().bind(observe(
 * t -> t >= 100, gauge.widthProperty()));
 * </pre>
 *
 * このメソッドは、ObservableValue を返し、そのObservableValue のgetValue
 * メソッドは、ラムダ式の値を返します。そして、そのObservableValue の InvalidationListener
 * とChangeListener が、入力のどれかが無効あるいは変 更になったときに呼び出されるようにしなさい。
 *
 * see
 * http://blog.netopyr.com/2012/02/08/when-to-use-a-changelistener-or-an-invalidationlistener/
 */
public class BindingUtil {

    /**
     * Constructs an ObservableValue which observes other ObservableValue,
     * and applies a Function to get a value.
     *
     * @param <T> the type of other ObservableValue
     * @param <R> the type of this ObservableValue after applying the Function
     * f.
     * @param f function to convert from T to R
     * @param value the other ObservableValue
     * @return a new ObservableValue
     * @throws NullPointerException if either f or value is null
     */
    public static <T, R> ObservableValue<R> observe(
            Function<T, R> f, ObservableValue<T> value) {
        Objects.requireNonNull(f, "f is null");
        Objects.requireNonNull(value, "value is null");

        return new SingleObservableValue<>(f, value);
    }

    /**
     * Constructs an ObservableValue which observes other two ObservableValues,
     * and applies a BiFunction to get a value.
     * 
     * @param <T> the type of the first ObservableValue.
     * @param <U> the type of the second ObservableValue.
     * @param <R> the type of this ObservableValue after applying the BiFunction.
     * @param f BiFunction to get a value
     * @param value1 the first ObservableValue.
     * @param value2 the second ObservableValue
     * @return a new ObservableValue
     * @throws NullPointerException if either f, value1, or value2 is null.
     */
    public static <T, U, R> ObservableValue<R> observe(
            BiFunction<T, U, R> f, ObservableValue<T> value1,
            ObservableValue<U> value2) {
        Objects.requireNonNull(f, "f is null");
        Objects.requireNonNull(value1, "value1 is null");
        Objects.requireNonNull(value2, "value1 is null");
        
        return new DoubleObservableValues<>(f, value1, value2);

    }

    public static class SingleObservableValue<T, R> extends ObservableValueBase<R> {

        private final Function<T, R> f;
        private final ObservableValue<T> value;

        SingleObservableValue(Function<T, R> f, ObservableValue<T> value) {
            this.f = f;
            this.value = value;
            value.addListener(observable -> fireValueChangedEvent());
            value.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
        }

        @Override
        public R getValue() {
            return f.apply(value.getValue());
        }
    }

    public static class DoubleObservableValues<T, U, R> extends ObservableValueBase<R> {

        private final BiFunction<T, U, R> f;
        private final ObservableValue<T> value1;
        private final ObservableValue<U> value2;

        DoubleObservableValues(BiFunction<T, U, R> f,
                ObservableValue<T> value1, ObservableValue<U> value2) {
            this.f = f;
            this.value1 = value1;
            this.value2 = value2;
            value1.addListener(observable -> fireValueChangedEvent());
            value1.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
            value2.addListener(observable -> fireValueChangedEvent());
            value2.addListener((observable, oldValue, newValue) -> fireValueChangedEvent());
        }

        @Override
        public R getValue() {
            return f.apply(value1.getValue(), value2.getValue());
        }
    }

}
