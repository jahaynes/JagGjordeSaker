package se.mirado.jgs.monitoring;

import lombok.Data;

@Data
public class Metric {

    private final String message;
    private final long timestamp;

    //TODO - consider escaping.
    public static Metric named(String message) {
        return new Metric(message, System.currentTimeMillis());
    }

    public static Metric anonymous() {
        return new Metric("<no message logged>", System.currentTimeMillis());
    }

}
