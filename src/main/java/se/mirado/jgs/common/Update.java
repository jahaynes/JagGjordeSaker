package se.mirado.jgs.common;

import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.monitoring.Metric;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Update implements Function<AppState, AppState>, HasMetric {

    @Getter
    private final Metric metric;
    private final Function<AppState, AppState> func;

    @Override
    public AppState apply(AppState a) {
        return func.apply(a);
    }

    public static Update named(String message, Function<AppState, AppState> func) {
        return new Update(Metric.named(message), func);
    }

    public static Update anonymous(Function<AppState, AppState> func) {
        return new Update(Metric.anonymous(), func);
    }

}