package se.mirado.jgs.monitoring;

import lombok.Data;

@Data
public class Metric {

    private final long timeStamp;
    private final String message;

}
