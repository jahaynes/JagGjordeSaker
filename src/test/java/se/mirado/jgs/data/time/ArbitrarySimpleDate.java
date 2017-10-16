package se.mirado.jgs.data.time;

import java.time.LocalDate;
import java.util.Random;

import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;

public class ArbitrarySimpleDate implements Arbitrary<SimpleDate> {

    Gen<LocalDate> localDates =
            new ArbitraryLocalDate().apply(new Random().nextInt());

    @Override
    public Gen<SimpleDate> apply(int seed) {
        return new Gen<SimpleDate>() {
            @Override
            public SimpleDate apply(Random rand) {
                return SimpleDate.fromDate(localDates.apply(rand));
            }
        };
    }

}
