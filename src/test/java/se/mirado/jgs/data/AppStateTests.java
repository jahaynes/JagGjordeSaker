package se.mirado.jgs.data;

import org.junit.Test;
import se.mirado.jgs.TestData;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class AppStateTests {

	private final Function<Long, Done> addFunc =
			id -> Done.make(id, TestData.date1, TestData.alice, TestData.done1);

	private final AppState empty = new AppState();
	private final AppState once = empty.prepend( addFunc );
	private final AppState twice = once.prepend( addFunc );

	@Test
	public void testEquality() {
		assertEquals(empty, empty);
		assertEquals(once, once);
		assertEquals(twice, twice);
	}

	@Test
	public void testAppend() {
		assertEquals(0, empty.getDones().length());
		assertEquals(1, once.getDones().length());
		assertEquals(2, twice.getDones().length());

		assertEquals(once, (empty.prepend(addFunc)));
		assertEquals(twice, (once.prepend(addFunc)));
	}

	@Test
	public void testRemove() {
		assertEquals(once, twice.remove(2L));
		assertEquals(empty, once.remove(1L));
	}

}
