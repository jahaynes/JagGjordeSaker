package se.mirado.jgs.common;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.monitoring.Metric;

import java.util.function.Function;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Update implements Function<AppState, Try<AppState>>, HasMetric {

    @Getter
    private final Metric metric;
    private final Function<AppState, Try<AppState>> func;

    @Override
    public Try<AppState> apply(AppState a) {
        return func.apply(a);
    }

    public static Update create(Metric metric, Function<AppState, Try<AppState>> func) {
        return new Update(metric, func);
    }

    public static Update named(String message, Function<AppState, Try<AppState>> func) {
        return new Update(Metric.named(message), func);
    }

    public static Update anonymous(Function<AppState, Try<AppState>> func) {
        return new Update(Metric.anonymous(), func);
    }

}