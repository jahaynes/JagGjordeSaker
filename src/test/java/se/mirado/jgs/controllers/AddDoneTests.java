package se.mirado.jgs.controllers;

import org.junit.Test;
import se.mirado.jgs.TestData;
import se.mirado.jgs.common.Update;
import se.mirado.jgs.data.AppState;

import static org.junit.Assert.assertEquals;

public class AddDoneTests {

	private final Update addDone = AddDone.run(TestData.date1, TestData.alice, TestData.done1);

	private final AppState empty = new AppState();
	private final AppState once = addDone.apply(empty).get();
	private final AppState twice = addDone.apply(once).get();

	@Test
	public void addDoneTest() {
		assertEquals(0, empty.getDones().size());
		assertEquals(1, once.getDones().size());
		assertEquals(2, twice.getDones().size());
	}

}
