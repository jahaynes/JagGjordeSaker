package se.mirado.jgs.common;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.monitoring.Metric;

@AllArgsConstructor
public class Update implements Function<AppState, AppState>, HasMetric {

    @Getter
    private final Metric metric;
    private final Function<AppState, AppState> func;

    @Override
    public AppState apply(AppState a) {
        return func.apply(a);
    }

}