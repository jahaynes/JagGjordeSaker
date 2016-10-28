package se.mirado.jgs.common;

import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.monitoring.Metric;

@AllArgsConstructor
public class MeasuredFunction<A,B> implements Function<A, B> {

    @Getter
    private final Metric metric;    
    private final Function<A, B> func;

    @Override
    public B apply(A a) {
        return func.apply(a);
    }

}
