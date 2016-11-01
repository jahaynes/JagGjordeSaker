package se.mirado.jgs.common;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.monitoring.Metric;

@AllArgsConstructor
public class Query<T> implements Function<AppState, T>, HasMetric {

    @Getter
    private final Metric metric;
    private final Function<AppState, T> func;

    @Override
    public T apply(AppState a) {
        return func.apply(a);
    }

}