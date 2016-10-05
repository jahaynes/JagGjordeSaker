package se.mirado.jgs.controllers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.function.Function;

import se.mirado.jgs.TestData;
import se.mirado.jgs.data.AppState;

public class DeleteDoneTests {

	private final Function<AppState, AppState> addDone =
			AddDone.run(TestData.alice, TestData.done1);

	private final AppState dobble =
			addDone.apply(addDone.apply(new AppState()));

	@Test
	public void deleteDoneTests() {

		assertEquals(2, dobble.dones.size());

		AppState single = DeleteDone.run(TestData.alice, 2L).apply(dobble);
		assertEquals(1, single.dones.size());

		AppState empty = DeleteDone.run(TestData.alice, 1L).apply(single);
		assertEquals(0, empty.dones.size());

	}

	@Test (expected = SecurityException.class)
	public void deleteDonesWrongUserTest() {

		assertEquals(2, dobble.dones.size());

		DeleteDone.run(TestData.bob, 2L).apply(dobble);

	}

}
