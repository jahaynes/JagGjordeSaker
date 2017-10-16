package se.mirado.jgs.data.time;

import java.time.LocalDate;
import java.util.Random;

import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;

public class ArbitraryLocalDate implements Arbitrary<LocalDate> {

    @Override
    public Gen<LocalDate> apply(int seed) {
        return new Gen<LocalDate>() {
            @Override
            public LocalDate apply(Random rand) {
                return LocalDate.ofEpochDay(rand.nextInt(10000));
            }
        };
    }

}
