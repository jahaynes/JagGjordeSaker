package se.mirado.jgs.data;

import org.junit.Test;

import se.mirado.jgs.TestData;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

public class AppStateTests {

	private Function<Long, Done> addFunc =
			id -> Done.make(id, TestData.date1, TestData.alice, TestData.done1);

	private AppState empty = new AppState();
	private AppState single = empty.prepend( addFunc );
	private AppState dubble = single.prepend( addFunc );

	@Test
	public void testEquality() {
		assertEquals(empty, empty);
		assertEquals(single, single);
		assertEquals(dubble, dubble);
	}

	@Test
	public void testAppend() {
		assertEquals(0, empty.dones.length());
		assertEquals(1, single.dones.length());
		assertEquals(2, dubble.dones.length());

		assertEquals(single, (empty.prepend(addFunc)));
		assertEquals(dubble, (single.prepend(addFunc)));
	}

	@Test
	public void testRemove() {
		assertEquals(single, dubble.remove(2L));
		assertEquals(empty, single.remove(1L));
	}

}
