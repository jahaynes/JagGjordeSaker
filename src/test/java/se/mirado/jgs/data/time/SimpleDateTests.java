package se.mirado.jgs.data.time;

import org.junit.Test;

import javaslang.test.Arbitrary;
import javaslang.test.CheckResult;
import javaslang.test.Property;

public class SimpleDateTests {

    private final Arbitrary<SimpleDate> simpleDates = new ArbitrarySimpleDate();

    @Test
    public void testToAndfromString() {

        CheckResult result = Property
            .def("A SimpleDate should toString() and fromString() back to the same value")       
            .forAll(simpleDates)
            .suchThat( d -> SimpleDate.fromStringDate(d.toString()).get().equals(d) )
            .check();
    
        if(result.isErroneous()) {
            result
                .sample()
                .forEach( System.err::println );
        }

   }
}






