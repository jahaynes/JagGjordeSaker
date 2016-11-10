package se.mirado.jgs.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.monitoring.Metric;

import java.util.function.Function;

import javaslang.control.Try;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Query<T> implements Function<AppState, Try<T>>, HasMetric {

    @Getter
    private final Metric metric;
    private final Function<AppState, Try<T>> func;

    @Override
    public Try<T> apply(AppState a) {
        return func.apply(a);
    }

    public static <T> Query<T> named(String message, Function<AppState, Try<T>> func) {
        return new Query<>(Metric.named(message), func);
    }

    public static <T> Query<T> anonymous(Function<AppState, Try<T>> func) {
        return new Query<>(Metric.anonymous(), func);
    }

}