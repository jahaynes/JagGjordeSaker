package se.mirado.jgs.controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import se.mirado.jgs.TestData;
import se.mirado.jgs.common.MeasuredFunction;
import se.mirado.jgs.data.AppState;

public class AddDoneTests {

	private final MeasuredFunction<AppState, AppState> addDone = AddDone.run(TestData.date1, TestData.alice, TestData.done1);

	private final AppState empty = new AppState();
	private final AppState single = addDone.apply(empty);
	private final AppState dobble = addDone.apply(single);

	@Test
	public void addDoneTest() {
		assertEquals(0, empty.dones.size());
		assertEquals(1, single.dones.size());
		assertEquals(2, dobble.dones.size());
	}

}
