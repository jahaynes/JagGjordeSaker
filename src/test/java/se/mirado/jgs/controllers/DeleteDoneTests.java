package se.mirado.jgs.controllers;

import org.junit.Test;
import se.mirado.jgs.TestData;
import se.mirado.jgs.common.Update;
import se.mirado.jgs.data.AppState;

import static org.junit.Assert.assertEquals;

public class DeleteDoneTests {

	private final Update addDone =
			AddDone.run(TestData.date1, TestData.alice, TestData.done1);

	private final AppState twice =
			addDone.apply(addDone.apply(new AppState()).get()).get();

	@Test
	public void deleteDoneTests() {

		assertEquals(2, twice.getDones().size());

		AppState once = DeleteDone.run(TestData.alice, 2L).apply(twice).get();
		assertEquals(1, once.getDones().size());

		AppState empty = DeleteDone.run(TestData.alice, 1L).apply(once).get();
		assertEquals(0, empty.getDones().size());

	}

	@Test (expected = SecurityException.class)
	public void deleteDonesWrongUserTest() {

		assertEquals(2, twice.getDones().size());

		DeleteDone.run(TestData.bob, 2L).apply(twice);

	}

}
